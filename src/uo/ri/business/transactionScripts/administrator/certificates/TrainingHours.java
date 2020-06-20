package uo.ri.business.transactionScripts.administrator.certificates;

import java.util.List;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.DedicationDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;

/**
 * Clase estatica <b>TrainingHours:</b> </br>
 * Sirve para calcular el numero de horas aprobadas que tiene un mecanico para
 * un tipo de vehiculo </br>
 */
public class TrainingHours {

    public static int Calculate(List<EnrollmentDto> allEnrollments,
	    List<DedicationDto> allDedications, List<CourseDto> allCourses,
	    VehicleTypeDto vehicleType, MechanicDto mechanic) {
	int trainingHours = 0;

	for (EnrollmentDto enrollment : allEnrollments) {
	    if (mechanic.id == enrollment.mechanicId) {
		// Solo si se ha pasado
		if (enrollment.passed) {
		    for (CourseDto course : allCourses) {
			if (course.id == enrollment.courseId) {
			    for (DedicationDto dedication : allDedications) {
				if (vehicleType.id
					== dedication.vehicleTyeId) {
				    trainingHours += course.hours
					    * (dedication.percentage / 100);
				}
			    }
			}
		    }
		}
	    }
	}
	return trainingHours;
    }

}
