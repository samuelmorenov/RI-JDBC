package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Conf;
import uo.ri.conf.Err;
import uo.ri.persistence.VehicleTypesGateway;

public class VehicleTypesGatewayImpl extends GatewayImpl
	implements VehicleTypesGateway {

    @Override
    public List<VehicleTypeDto> findAll() {
	List<VehicleTypeDto> vehicleTypes = null;
	VehicleTypeDto vehicleType = null;
	Statement st = null;
	ResultSet rs = null;
	String SQL =
		Conf.getInstance().getProperty("SQL_FIND_ALL_VEHICLETYPE");

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
	    Err.persistence(e);
	} finally {
	    Jdbc.close(rs, st);
	}
	return vehicleTypes;
    }

    @Override
    public VehicleTypeDto findById(Long key) {
	VehicleTypeDto vehicleType = null;

	PreparedStatement pst = null;
	ResultSet rs = null;

	String SQL =
		Conf.getInstance().getProperty("SQL_FIND_VEHICLETYPE_BY_ID");

	try {

	    pst = c.prepareStatement(SQL);
	    pst.setLong(1, key);
	    rs = pst.executeQuery();

	    if (rs.next() == false) {
		return vehicleType;
	    }

	    vehicleType = new VehicleTypeDto();
	    vehicleType.id = rs.getLong("id");
	    vehicleType.id = rs.getLong("id");
	    vehicleType.minTrainigHours = rs.getInt("MINTRAININGHOURS");
	    vehicleType.name = rs.getString("name");
	    vehicleType.pricePerHour = rs.getInt("pricePerHour");

	} catch (SQLException e) {
	    Err.persistence(e);
	} finally {
	    Jdbc.close(rs, pst);
	}
	return vehicleType;
    }

}
