package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		} finally {
			Jdbc.close(rs, pst);
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
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void delete(Long id) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_WORKORDER");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public WorkOrderDto findById(Long id) {
		WorkOrderDto workOrder = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_WORKORDER_BY_ID");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return workOrder;
			}

			workOrder = new WorkOrderDto();
			workOrder.id = rs.getLong("id");
			workOrder.total = rs.getDouble("AMOUNT");
			workOrder.date = rs.getDate("DATE");
			workOrder.description = rs.getString("DESCRIPTION");
			workOrder.status = rs.getString("STATUS");
			workOrder.invoiceId = rs.getLong("INVOICE_ID");
			workOrder.mechanicId = rs.getLong("MECHANIC_ID");
			workOrder.vehicleId = rs.getLong("VEHICLE_ID");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return workOrder;
	}

	@Override
	public void update(WorkOrderDto workOrderDto) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, workOrderDto.description);
			pst.setString(2, workOrderDto.status);
			pst.setLong(3, workOrderDto.id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean mechanicAbleToWorkOrder(Long mechanicId, Long woId) {

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_MECHANIC_CERTIFICATE_IN_VEHICLETYPE");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, woId);
			pst.setLong(2, mechanicId);
			rs = pst.executeQuery();
			return rs.next();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void AssignMechanic(Long mechanicId, Long woId) {
		Connection c = null;
		PreparedStatement pst = null;

		String SQL = Conf.getInstance().getProperty("SQL_ASSIGN_MECHANIC");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanicId);
			pst.setLong(2, woId);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public Long getLastId() {
		Statement st = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_LAST_ID_WORKORDER");
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
	}

	@Override
	public int numberOfInterventios(Long woId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_INTERVENTIONS_OF_WORKORDER");
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, woId);
			rs = pst.executeQuery();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public String getStatus(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String SQL = Conf.getInstance().getProperty("SQL_CHECK_WORKORDER_STATUS");
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderID);
			rs = pst.executeQuery();
			rs.next();
			return rs.getString("STATUS");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
