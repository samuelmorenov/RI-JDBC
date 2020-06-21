package uo.ri.business.transactionScripts.administrator.certificates;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.DedicationDto;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificatesGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.DedicationsGateway;
import uo.ri.persistence.EnrollmentGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.VehicleTypesGateway;

public class GenerateCertificates {

    /**
     * Generates certificates according to the rules: - Each vehicle type specifies
     * the number of attended-and-passed training hours needed to get the
     * certificate for that vehicle type
     * 
     * - The mechanic has to accumulate at least that number of hours in one or
     * several courses
     * 
     * - A course specifies the % of training hours devoted to some vehicle types
     * 
     * @return the number of new certificates generated DOES NOT @throws
     *         BusinessException
     */
    public Integer execute() {
	int generated = 0;

	try (Connection c = Jdbc.getConnection();) {

	    /////// Inicialización de listas

	    c.setAutoCommit(false);

	    // Obtencion de las Gateways que se van a usar
	    CertificatesGateway cg =
		    PersistenceFactory.getCertificatesGateway();
	    VehicleTypesGateway vtg =
		    PersistenceFactory.getVehicleTypesGateway();
	    MechanicGateway mg = PersistenceFactory.getMechanicGateway();
	    EnrollmentGateway eg = PersistenceFactory.getEnrollmentGateway();
	    DedicationsGateway dg =
		    PersistenceFactory.getDedicationsGateway();
	    CourseGateway cog = PersistenceFactory.getCourseGateway();

	    // Se establece la conexion de todas las gateways
	    cg.setConnection(c);
	    vtg.setConnection(c);
	    mg.setConnection(c);
	    eg.setConnection(c);
	    dg.setConnection(c);
	    cog.setConnection(c);

	    // Se obtienen las listas de los datos que se van a usar
	    List<CertificateDto> allCertificates = cg.findAll();
	    List<VehicleTypeDto> allVehicleTypes = vtg.findAll();
	    List<MechanicDto> allMechanics = mg.findAll();
	    List<EnrollmentDto> allEnrollments = eg.findAll();
	    List<DedicationDto> allDedications = dg.findAll();
	    List<CourseDto> allCourses = cog.findAll();
	    List<CertificateDto> CertificatesToGenerate =
		    new ArrayList<CertificateDto>();

	    /////// Calculo de certificados

	    // Se recorren todos los mecanicos para cada tipo de vehiculo
	    for (MechanicDto mechanic : allMechanics) {
		for (VehicleTypeDto vehicleType : allVehicleTypes) {
		    boolean existe = false;
		    for (CertificateDto certificate : allCertificates) {
			if (certificate.mechanicId == mechanic.id
				&& certificate.vehicleTypeId
					== vehicleType.id) {
			    existe = true;
			}
		    }

		    // Si el certificado no existe
		    if (!existe) {
			int trainingHours = TrainingHours.Calculate(
				allEnrollments, allDedications, allCourses,
				vehicleType, mechanic);

			// Y si el numero de horas es suficiente
			if (trainingHours >= vehicleType.minTrainigHours) {

			    // Se genera el certificado
			    CertificateDto newCertificate =
				    new CertificateDto();
			    newCertificate.mechanicId = mechanic.id;
			    newCertificate.vehicleTypeId = vehicleType.id;
			    newCertificate.obtainedAt = new Date();
			    CertificatesToGenerate.add(newCertificate);
			}
		    }
		}
	    }

	    if (CertificatesToGenerate.size() > 0) {
		generated = cg.insertCertificates(CertificatesToGenerate);
	    }

	    c.commit();

	} catch (SQLException e) {
	    Err.transactionScripts(e);
	}
	return generated;
    }

}