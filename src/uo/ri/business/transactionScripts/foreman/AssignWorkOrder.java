package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class AssignWorkOrder {
	private Long woId;
	private Long mechanicId;

	public AssignWorkOrder(Long woId, Long mechanicId) {
		this.woId = woId;
		this.mechanicId = mechanicId;
	}

	public void execute() throws BusinessException {
		try (Connection c = Jdbc.getConnection();) {

			// TODO -> Deberías haber controlado las validaciones que se
			// especifican en la interfaz de servicio en el TS correspondiente
			// (assignToMechanic).

			// @throws BusinessException if:
			// - the mechanic does not exist, or
			// - the work order does not exist, or
			// - the work order is not in OPEN status
			//

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);
			c.setAutoCommit(false);

			if (!wog.mechanicAbleToWorkOrder(mechanicId, woId))
				throw new BusinessException("El mecanico no esta certificado para ese tipo de vehiculo");

			wog.AssignMechanic(mechanicId, woId);
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

}
