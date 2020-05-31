package uo.ri.persistence;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import uo.ri.common.BusinessException;

public interface InvoiceGateway extends Gateway{

	void testRepairs(List<Long> workOrderIds) throws SQLException, BusinessException;

	long generateInvoiceNumber() throws SQLException;

	long createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) throws SQLException;

	void linkWorkorderInvoice(long idInvoice, List<Long> workOrderIds) throws SQLException;

	void updateWorkOrderStatus(List<Long> workOrderIds, String string) throws SQLException;

	double checkTotalLabor(Long workOrderID) throws BusinessException, SQLException;

	double checkTotalParts(Long workOrderID) throws SQLException;

	void updateWorkorderTotal(Long workOrderID, double workTotal) throws SQLException;

}
