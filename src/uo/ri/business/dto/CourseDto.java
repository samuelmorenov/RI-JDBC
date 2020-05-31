package uo.ri.business.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CourseDto {

	public Long id;

	public String code;
	public String name;
	public String description;
	public Date startDate;
	public Date endDate;
	public int hours;

	/**
	 * A map of the form:
	 * 	Vehicle type id -> percentage
	 * 	 2423 -> 25%
	 * 	 4232-346-tyc -> 25%
	 * 	 5445 -> 50%
	 */
	public Map<Long, Integer> percentages = new HashMap<>();
}
