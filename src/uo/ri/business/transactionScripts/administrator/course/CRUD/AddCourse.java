package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class AddCourse {

	private CourseDto course;

	public AddCourse(CourseDto dto) {
		this.course = dto;
	}

	/**
	 * TODO @throws BusinessException if: <br>
	 * - any field other than id and version is null or empty, or <br>
	 * - there already exists a course with the same name, or <br>
	 * - there is percentage devoted to a non existing vehicle type, or <br>
	 * - the initial and final dates are in the past or inverted, or <br>
	 * - the number of hours are zero or negative, or <br>
	 * - there are no dedications specified, or <br>
	 * - the sum of devoted percentages does not equals 100%, or <br>
	 * - the are any dedication with an invalid percentage (empty, zero, negative)
	 */
	public CourseDto execute() {
		try (Connection c = Jdbc.getConnection();) {
			// Factoria
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			c.setAutoCommit(false);
			cg.setConnection(c);
			// Llamada al add curso de la persistencia
			cg.add(course);
			long lId = cg.findLastId();
			course.id = lId;
			c.commit();
			return course;
		} catch (SQLException e) {
			Err.transactionScripts(e);
			return null;
		}
	}

}
