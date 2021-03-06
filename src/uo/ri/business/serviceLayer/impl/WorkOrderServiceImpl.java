package uo.ri.business.serviceLayer.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.business.serviceLayer.WorkOrderService;
import uo.ri.business.transactionScripts.foreman.FindCertificatesByVehicleTypeId;
import uo.ri.business.transactionScripts.foreman.workOrder.AssignWorkOrder;
import uo.ri.business.transactionScripts.foreman.workOrder.CRUD.FindWorkOrderById;
import uo.ri.business.transactionScripts.foreman.workOrder.CRUD.RegisterWorkOrder;
import uo.ri.business.transactionScripts.foreman.workOrder.CRUD.RemoveWorkOrder;
import uo.ri.business.transactionScripts.foreman.workOrder.CRUD.UpdateWorkOrder;
import uo.ri.common.BusinessException;

public class WorkOrderServiceImpl implements WorkOrderService {

    @Override
    public WorkOrderDto registerNew(WorkOrderDto dto)
	    throws BusinessException {
	RegisterWorkOrder rwo = new RegisterWorkOrder(dto);
	return rwo.execute();
    }

    @Override
    public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
	UpdateWorkOrder rwo = new UpdateWorkOrder(dto);
	rwo.execute();
    }

    @Override
    public void deleteWorkOrder(Long id) throws BusinessException {
	RemoveWorkOrder rwo = new RemoveWorkOrder(id);
	rwo.execute();
    }

    @Override
    public Optional<WorkOrderDto> findWorkOrderById(Long woId)
	    throws BusinessException {
	FindWorkOrderById fwo = new FindWorkOrderById(woId);
	return fwo.execute();
    }

    @Override
    public List<CertificateDto> findCertificatesByVehicleTypeId(Long id) {
	FindCertificatesByVehicleTypeId fcbvti =
		new FindCertificatesByVehicleTypeId(id);
	return fcbvti.execute();
    }

    @Override
    public void assignWorkOrderToMechanic(Long woId, Long mechanicId)
	    throws BusinessException {
	AssignWorkOrder awo = new AssignWorkOrder(woId, mechanicId);
	awo.execute();
    }

}
