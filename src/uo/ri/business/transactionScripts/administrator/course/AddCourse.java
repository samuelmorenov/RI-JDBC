package uo.ri.business.transactionScripts.administrator.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class AddCourse {

	private CourseDto course;

	public AddCourse(CourseDto dto) {
		this.course = dto;
	}

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
			throw new RuntimeException("Error de conexion");
		}
	}

}
