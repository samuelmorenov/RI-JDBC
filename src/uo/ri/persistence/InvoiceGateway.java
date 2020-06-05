package uo.ri.persistence;

import java.util.Date;
import java.util.List;

public interface InvoiceGateway extends Gateway{

	void testRepairs(List<Long> workOrderIds);

	long generateInvoiceNumber();

	void createInvoice(long numberInvoice, Date dateInvoice, double vat, double total);

	void linkWorkorderInvoice(long idInvoice, List<Long> workOrderIds);

	void updateWorkOrderStatus(List<Long> workOrderIds, String string);

	double checkTotalLabor(Long workOrderID);

	double checkTotalParts(Long workOrderID);

	void updateWorkorderTotal(Long workOrderID, double workTotal);

	long getGeneratedKey(long numberInvoice);

}
