package uo.ri.business.serviceLayer.impl;

import java.util.List;

import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.serviceLayer.CourseReportService;
import uo.ri.business.transactionScripts.administrator.certificates.ListTrainingByVehicleType;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() {
		ListTrainingByVehicleType ltbvt = new ListTrainingByVehicleType();
		return ltbvt.execute();
	}

}
