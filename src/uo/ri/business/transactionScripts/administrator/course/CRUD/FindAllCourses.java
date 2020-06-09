package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class FindAllCourses {

	public FindAllCourses() {

	}

	public List<CourseDto> execute() {
		try (Connection c = Jdbc.getConnection();) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findAll();
		} catch (SQLException e) {
			Err.transactionScripts(e);
			return null;
		}
	}

}
