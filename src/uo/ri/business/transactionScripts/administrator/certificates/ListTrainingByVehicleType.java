package uo.ri.business.transactionScripts.administrator.certificates;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.DedicationDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.DedicationsGateway;
import uo.ri.persistence.EnrollmentGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.VehicleTypesGateway;

public class ListTrainingByVehicleType {

    public ListTrainingByVehicleType() {

    }

    public List<TrainingHoursRow> execute() {
	try (Connection c = Jdbc.getConnection();) {

	    /////// Inicialización de listas

	    c.setAutoCommit(false);

	    // Obtencion de las Gateways que se van a usar
	    VehicleTypesGateway vtg =
		    PersistenceFactory.getVehicleTypesGateway();
	    MechanicGateway mg = PersistenceFactory.getMechanicGateway();
	    EnrollmentGateway eg = PersistenceFactory.getEnrollmentGateway();
	    DedicationsGateway dg =
		    PersistenceFactory.getDedicationsGateway();
	    CourseGateway cog = PersistenceFactory.getCourseGateway();

	    // Se establece la conexion de todas las gateways

	    vtg.setConnection(c);
	    mg.setConnection(c);
	    eg.setConnection(c);
	    dg.setConnection(c);
	    cog.setConnection(c);

	    // Se obtienen las listas de los datos que se van a usar
	    List<VehicleTypeDto> allVehicleTypes = vtg.findAll();
	    List<MechanicDto> allMechanics = mg.findAll();
	    List<EnrollmentDto> allEnrollments = eg.findAll();
	    List<DedicationDto> allDedications = dg.findAll();
	    List<CourseDto> allCourses = cog.findAll();

	    List<TrainingHoursRow> list = new ArrayList<TrainingHoursRow>();

	    for (VehicleTypeDto vehicleType : allVehicleTypes) {
		for (MechanicDto mechanic : allMechanics) {
		    TrainingHoursRow tfr = new TrainingHoursRow();
		    tfr.mechanicFullName =
			    mechanic.name + " " + mechanic.surname;
		    tfr.vehicleTypeName = vehicleType.name;
		    tfr.enrolledHours = TrainingHours.Calculate(
			    allEnrollments, allDedications, allCourses,
			    vehicleType, mechanic);

		    // Aclaracion: no se especifica si se tienen que mostrar en caso de que sean 0
		    // de ser asi comentar la siguiente linea
		    if (tfr.enrolledHours > 0)
			list.add(tfr);

		}
	    }

	    c.commit();
	    return list;

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }
}
