package uo.ri.business.transactionScripts.administrator.vehicleTypes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehicleTypesGateway;

public class FindAllVehicleTypes {
	
	public FindAllVehicleTypes() {
		
	}

	public List<VehicleTypeDto> execute() {
		try (Connection c = Jdbc.getConnection();) {
			VehicleTypesGateway vtg = PersistenceFactory.getVehicleTypesGateway();
			vtg.setConnection(c);
			return vtg.findAllVehicleTypes();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
