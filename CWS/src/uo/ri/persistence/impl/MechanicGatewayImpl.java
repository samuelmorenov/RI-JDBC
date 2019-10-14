package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		return null;
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
