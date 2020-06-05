package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
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
		String SQL = Conf.getInstance().getProperty("SQL_FIND_TRAINING_HOURS_ROW");
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			list = new ArrayList<TrainingHoursRow>();
			while (rs.next()) {

				trainingHoursRow = new TrainingHoursRow();
				trainingHoursRow.mechanicFullName = rs.getString("M_NAME") + rs.getString("M_SURNAME");
				trainingHoursRow.vehicleTypeName = rs.getString("VT_NAME");
				trainingHoursRow.enrolledHours = (int)rs.getLong("HOURS_P");
				list.add(trainingHoursRow);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return list;
	}

}
