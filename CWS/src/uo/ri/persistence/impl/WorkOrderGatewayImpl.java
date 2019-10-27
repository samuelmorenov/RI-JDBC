package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.WorkOrderGateway;

public class WorkOrderGatewayImpl extends GatewayImpl implements WorkOrderGateway {

	@Override
	public Optional<VehicleDto> findVehicleByPlate(String plate) {
		VehicleDto vehicle = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_VEHICLE_BY_PLATE");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setString(1, plate);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return Optional.empty();
			}
			vehicle = new VehicleDto();
			vehicle.id = rs.getLong("ID");
			vehicle.make = rs.getString("MAKE");
			vehicle.model = rs.getString("MODEL");
			vehicle.plate = rs.getString("PLATENUMBER");
			vehicle.clientId = rs.getLong("CLIENT_ID");
			vehicle.vehicleTypeId = rs.getLong("VEHICLETYPE_ID");

			return Optional.of(vehicle);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void AddWorkOrder(WorkOrderDto workOrderDto) {

		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_WORKORDER");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderDto.vehicleId);
			pst.setString(2, workOrderDto.description);
			java.sql.Date sqlDate = new Date(workOrderDto.date.getTime());
			pst.setDate(3, sqlDate);
			pst.setString(4, workOrderDto.status);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
