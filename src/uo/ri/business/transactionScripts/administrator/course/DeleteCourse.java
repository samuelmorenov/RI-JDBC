package uo.ri.business.transactionScripts.administrator.course;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class DeleteCourse {

	private Long id;

	public DeleteCourse(Long id) {
		this.id = id;
	}

	public void execute() throws BusinessException{
		//TODO: Eliminar un curso. Solo si no tiene mecánicos registrados.
		try (Connection c = Jdbc.getConnection();) {

			CourseGateway cg = PersistenceFactory.getCourseGateway(); 
			c.setAutoCommit(false);
			cg.setConnection(c);
			if (cg.findById(id) == null) {
				c.rollback();
				throw new BusinessException("No existe un curso con ese ID");
			}
			cg.delete(id);
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
