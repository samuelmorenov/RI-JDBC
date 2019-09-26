package uo.ri.ui.administrador.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.business.exception.BusinessException;

public class AddMechanicAction implements Action {

	private static String SQL = "insert into TMECHANICS(dni, name, surname) values (?, ?, ?)";

	@Override
	public void execute() throws BusinessException {

		// Pedir datos
		String dni = Console.readString("Dni");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");

		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(SQL);
			pst.setString(1, dni);
			pst.setString(2, nombre);
			pst.setString(3, apellidos);

			pst.executeUpdate();

			// Mostrar resultado
			Console.println("Nuevo mecánico añadido");

		} catch (SQLException e) {
			// throw new RuntimeException(e);
			Console.println("Error al añadir el mecanico.");
			// TODO Comprobar el tratamiento de excepciones, de momento no se muestra
			// informacion al usuario
		} finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
