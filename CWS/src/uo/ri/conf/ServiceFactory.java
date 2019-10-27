package uo.ri.conf;

import uo.ri.business.serviceLayer.CertificateService;
import uo.ri.business.serviceLayer.CourseReportService;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.business.serviceLayer.VehicleCrudService;
import uo.ri.business.serviceLayer.WorkOrderService;
import uo.ri.business.serviceLayer.impl.CertificateServiceImpl;
import uo.ri.business.serviceLayer.impl.CourseReportServiceImpl;
import uo.ri.business.serviceLayer.impl.MechanicCrudServiceImpl;
import uo.ri.business.serviceLayer.impl.VehicleCrudServiceImpl;
import uo.ri.business.serviceLayer.impl.WorkOrderServiceImpl;

public class ServiceFactory {
	
	public static MechanicCrudService getMechanicCrudService() {
		return new MechanicCrudServiceImpl();
	}
	
	public static CertificateService getCertificateService() {
		return new CertificateServiceImpl();
	}

	public static CourseReportService getCourseReportService() {
		return new CourseReportServiceImpl();
	}

	public static WorkOrderService getWorkOrderService() {
		return new WorkOrderServiceImpl();
	}

	public static VehicleCrudService getVehicleCrudService() {
		return new VehicleCrudServiceImpl();
	}
	
	

}
