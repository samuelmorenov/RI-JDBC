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

	public CourseDto execute() {
		/*TODO @throws BusinessException, if:
		 *  - any field other than id and version is null or empty, or
		 * 	- there already exists a course with the same name, or
		 * 	- there is percentage devoted to a non existing vehicle type, or
		 * 	- the initial and final dates are in the past or inverted, or
		 * 	- the number of hours are zero or negative, or
		 *  - there are no dedications specified, or
		 *  - the sum of devoted percentages does not equals 100%, or
		 *  - the are any dedication with an invalid percentage (empty, zero, negative)
		 */
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
