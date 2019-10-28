package uo.ri.business.transactionScripts.cashier;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.InvoiceGateway;

public class WorkOrderBilling {

	List<Long> workOrderIds;
	Connection connection;
	InvoiceGateway ig;

	public WorkOrderBilling(List<Long> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	public InvoiceDto execute() throws BusinessException {
		
		InvoiceDto invoice = null;

		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
			
			ig = PersistenceFactory.getInvoiceGateway(); //Factoria
			ig.setConnection(connection);

			ig.testRepairs(workOrderIds);

			long numberInvoice = ig.generateInvoiceNumber();
			Date dateInvoice = Dates.today();
			double amount = calculateTotalInvoice(workOrderIds); // not vat included
			double vat = vatPercentage(amount, dateInvoice);
			double total = amount * (1 + vat / 100); // vat included
			total = Round.twoCents(total);

			long idInvoice = ig.createInvoice(numberInvoice, dateInvoice, vat, total);
			ig.linkWorkorderInvoice(idInvoice, workOrderIds);
			ig.updateWorkOrderStatus(workOrderIds, "INVOICED");

			invoice = new InvoiceDto();
			invoice.number = numberInvoice;
			invoice.date = dateInvoice;
			invoice.amount = amount;
			invoice.vat = vat;
			invoice.total = total;

			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);
		}
		
		return invoice;

	}

	
	private double vatPercentage(double totalInvoice, Date dateInvoice) {
		return Dates.fromString("1/7/2012").before(dateInvoice) ? 21.0 : 18.0;
	}

	protected double calculateTotalInvoice(List<Long> workOrderIDS) throws BusinessException, SQLException {

		double totalInvoice = 0.0;
		for (Long workOrderID : workOrderIDS) {
			double laborTotal = ig.checkTotalLabor(workOrderID);
			double sparesTotal = ig.checkTotalParts(workOrderID);
			double workTotal = laborTotal + sparesTotal;

			ig.updateWorkorderTotal(workOrderID, workTotal);

			totalInvoice += workTotal;
		}
		return totalInvoice;
	}

}
