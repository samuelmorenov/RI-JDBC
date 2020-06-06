package uo.ri.business.transactionScripts.administrator.certificates;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificatesGateway;

public class GenerateCertificates {
	public int execute() throws BusinessException {
		int certificadosGenerados = 0;

		// TODO - Has introducido parte de la lógica de negocio de la generación de
		// certificados, que debería estar en el TS, en una consulta SQL
		// (SQL_ALL_POSSIBLE_CERTIFICATES especialmente). Esto genera problemas de
		// escalabilidad, mantenibilidad y legibilidad. Si cambias de base de datos vas
		// a tener que adaptar si o si el TS ya que esa operación depende casi por
		// completo de la consulta. Además rompes el principio básico del uso del patrón
		// TDG. A veces es mejor sacrificar un poco de rendimiento para mejorar el
		// diseño.

		// TODO - La generación debería ser atómica y al no usar una misma transacción
		// para todas las operaciones que la forman te expones a que se produzcan
		// anomalías.

		try (Connection c = Jdbc.getConnection();) {
			CertificatesGateway cg = PersistenceFactory.getCertificatesGateway(); // Factoria
			c.setAutoCommit(false);
			cg.setConnection(c);
			List<CertificateDto> list;
			list = cg.getAllPossibleCertificates(); // Llamada al add mecanico de la persistencia
			for (CertificateDto e : list) {
				if (cg.findCertificate(e.mechanic, e.vehicleType) == null) {
					e.obtainedAt = new Date();
					cg.addCertificate(e);
					certificadosGenerados++;
				}
			}
			c.commit();
		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		return certificadosGenerados;
	}
}
