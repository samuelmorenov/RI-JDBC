package uo.ri.business.transactionScripts.foreman.workOrder.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehiclesGateway;
import uo.ri.persistence.WorkOrderGateway;

public class RegisterWorkOrder {
	private WorkOrderDto workOrderDto;

	public RegisterWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	/**
	 * @throws BusinessException if: <br>
	 * - there is another work order for the same vehicle at the same date and time
	 * (timestamp), or <br>
	 * - the vehicle does not exist
	 */
	public WorkOrderDto execute() throws BusinessException {

		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			VehiclesGateway vg = PersistenceFactory.getVehiclesGateway();

			wog.setConnection(c);
			c.setAutoCommit(false);
			workOrderDto.date = new Date();
			workOrderDto.status = "OPEN";

			if (!vg.existId(workOrderDto.vehicleId)) {
				throw new BusinessException("No existe un vehiculo con ese id");
			}

			if (wog.SearchWorkOrder(workOrderDto.vehicleId, workOrderDto.date) != null) {
				throw new BusinessException("Ya existe una work order para ese vehiculo con esta fecha");
			}

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
