package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class FindWorkOrderById {

	Long id;

	public FindWorkOrderById(Long woId) {
		id = woId;
	}

	public Optional<WorkOrderDto> execute() {

		WorkOrderDto workOrder;

		try (Connection c = Jdbc.getConnection();) {

			WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
			wog.setConnection(c);

			workOrder = wog.findById(id);

			return (workOrder != null) ? Optional.of(workOrder) : Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
	}

}
