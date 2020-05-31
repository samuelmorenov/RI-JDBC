package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class UpdateWorkOrder {

	private WorkOrderDto workOrderDto;

	public UpdateWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);

			if (wog.findById(workOrderDto.id) == null) {
				throw new BusinessException("No existe una workorder con ese ID");
			}

			if (!workOrderDto.status.equals("OPEN") && !workOrderDto.status.equals("ASSIGNED")) {
				throw new BusinessException("El estado de la work order impide su modificacion");
			}

			wog.update(workOrderDto);

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}
}