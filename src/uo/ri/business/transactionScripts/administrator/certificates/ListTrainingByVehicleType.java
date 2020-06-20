package uo.ri.business.transactionScripts.administrator.certificates;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.TrainingGateway;

public class ListTrainingByVehicleType {

    public ListTrainingByVehicleType() {

    }

    public List<TrainingHoursRow> execute() {
	// TODO: Aqui estaria mal? no se no se
	try (Connection c = Jdbc.getConnection();) {
	    TrainingGateway tg = PersistenceFactory.getTrainingGateway();
	    tg.setConnection(c);
	    return tg.getTrainingHoursRowList();
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }
}
