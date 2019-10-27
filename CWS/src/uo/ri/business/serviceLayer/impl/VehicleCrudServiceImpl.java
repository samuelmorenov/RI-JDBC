package uo.ri.business.serviceLayer.impl;

import java.util.Optional;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.serviceLayer.VehicleCrudService;
import uo.ri.business.transactionScripts.foreman.GetVehicleByPlate;
import uo.ri.common.BusinessException;

public class VehicleCrudServiceImpl implements VehicleCrudService {

	@Override
	public Optional<VehicleDto> findVehicleByPlate(String plate) throws BusinessException {
		GetVehicleByPlate gvbp = new GetVehicleByPlate(plate);
		return gvbp.execute();
	}

}
