package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.conf.Err;
import uo.ri.persistence.VehiclesGateway;

public class VehiclesGatewayImpl extends GatewayImpl implements VehiclesGateway{

	@Override
	public boolean existId(Long vehicleId) {

		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_ID_FROM_VEHICLE_BY_ID");

		try {

			pst = c.prepareStatement(SQL);
			pst.setLong(1, vehicleId);
			rs = pst.executeQuery();

			return rs.next();

		} catch (SQLException e) {
			Err.persistence(e);
			return false;
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
