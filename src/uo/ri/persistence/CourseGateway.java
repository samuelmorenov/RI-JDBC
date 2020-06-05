package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.CourseDto;

public interface CourseGateway extends Gateway {
	void add(CourseDto course);

	long findLastId();

	void delete(Long id);

	CourseDto findById(Long cId);

	List<CourseDto> findAll();

	void update(CourseDto dto);
}
