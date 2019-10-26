package uo.ri.ui.cashier.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.transactionScripts.cashier.WorkOrderBilling;
import uo.ri.common.BusinessException;

public class WorkOrderBillingAction implements Action {

	@Override
	public void execute() throws BusinessException {
		List<Long> workOrderIds = new ArrayList<Long>();

		// type work order ids to be invoiced in the invoice
		do {
			Long id = Console.readLong("Type work order ids ? ");
			workOrderIds.add(id);
		} while (nextWorkorder());

		WorkOrderBilling wob = new WorkOrderBilling();
		InvoiceDto invoice = wob.execute();
		
		//TODO WorkOrderBilling Hacer la implemementacion del curdservice
		//MechanicCrudService mcs = new MechanicCrudServiceImpl();
		//mcs.addMechanic(mechanic);
		
		displayInvoice(invoice.number, invoice.date, invoice.amount, invoice.vat, invoice.total);

	}

	private void displayInvoice(long numberInvoice, Date dateInvoice, double totalInvoice, double vat,
			double totalConIva) {

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
