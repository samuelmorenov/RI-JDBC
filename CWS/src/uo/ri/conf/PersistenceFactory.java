package uo.ri.conf;

import uo.ri.persistence.CertificatesGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.TrainingGateway;
import uo.ri.persistence.impl.CertificatesGatewayImpl;
import uo.ri.persistence.impl.MechanicGatewayImpl;
import uo.ri.persistence.impl.TrainingGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}
	
	public static CertificatesGateway getCertificatesGateway() {
		return new CertificatesGatewayImpl();
	}

	public static TrainingGateway getTrainingGateway() {
		return new TrainingGatewayImpl();
	}
}
