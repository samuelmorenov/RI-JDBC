package uo.ri.business.transactionScripts.administrator.course;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;

public class FindAllVehicleTypes {
	
	public FindAllVehicleTypes() {
		
	}

	public List<VehicleTypeDto> execute() {
		try (Connection c = Jdbc.getConnection();) {
			CourseGateway cg = PersistenceFactory.getCourseGateway();
			cg.setConnection(c);
			return cg.findAllVehicleTypes();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
