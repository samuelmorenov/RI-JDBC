package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.CertificateDto;

public interface CertificatesGateway {
	void setConnection(Connection c) throws SQLException;

	List<CertificateDto> getAllPossibleCertificates();

	CertificateDto findCertificate(Long mechanicId, Long vehicleTypeId);

	void addCertificate(CertificateDto certificado);
}
