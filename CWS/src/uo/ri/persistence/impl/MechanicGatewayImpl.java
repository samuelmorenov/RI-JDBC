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

	@Override
	public void add(MechanicDto mechanic) {

		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_MECHANIC");

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, mechanic.dni);
			pst.setString(2, mechanic.name);
			pst.setString(3, mechanic.surname);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
	

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(MechanicDto mechanic) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<MechanicDto> findAll() {
		List<MechanicDto> mechanics = null;
		MechanicDto mechanic = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_MECHANICS");
		

		try {
			c = Jdbc.getConnection();
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

				// Console.printf("\t%d %s %s %s\n", rs.getLong(1);, rs.getString(2),
				// rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst, c);
		}
		return mechanics;
	}

	@Override
	public MechanicDto findByDNI(String dni) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MechanicDto findById(Long idMechanic) {
		// TODO Auto-generated method stub
		return null;
	}

}
