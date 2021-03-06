package uo.ri.business.transactionScripts.foreman.workOrder.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.conf.Err;
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
	    c.setAutoCommit(false);

	    workOrder = wog.findById(id);
	    c.commit();

	    return (workOrder != null) ? Optional.of(workOrder)
		    : Optional.empty();

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }

}
