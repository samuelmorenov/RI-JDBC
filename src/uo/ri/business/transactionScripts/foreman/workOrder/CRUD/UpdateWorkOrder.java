package uo.ri.business.transactionScripts.foreman.workOrder.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class UpdateWorkOrder {

	private WorkOrderDto workOrderDto;

	public UpdateWorkOrder(WorkOrderDto dto) {
		this.workOrderDto = dto;
	}

	public void execute() throws BusinessException {
		/* TODO @throws BusinessException if:
		 * 	- there is no work order with that id, or
		 *  - there work order has not the specified version (optimistic lock), or
		 *  - the work order is not in the OPEN or ASSIGNED status
		 */
		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			c.setAutoCommit(false);

			if (wog.findById(workOrderDto.id) == null) {
				throw new BusinessException("No existe una workorder con ese ID");
			}

			if (!workOrderDto.status.equals("OPEN") && !workOrderDto.status.equals("ASSIGNED")) {
				throw new BusinessException("El estado de la work order impide su modificacion");
			}

			wog.update(workOrderDto);
			c.commit();

		} catch (SQLException e) {
			Err.transactionScripts(e);
		}
	}
}