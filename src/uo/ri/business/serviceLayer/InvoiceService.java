package uo.ri.business.serviceLayer;

import java.util.List;
import uo.ri.business.dto.InvoiceDto;
import uo.ri.common.BusinessException;

/**
 * TODO: This service is intended to be used by the Cashier It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface InvoiceService {

	InvoiceDto createInvoiceFor(List<Long> workOrderIds) throws BusinessException;

}
