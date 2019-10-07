package uo.ri.ui.administrador.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.exception.BusinessException;

public class ListMechanicsAction implements Action {

	private static String SQL = "select id, dni, name, surname from TMECHANICS";

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);

			rs = pst.executeQuery();
			while (rs.next()) {
				Console.printf("\t%d %s %s %s\n", rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// throw new RuntimeException(e);
			Console.println("Error al listar mecanicos.");
			// TODO Comprobar el tratamiento de excepciones, de momento no se muestra
			// informacion al usuario
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
