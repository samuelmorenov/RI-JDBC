package uo.ri.business.serviceLayer.impl;

import uo.ri.business.serviceLayer.CertificateService;
import uo.ri.business.transactionScripts.administrator.certificates.GenerateCertificates;

public class CertificateServiceImpl implements CertificateService {

	@Override
	public int generateCertificates() {
		GenerateCertificates gc = new GenerateCertificates();
		return gc.execute();
	}

}
