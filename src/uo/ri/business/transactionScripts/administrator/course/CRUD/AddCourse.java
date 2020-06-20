package uo.ri.business.transactionScripts.administrator.course.CRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.common.BusinessException;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.VehicleTypesGateway;

public class AddCourse {

    private CourseDto course;

    public AddCourse(CourseDto dto) {
	this.course = dto;
    }

    /**
     * @throws BusinessException if: <br>
     *                           - there already exists a course with the same name,
     * 
     */
    public CourseDto execute() throws BusinessException {
	try (Connection c = Jdbc.getConnection();) {
	    // Factoria
	    CourseGateway cg = PersistenceFactory.getCourseGateway();
	    c.setAutoCommit(false);
	    cg.setConnection(c);

	    validateCourse(course);

	    // there already exists a course with the same name
	    if (cg.findByName(course.name) != null) {
		throw new BusinessException(
			"Ya existe un curso con ese nombre");
	    }

	    // Llamada al add curso de la persistencia
	    cg.add(course);
	    long lId = cg.findLastId();
	    course.id = lId;
	    c.commit();
	    return course;
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }

    /**
     * @throws BusinessException if: <br>
     *                           - any field other than id and version is null or
     *                           empty, or <br>
     *                           - there is percentage devoted to a non existing
     *                           vehicle type, or <br>
     *                           - the initial and final dates are in the past or
     *                           inverted, or <br>
     *                           - the number of hours are zero or negative, or <br>
     *                           - there are no dedications specified, or <br>
     *                           - the sum of devoted percentages does not equals
     *                           100%, or <br>
     *                           - the are any dedication with an invalid percentage
     *                           (empty, zero, negative)
     */
    public static void validateCourse(CourseDto course)
	    throws BusinessException {
	// any field other than id and version is null
	if (course.code == null || course.name == null
		|| course.description == null || course.startDate == null
		|| course.endDate == null) {
	    throw new BusinessException(
		    "No puede haber campos vacios en un curso");
	}

	// any field other than id and version is empty
	if (course.code.equals("") || course.name.equals("")
		|| course.description.equals("")) {
	    throw new BusinessException(
		    "No puede haber campos vacios en un curso");
	}

	// the initial and final dates are inverted
	if (course.startDate.getTime() >= course.endDate.getTime()) {
	    throw new BusinessException("Las fechas estan invertidas");
	}
	// the initial date are in the past
	Date now = new Date();
	if (course.startDate.getTime() < now.getTime()) {
	    throw new BusinessException("La fecha de inicio ya han pasado");
	}
	// the final dates are in the past
	if (course.endDate.getTime() < now.getTime()) {
	    throw new BusinessException("La fecha de fin ya han pasado");
	}

	// the number of hours are zero or negative
	if (course.hours <= 0) {
	    throw new BusinessException(
		    "El numero de horas no puede ser menor o igual a 0");
	}

	try (Connection c = Jdbc.getConnection();) {
	    VehicleTypesGateway vtg =
		    PersistenceFactory.getVehicleTypesGateway();
	    c.setAutoCommit(false);
	    vtg.setConnection(c);

	    // there is percentage devoted to a non existing vehicle type
	    for (Long key : course.percentages.keySet()) {
		if (vtg.findById(key) == null) {
		    throw new BusinessException(
			    "Existen porcentajes asociados a vehiculos que no existen");
		}
	    }

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	}

	Map<Long, Integer> dedications = course.percentages;

	// there are no dedications specified
	if (dedications == null) {
	    throw new BusinessException(
		    "No se han especificado los vehiculos dedicados");
	}
	if (dedications.isEmpty()) {
	    throw new BusinessException(
		    "No se han especificado los vehiculos dedicados");
	}

	int suma = 0;
	for (Long key : dedications.keySet()) {
	    // the are any dedication with an invalid percentage (empty)
	    if (dedications.get(key) == null) {
		throw new BusinessException(
			"Hay procentajes mal definidos (Vacio)");
	    }
	    // the are any dedication with an invalid percentage (zero, negative)
	    int dedication = dedications.get(key);
	    if (dedication <= 0) {
		throw new BusinessException(
			"Hay procentajes mal definidos (cero o negativo)");

	    }

	    suma += dedications.get(key);
	}
	// the sum of devoted percentages does not equals 100%
	if (suma != 100) {
	    throw new BusinessException(
		    "El porcentaje total debe ser del 100%");
	}

    }

}
