package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class UpdateWorkOrder {
	
	private WorkOrderDto workOrderDto;

	public UpdateWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	public void execute() {
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);

			//TODO 
			
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		
	}

}
