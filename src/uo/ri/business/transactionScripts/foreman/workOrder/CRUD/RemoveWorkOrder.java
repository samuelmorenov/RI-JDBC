package uo.ri.business.transactionScripts.foreman.workOrder.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.WorkOrderGateway;

public class RemoveWorkOrder {

    private Long id;

    public RemoveWorkOrder(Long id) {
	this.id = id;
    }

    /**
     * @throws BusinessException if: <br>
     *                           - the work order does not exist, or <br>
     *                           - there already is some intervention registered.
     */
    public void execute() throws BusinessException {

	try (Connection c = Jdbc.getConnection();) {

	    WorkOrderGateway wog = PersistenceFactory.getWorkOrderGateway();
	    wog.setConnection(c);
	    c.setAutoCommit(false);

	    WorkOrderDto wod = wog.findById(id);
	    if (wod == null) {
		c.rollback();
		throw new BusinessException("La orden de trabajo no existe");
	    }
	    if (wog.numberOfInterventios(id) != 0) {

		c.rollback();
		throw new BusinessException(
			"La orden de trabajo aun tiene intercenciones");
	    }

	    wog.delete(id);
	    c.commit();

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	}

    }

}
