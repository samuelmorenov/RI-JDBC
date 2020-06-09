package uo.ri.business.transactionScripts.foreman.workOrder.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class RegisterWorkOrder {
	private WorkOrderDto workOrderDto;

	public RegisterWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	public WorkOrderDto execute() {
		/* TODO @throws BusinessException if:
		 * 	- there is another work order for the same vehicle at the same
		 * 		date and time (timestamp), or
		 *  - the vehicle does not exist
		 */
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			c.setAutoCommit(false);
			workOrderDto.date = new Date();
			workOrderDto.status = "OPEN";
			wog.AddWorkOrder(workOrderDto);
			workOrderDto.id = wog.getLastId();
			c.commit();
			return workOrderDto;

		} catch (SQLException e) {
			Err.transactionScripts(e);
			return null;
		}
	}

}
