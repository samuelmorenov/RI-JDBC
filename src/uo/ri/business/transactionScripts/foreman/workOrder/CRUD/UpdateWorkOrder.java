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

	private WorkOrderDto workOrderToUpdate;

	public UpdateWorkOrder(WorkOrderDto dto) {
		this.workOrderToUpdate = dto;
	}

	/**
	 * @throws BusinessException if: <br>
	 * - there is no work order with that id, or <br>
	 * - there work order has not the specified version (optimistic lock), or <br>
	 * - the work order is not in the OPEN or ASSIGNED status
	 */
	public void execute() throws BusinessException {

		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			c.setAutoCommit(false);
			
			WorkOrderDto workOrderOld = wog.findById(workOrderToUpdate.id);

			if (workOrderOld == null) {
				throw new BusinessException("No existe una workorder con ese ID");
			}

			if (!workOrderToUpdate.status.equals("OPEN") && !workOrderToUpdate.status.equals("ASSIGNED")) {
				throw new BusinessException("El estado de la work order impide su modificacion");
			}
			
			if(workOrderToUpdate.description.equals(workOrderOld.description)) {
				throw new BusinessException("La work order ya tiene esa descripcion");
			}

			wog.update(workOrderToUpdate);
			c.commit();

		} catch (SQLException e) {
			Err.transactionScripts(e);
		}
	}
}