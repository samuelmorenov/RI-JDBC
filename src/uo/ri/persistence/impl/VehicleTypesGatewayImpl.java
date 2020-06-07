package uo.ri.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.VehicleTypesGateway;

public class VehicleTypesGatewayImpl extends GatewayImpl implements VehicleTypesGateway {

	@Override
	public List<VehicleTypeDto> findAll() {
		List<VehicleTypeDto> vehicleTypes = null;
		VehicleTypeDto vehicleType = null;
		Statement st = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLETYPE");

		try {
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			vehicleTypes = new ArrayList<VehicleTypeDto>();
			while (rs.next()) {
				vehicleType = new VehicleTypeDto();
				vehicleType.id = rs.getLong("id");
				vehicleType.minTrainigHours = rs.getInt("MINTRAININGHOURS");
				vehicleType.name = rs.getString("name");
				vehicleType.pricePerHour = rs.getInt("pricePerHour");
				vehicleTypes.add(vehicleType);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
		return vehicleTypes;
	}

}
