package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class ListMechanics {

	public ListMechanics() {
	}

	public List<MechanicDto> execute() {

		try (Connection c = Jdbc.getConnection();) {
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			mg.setConnection(c);
			return mg.findAll();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
