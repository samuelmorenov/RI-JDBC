package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class UpdateCourse {

    private CourseDto CourseToUpdate;

    public UpdateCourse(CourseDto dto) {
	this.CourseToUpdate = dto;
    }

    /**
     * @throws BusinessException if: <br>
     *                           - it does not exist the course with the specified
     *                           id, or <br>
     *                           - the current version of the course does not match
     *                           the version of the dto, or <br>
     *                           - the course has its start date in the past, or
     *                           <br>
     *                           - the new data does not pass the validation
     *                           specified <br>
     */
    public void execute() throws BusinessException {
	// Modificar datos de un curso. Mientras no haya sido, o esté siendo, impartido.

	try (Connection c = Jdbc.getConnection();) {

	    CourseGateway cg = PersistenceFactory.getCourseGateway();
	    c.setAutoCommit(false);
	    cg.setConnection(c);

	    CourseDto CourseOld = cg.findById(CourseToUpdate.id);

	    if (CourseOld == null) {
		c.rollback();
		throw new BusinessException(
			"No existe un mecanico con ese ID");
	    }

	    if (CourseToUpdate.startDate.before(new Date())) {
		c.rollback();
		throw new BusinessException("El curso ya ha empezado");
	    }

	    if (CourseToUpdate.code == CourseOld.code
		    && CourseToUpdate.name.equals(CourseOld.name)
		    && CourseToUpdate.description.equals(
			    CourseOld.description)
		    && CourseToUpdate.startDate.getTime()
			    == CourseOld.startDate.getTime()
		    && CourseToUpdate.endDate.getTime()
			    == CourseOld.endDate.getTime()) {
		c.rollback();
		throw new BusinessException("El curso ya tiene esos datos");
	    }

	    try {
		AddCourse.validateCourse(CourseToUpdate);
	    } catch (BusinessException e) {
		c.rollback();
		throw e;
	    }

	    cg.update(CourseToUpdate);
	    c.commit();
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	}
    }

}
