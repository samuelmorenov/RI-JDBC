package uo.ri.business.transactionScripts.administrator.mechanic;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class AddMechanic {

	private MechanicDto mechanic;

	public AddMechanic(MechanicDto mechanic) {
		this.mechanic = mechanic;
	}

	public void execute() throws BusinessException {

		try (Connection c = Jdbc.getConnection();) {

			//Factoria
			MechanicGateway mg = PersistenceFactory.getMechanicGateway(); 
			c.setAutoCommit(false);
			mg.setConnection(c);
			//Llamada al findByDNI de la persistencia
			if (mg.findByDNI(mechanic.dni) != null) { 
				c.rollback();
				throw new BusinessException("Ya existe un mecanico con ese DNI");
			}
			//Llamada al add mecanico de la persistencia
			mg.add(mechanic); 
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}
}
