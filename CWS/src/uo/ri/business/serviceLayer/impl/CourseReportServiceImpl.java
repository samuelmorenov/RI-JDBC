package uo.ri.business.serviceLayer.impl;

import java.util.List;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.TrainingForMechanicRow;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.serviceLayer.CourseReportService;
import uo.ri.business.transactionScripts.administrator.ListTrainingByVehicleType;
import uo.ri.common.BusinessException;

public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(Long id) throws BusinessException {
		// TO-DO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		ListTrainingByVehicleType ltbvt = new ListTrainingByVehicleType();
		return ltbvt.execute();
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		// TO-DO Auto-generated method stub
		return null;
	}

}
