package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class RemoveWorkOrder {
	
	private Long id;

	public RemoveWorkOrder(Long id) {
		this.id=id;
	}

	public void execute() {
		try (Connection c = Jdbc.getConnection();) {


			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			c.setAutoCommit(false);
			//TODO -> En borrar no realizas ninguna comprobación
			wog.delete(id);
			c.commit();
			
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		
	}

}
