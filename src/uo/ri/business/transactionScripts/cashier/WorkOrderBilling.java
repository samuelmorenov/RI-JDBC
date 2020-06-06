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
import uo.ri.persistence.WorkOrderGateway;

public class WorkOrderBilling {

	List<Long> workOrderIds;
	Connection c;
	InvoiceGateway ig;

	public WorkOrderBilling(List<Long> workOrderIds) {
		this.workOrderIds = workOrderIds;
	}

	public InvoiceDto execute() throws BusinessException {

		InvoiceDto invoice = null;

		try (Connection c = Jdbc.getConnection();) {

			ig = PersistenceFactory.getInvoiceGateway(); // Factoria
			ig.setConnection(c);
			c.setAutoCommit(false);

			testRepairs(workOrderIds, c);

			long numberInvoice = ig.generateInvoiceNumber();
			Date dateInvoice = Dates.today();
			double amount = calculateTotalInvoice(workOrderIds); // not vat included
			double vat = vatPercentage(dateInvoice);
			double total = amount * (1 + vat / 100); // vat included
			total = Round.twoCents(total);

			ig.createInvoice(numberInvoice, dateInvoice, vat, total);
			long idInvoice = ig.getGeneratedKey(numberInvoice);

			ig.linkWorkorderInvoice(idInvoice, workOrderIds);
			ig.updateWorkOrderStatus(workOrderIds, "INVOICED");

			invoice = new InvoiceDto();
			invoice.number = numberInvoice;
			invoice.date = dateInvoice;
			invoice.amount = amount;
			invoice.vat = vat;
			invoice.total = total;

			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

		return invoice;

	}

	private double vatPercentage(Date dateInvoice) {
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
	
	private void testRepairs(List<Long> workOrderIds, Connection c) throws SQLException {
		WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway(); // Factoria
		wog.setConnection(c);
		
		for (Long workOrderID : workOrderIds) {
			if(wog.findById(workOrderID)== null) {
				throw new RuntimeException("Workorder " + workOrderID + " doesn't exist");
			}
			
			if(!"FINISHED".equalsIgnoreCase(wog.getStatus(workOrderID))){
				throw new RuntimeException("Workorder " + workOrderID + " is not finished yet");
			}
			
		}
		
	}

}
