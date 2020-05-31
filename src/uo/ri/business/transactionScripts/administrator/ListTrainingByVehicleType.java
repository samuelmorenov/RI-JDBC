package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.TrainingGateway;

public class ListTrainingByVehicleType {

	public ListTrainingByVehicleType() {

	}

	public List<TrainingHoursRow> execute() {
		try (Connection c = Jdbc.getConnection();) {
			TrainingGateway tg = PersistenceFactory.getTrainingGateway();
			tg.setConnection(c);
			return tg.getTrainingHoursRowList();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}
