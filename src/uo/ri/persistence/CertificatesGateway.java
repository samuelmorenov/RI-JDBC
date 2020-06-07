package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.CertificateDto;

public interface CertificatesGateway extends Gateway{

	List<CertificateDto> getAllPossibleCertificates();

	CertificateDto findCertificate(Long mechanicId, Long vehicleTypeId);

	void addCertificate(CertificateDto certificado);

	List<CertificateDto> getCertificatesByVehicleTypeId(Long id);

	List<CertificateDto> findAll();
}
