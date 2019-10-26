package uo.ri.business.transactionScripts.administrator;

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

		try (Connection c = Jdbc.getConnection();) {

			CertificatesGateway cg = PersistenceFactory.getCertificatesGateway(); // Factoria
			cg.setConnection(c);
			List<CertificateDto> list;
			list = cg.getAllPossibleCertificates(); // Llamada al add mecanico de la persistencia
			for (CertificateDto e : list) {
				if (cg.findCertificate(e.mechanic, e.vehicleType) == null) { //
					e.obtainedAt = new Date();
					cg.addCertificate(e);
					certificadosGenerados++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error de conexion");
		}
		return certificadosGenerados;
	}
}
