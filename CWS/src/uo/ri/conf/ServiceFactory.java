package uo.ri.conf;

import uo.ri.business.serviceLayer.CertificateService;
import uo.ri.business.serviceLayer.CourseReportService;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.business.serviceLayer.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.impl.MechanicCrudServiceImpl;

public class ServiceFactory {
	
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static CertificateService getCertificateService() {
		return new CertificateServiceImpl();
	}

	public static CourseReportService forCourseReportService() {
		return null; //TODO new CourseReportServiceImpl();
	}

}
