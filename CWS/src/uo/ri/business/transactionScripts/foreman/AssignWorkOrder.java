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

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);

			if (!wog.mechanicAbleToWorkOrder(mechanicId, woId))
				throw new BusinessException("El mecanico no esta certificado para ese tipo de vehiculo");
			//TODO siempre salta esta excepcion, testear bien 

			wog.AssignMechanic(mechanicId, woId);

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}

	}

}
