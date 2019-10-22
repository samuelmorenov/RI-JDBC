package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {

	private Connection c;

	@Override
	public void setConnection(Connection c) throws SQLException {
		c = Jdbc.getConnection();
	}

	@Override
	public void add(MechanicDto mechanic) {
		// Process
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_MECHANIC");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.dni);
			pst.setString(2, mechanic.name);
			pst.setString(3, mechanic.surname);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}

	@Override
	public void delete(Long idMechanic) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_MECHANIC");
		
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}

	@Override
	public void update(MechanicDto mechanic) {
		// Process
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_MECHANIC");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.name);
			pst.setString(2, mechanic.surname);
			pst.setLong(3, mechanic.id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}

	@Override
	public List<MechanicDto> findAll() {
		List<MechanicDto> mechanics = null;
		MechanicDto mechanic = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANICS");

		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			mechanics = new ArrayList<MechanicDto>();
			while (rs.next()) {
				mechanic = new MechanicDto();
				mechanic.id = rs.getLong("id");// (1);
				mechanic.dni = rs.getString("dni");
				mechanic.name = rs.getString("name");
				mechanic.surname = rs.getString("surname");
				mechanics.add(mechanic);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {

		}
		return mechanics;
	}

	@Override
	public MechanicDto findByDNI(String dni) {

		MechanicDto mechanic = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_BY_DNI");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return mechanic;
			}

			mechanic = new MechanicDto();
			mechanic.id = rs.getLong("id");
			mechanic.dni = rs.getString("dni");
			mechanic.name = rs.getString("name");
			mechanic.surname = rs.getString("surname");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanic;
	}

	@Override
	public MechanicDto findById(Long idMechanic) {
		MechanicDto mechanic = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_MECHANIC_BY_ID");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return mechanic;
			}

			mechanic = new MechanicDto();
			mechanic.id = rs.getLong("id");
			mechanic.dni = rs.getString("dni");
			mechanic.name = rs.getString("name");
			mechanic.surname = rs.getString("surname");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanic;
	}

}
