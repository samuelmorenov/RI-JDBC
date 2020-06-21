package uo.ri.business.transactionScripts.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificatesGateway;

public class FindCertificatesByVehicleTypeId {

    private Long id;

    public FindCertificatesByVehicleTypeId(Long id) {
	this.id = id;
    }

    public List<CertificateDto> execute() {
	try (Connection c = Jdbc.getConnection();) {
	    CertificatesGateway cg =
		    PersistenceFactory.getCertificatesGateway();
	    cg.setConnection(c);
	    c.setAutoCommit(false);
	    List<CertificateDto> aux = cg.getCertificatesByVehicleTypeId(id);
	    c.commit();
	    return aux;
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }

}
