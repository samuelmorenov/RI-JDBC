package uo.ri.business.serviceLayer;

import java.util.List;

import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.common.BusinessException;

/**
 * This service is intended to be used by the Manager It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface CourseReportService {

	/**
	 * Returns a list of rows, one for each vehicle type and mechanic that had
	 * attended to a course.
	 *
	 * @return the list, that might be empty if no mechanic has been trained for any
	 *         vehicle type.
	 * @throws BusinessException, DOES NOT
	 */
	List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic();

}
