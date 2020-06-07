package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.EnrollmentDto;

public interface EnrollmentGateway extends Gateway{

	List<EnrollmentDto> findAll();

}
