package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.VehicleTypesGateway;

public class VehicleTypesGatewayImpl extends GatewayImpl implements VehicleTypesGateway {

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() {
		List<VehicleTypeDto> vehicleTypes = null;
		VehicleTypeDto vehicleType = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLETYPE");

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
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
			Jdbc.close(rs, pst);
		}
		return vehicleTypes;
	}

}
