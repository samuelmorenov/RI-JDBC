package uo.ri.business.transactionScripts.administrator.mechanic.CRUD;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class DeleteMechanic {
	private long idMechanic;

	public DeleteMechanic(Long idMechanic) {
		this.idMechanic = idMechanic;
	}

	/**
	 * @throws BusinessException if: <br>
	 *                           the mechanic does not exist
	 */
	public void execute() throws BusinessException {

		try (Connection c = Jdbc.getConnection();) {

			MechanicGateway mg = PersistenceFactory.getMechanicGateway(); // Factoria
			c.setAutoCommit(false);
			mg.setConnection(c);
			if (mg.findById(idMechanic) == null) { // Llamada al findById de la persistencia
				c.rollback();
				throw new BusinessException("No existe un mecanico con ese ID");
			}
			mg.delete(idMechanic); // Llamada al add mecanico de la persistencia
			c.commit();
		} catch (SQLException e) {
			Err.transactionScripts(e);
		}

	}

}
