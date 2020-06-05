package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.VehicleTypeDto;

public interface VehicleTypesGateway extends Gateway {

	List<VehicleTypeDto> findAllVehicleTypes();

}