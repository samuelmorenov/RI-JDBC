package uo.ri.ui.administrator.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class UpdateMechanicAction implements Action {

	private static String SQL = 
		"update TMechanics " +
			"set name = ?, surname = ? " +
			"where id = ?";

	@Override
	public void execute() throws BusinessException {
		
		// Get info
		Long id = Console.readLong("Type mechahic id to update"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, name);
			pst.setString(2, surname);
			pst.setLong(3, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		// Print result
		Console.println("Mechanic updated");
	}

}
