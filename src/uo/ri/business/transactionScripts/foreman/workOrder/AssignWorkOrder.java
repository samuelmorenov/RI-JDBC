package uo.ri.business.transactionScripts.foreman.workOrder;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.WorkOrderGateway;

public class AssignWorkOrder {
	private Long woId;
	private Long mechanicId;

	public AssignWorkOrder(Long woId, Long mechanicId) {
		this.woId = woId;
		this.mechanicId = mechanicId;
	}

	/**
	 * @throws BusinessException if: <br>
	 *                           - the mechanic does not exist, or <br>
	 *                           - the work order does not exist, or <br>
	 *                           - the work order is not in OPEN status
	 */
	public void execute() throws BusinessException {

		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			wog.setConnection(c);
			mg.setConnection(c);
			c.setAutoCommit(false);

			if (mg.findById(mechanicId) == null) {
				c.rollback();
				throw new BusinessException("El mecanico no existe");
			}

			WorkOrderDto wod = wog.findById(woId);

			if (wod == null) {
				c.rollback();
				throw new BusinessException("La orden de trabajo no existe");
			}

			if (!wod.status.equals("OPEN")) {
				c.rollback();
				throw new BusinessException("La orden de trabajo no esta abierta");
			}

			if (!wog.mechanicAbleToWorkOrder(mechanicId, woId)) {
				c.rollback();

				throw new BusinessException("El mecanico no esta certificado para ese tipo de vehiculo");
			}
			wog.AssignMechanic(mechanicId, woId);
			c.commit();

		} catch (SQLException e) {
			Err.transactionScripts(e);
		}

	}

}
