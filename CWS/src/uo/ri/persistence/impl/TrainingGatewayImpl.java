package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.conf.Conf;
import uo.ri.persistence.TrainingGateway;

public class TrainingGatewayImpl extends GatewayImpl implements TrainingGateway {

	@Override
	public List<TrainingHoursRow> getTrainingHoursRowList() {
		List<TrainingHoursRow> list = null;
		TrainingHoursRow trainingHoursRow = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_"); // TODO Sentencia SQL
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			list = new ArrayList<TrainingHoursRow>();
			while (rs.next()) {

				trainingHoursRow = new TrainingHoursRow();
				// trainingHoursRow.mechanic = rs.getLong("M_ID");
				// trainingHoursRow.vehicleType = rs.getLong("V_ID");
				list.add(trainingHoursRow);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
