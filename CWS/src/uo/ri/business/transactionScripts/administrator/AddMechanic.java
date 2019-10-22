package uo.ri.business.transactionScripts.administrator;

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

			MechanicGateway mg = PersistenceFactory.getMechanicGateway(); //Factoria
			//c.setAutoCommit(false);
			mg.setConnection(c);
			if (mg.findByDNI(mechanic.dni) != null) { //Llamada al findByDNI de la persistencia
				//c.rollback();
				throw new BusinessException("Ya existe un mecanico con ese DNI");
			}
			mg.add(mechanic); //Llamada al add mecanico de la persistencia
			System.out.println(c == null);
			//c.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error de conexion");
		}

	}
}
