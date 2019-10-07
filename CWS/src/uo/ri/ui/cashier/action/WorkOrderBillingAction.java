package uo.ri.ui.cashier.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.console.Console;
import alb.util.date.Dates;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class WorkOrderBillingAction implements Action {


	
	@Override
	public void execute() throws BusinessException {
		List<Long> workOrderIds = new ArrayList<Long>();
		
		// type work order ids to be invoiced in the invoice
		do {
			Long id = Console.readLong("Type work order ids ? ");
			workOrderIds.add(id);
		} while ( nextWorkorder() );

		//TODO 
		displayInvoice(numberInvoice, dateInvoice, amount, vat, total);

	}

	private void displayInvoice(long numberInvoice, Date dateInvoice,
			double totalInvoice, double vat, double totalConIva) {
		
		Console.printf("Invoice number: %d\n", numberInvoice);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", dateInvoice);
		Console.printf("\tAmount: %.2f €\n", totalInvoice);
		Console.printf("\tVAT: %.1f %% \n", vat);
		Console.printf("\tTotal (including VAT): %.2f €\n", totalConIva);
	}

	private boolean nextWorkorder() {
		return Console.readString(" Any other workorder? (y/n) ").equalsIgnoreCase("y");
	}

}
