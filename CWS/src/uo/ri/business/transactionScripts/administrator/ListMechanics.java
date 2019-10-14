package uo.ri.business.transactionScripts.administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.MechanicDto;

public class ListMechanics {
	private static String SQL = "select id, dni, name, surname from TMechanics";

	public List<MechanicDto> execute() {

		List<MechanicDto> mechanics = null;
		MechanicDto mechanic = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

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
}
