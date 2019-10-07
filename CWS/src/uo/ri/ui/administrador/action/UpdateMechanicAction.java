package uo.ri.ui.administrador.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.exception.BusinessException;

public class UpdateMechanicAction implements Action {

	private static String SQL = "update TMECHANICS " + "set name = ?, surname = ? " + "where id = ?";

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		Long id = Console.readLong("Id del mecánico");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			pst.executeUpdate();

			// Mostrar resultado
			Console.println("Mecánico actualizado");

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			Console.println("Error al actualizar el mecanico.");
			// TODO Comprobar el tratamiento de excepciones, de momento no se muestra
			// informacion al usuario
		} finally {
			Jdbc.close(rs, pst, c);
		}

	}

}
