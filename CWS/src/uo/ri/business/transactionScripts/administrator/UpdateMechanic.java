package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class UpdateMechanic {
	private MechanicDto mechanic;

	public UpdateMechanic(MechanicDto mechanic) {
		this.mechanic = mechanic;
	}

	public void execute() throws BusinessException {
		
		
		try (Connection c = Jdbc.getConnection();) {

			MechanicGateway mg = PersistenceFactory.getMechanicGateway(); //Factoria
			c.setAutoCommit(false);
			mg.setConnection(c);
			if (mg.findById(mechanic.id) == null) { // Llamada al findById de la persistencia
				c.rollback();
				throw new BusinessException("No existe un mecanico con ese ID");
			}
			mg.update(mechanic); //Llamada al add mecanico de la persistencia
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
