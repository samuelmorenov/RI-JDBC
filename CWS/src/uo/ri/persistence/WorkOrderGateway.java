package uo.ri.persistence;

import java.util.Optional;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;

public interface WorkOrderGateway extends Gateway {

	Optional<VehicleDto> findVehicleByPlate(String plate);

	void AddWorkOrder(WorkOrderDto workOrderDto);

	void delete(Long id);
	
}
