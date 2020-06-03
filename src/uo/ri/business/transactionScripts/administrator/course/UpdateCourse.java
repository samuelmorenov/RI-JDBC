package uo.ri.business.transactionScripts.administrator.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class UpdateCourse {

	private CourseDto dto;

	public UpdateCourse(CourseDto dto) {
		this.dto = dto;
	}

	public void execute() throws BusinessException {
		//Modificar datos de un curso. Mientras no haya sido, o esté siendo, impartido.
		try (Connection c = Jdbc.getConnection();) {

			CourseGateway cg = PersistenceFactory.getCourseGateway();
			c.setAutoCommit(false);
			cg.setConnection(c);
			if (cg.findById(dto.id) == null) { // Llamada al findById de la persistencia
				c.rollback();
				throw new BusinessException("No existe un mecanico con ese ID");
			}
			cg.update(dto); //Llamada al add mecanico de la persistencia
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
