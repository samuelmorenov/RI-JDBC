package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class RegisterWorkOrder {
	private WorkOrderDto workOrderDto;

	public RegisterWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	public void execute() {
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			workOrderDto.date = new Date();
			workOrderDto.status = "OPEN";
			wog.AddWorkOrder(workOrderDto);
			
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
