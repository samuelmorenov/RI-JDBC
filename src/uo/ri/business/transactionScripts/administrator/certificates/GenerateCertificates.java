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
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.CertificatesGateway;
import uo.ri.persistence.CourseGateway;
import uo.ri.persistence.DedicationsGateway;
import uo.ri.persistence.EnrollmentGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.VehicleTypesGateway;

public class GenerateCertificates {

	public Integer execute() throws BusinessException {
		int generated = 0;

		try (Connection c = Jdbc.getConnection();) {

			CertificatesGateway cg = PersistenceFactory.getCertificatesGateway();
			VehicleTypesGateway vtg = PersistenceFactory.getVehicleTypesGateway();
			MechanicGateway mg = PersistenceFactory.getMechanicGateway();
			EnrollmentGateway eg = PersistenceFactory.getEnrollmentGateway();
			DedicationsGateway dg = PersistenceFactory.getDedicationsGateway();
			CourseGateway cog = PersistenceFactory.getCourseGateway();

			c.setAutoCommit(false);
			
			cg.setConnection(c);
			vtg.setConnection(c);
			mg.setConnection(c);
			eg.setConnection(c);
			dg.setConnection(c);
			cog.setConnection(c);

			List<CertificateDto> CertificatesToGenerate = getAllPossibleCertificates(
					cg.findAll(), 
					vtg.findAll(), 
					mg.findAll(), 
					eg.findAll(), 
					dg.findAll(), 
					cog.findAll());

			if (CertificatesToGenerate.size() > 0) {
				generated = cg.insertCertificates(CertificatesToGenerate);
			}
			
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		return generated;
	}

	private List<CertificateDto> getAllPossibleCertificates(
			List<CertificateDto> allCertificates,
			List<VehicleTypeDto> allVehicleTypes, 
			List<MechanicDto> allMechanics, 
			List<EnrollmentDto> allEnrollments,
			List<DedicationDto> allDedications, 
			List<CourseDto> allCourses) {
		List<CertificateDto> CertificatesToGenerate = new ArrayList<CertificateDto>();

		// Se recorren todos los mecanicos para cada tipo de vehiculo
		for (MechanicDto mechanic : allMechanics) {
			for (VehicleTypeDto vehicleType : allVehicleTypes) {
				boolean existe = false;
				for (CertificateDto certificate : allCertificates) {
					if (certificate.mechanicId == mechanic.id && 
							certificate.vehicleTypeId == vehicleType.id) {
						existe = true;
					}
				}
				// Si el certificado no existe
				if (!existe) {
					int trainingHours = 0;
					for (EnrollmentDto enrollment : allEnrollments) {
						if (mechanic.id == enrollment.mechanicId) {
							// Solo si se ha pasado
							if (enrollment.passed) {
								for (CourseDto course : allCourses) {
									if (course.id == enrollment.courseId) {
										for (DedicationDto dedication : allDedications) {
											if (vehicleType.id == dedication.vehicleTyeId) {
												trainingHours += course.hours * dedication.percentage;
											}
										}
									}
								}
							}
						}
					}

					// Y si el numero de horas es suficiente
					if (trainingHours >= vehicleType.minTrainigHours) {

						// Se genera el certificado
						CertificateDto newCertificate = new CertificateDto();
						newCertificate.mechanicId = mechanic.id;
						newCertificate.vehicleTypeId = vehicleType.id;
						newCertificate.obtainedAt = new Date();
						CertificatesToGenerate.add(newCertificate);
					}
				}
			}
		}
		return CertificatesToGenerate;
	}
}