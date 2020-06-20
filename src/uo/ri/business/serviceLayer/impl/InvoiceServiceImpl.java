package uo.ri.business.serviceLayer.impl;

import java.util.List;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.business.serviceLayer.InvoiceService;
import uo.ri.business.transactionScripts.cashier.WorkOrderBilling;
import uo.ri.common.BusinessException;

public class InvoiceServiceImpl implements InvoiceService {

    @Override
    public InvoiceDto createInvoiceFor(List<Long> workOrderIds)
	    throws BusinessException {
	WorkOrderBilling wob = new WorkOrderBilling(workOrderIds);
	return wob.execute();
    }
}
