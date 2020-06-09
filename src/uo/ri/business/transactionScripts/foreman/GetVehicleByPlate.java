package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class GetVehicleByPlate {
	private String plate;

	public GetVehicleByPlate(String plate) {
		this.plate = plate;
	}

	public Optional<VehicleDto> execute() {
		Optional<VehicleDto> vehicle = null;
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			vehicle = wog.findVehicleByPlate(plate);

		} catch (SQLException e) {
			Err.transactionScripts(e);
		}
		return vehicle;
	}

}
