package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class DeleteCourse {

    private Long id;

    public DeleteCourse(Long id) {
	this.id = id;
    }

    /**
     * @throws BusinessException if: <br>
     *                           - there is no course with the specified id, or <br>
     *                           - the course already has enrollments registered.
     */
    public void execute() throws BusinessException {

	try (Connection c = Jdbc.getConnection();) {

	    CourseGateway cg = PersistenceFactory.getCourseGateway();

	    c.setAutoCommit(false);

	    cg.setConnection(c);

	    if (cg.findById(id) == null) {
		c.rollback();
		throw new BusinessException("No existe un curso con ese ID");
	    }
	    if (cg.getEnrolledMechanics(id) != 0) {
		c.rollback();
		throw new BusinessException(
			"Ese curso aun tiene mecanicos registrados");
	    }
	    cg.delete(id);
	    c.commit();
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	}
    }

}
