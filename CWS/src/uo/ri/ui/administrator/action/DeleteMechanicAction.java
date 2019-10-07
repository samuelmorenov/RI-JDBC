package uo.ri.ui.administrator.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class DeleteMechanicAction implements Action {

	private static String SQL = "delete from TMechanics where id = ?";

	@Override
	public void execute() throws BusinessException {
		Long idMechanic = Console.readLong("Type mechanic id "); 
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMechanic);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		Console.println("Mechanic deleted");
	}

}
