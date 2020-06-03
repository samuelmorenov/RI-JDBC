package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;

public interface CourseGateway extends Gateway {
	void add(CourseDto course);

	long findLastId();

	void delete(Long id);

	CourseDto findById(Long cId);

	List<CourseDto> findAll();

	List<VehicleTypeDto> findAllVehicleTypes();

	void update(CourseDto dto);
}
