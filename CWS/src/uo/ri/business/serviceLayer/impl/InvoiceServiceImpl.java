package uo.ri.business.serviceLayer.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.dto.PaymentMeanDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.InvoiceService;
import uo.ri.business.transactionScripts.cashier.WorkOrderBilling;
import uo.ri.common.BusinessException;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public InvoiceDto createInvoiceFor(List<Long> workOrderIds) throws BusinessException {
		WorkOrderBilling wob = new WorkOrderBilling(workOrderIds);
		return wob.execute();
	}

	@Override
	public InvoiceDto findInvoice(Long numeroFactura) throws BusinessException {
		// TO-DO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPayMethodsForInvoice(Long invoiceId) throws BusinessException {
		// TO-DO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long invoiceId, Map<Long, Double> cargos) throws BusinessException {
		// TO-DO Auto-generated method stub

	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TO-DO Auto-generated method stub
		return null;
	}

}
