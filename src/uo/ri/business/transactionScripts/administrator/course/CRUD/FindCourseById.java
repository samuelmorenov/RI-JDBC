package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class FindCourseById {

    private Long cId;

    public FindCourseById(Long cId) {
	this.cId = cId;
    }

    public Optional<CourseDto> execute() {
	CourseDto course;

	try (Connection c = Jdbc.getConnection();) {

	    CourseGateway cg = PersistenceFactory.getCourseGateway();
	    cg.setConnection(c);
	    c.setAutoCommit(false);
	    course = cg.findById(cId);
	    c.commit();
	    return (course != null) ? Optional.of(course) : Optional.empty();

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }

}
