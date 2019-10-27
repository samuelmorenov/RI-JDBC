package uo.ri.persistence;

import java.util.Optional;

import uo.ri.business.dto.VehicleDto;

public interface WorkOrderGateway extends Gateway {

	Optional<VehicleDto> findVehicleByPlate(String plate);


}
