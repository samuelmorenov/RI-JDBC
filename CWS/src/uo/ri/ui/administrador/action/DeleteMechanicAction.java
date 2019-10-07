package uo.ri.ui.administrador.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.exception.BusinessException;

public class DeleteMechanicAction implements Action {

	private static String SQL = "delete from TMECHANICS where id = ?";

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMecanico);

			pst.executeUpdate();
			Console.println("Se ha eliminado el mecánico");

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			System.out.println("Error al borrar el mecanico.");
			// TODO Comprobar el tratamiento de excepciones, de momento no se muestra
			// informacion al usuario
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
