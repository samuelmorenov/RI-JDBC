package uo.ri.business.transactionScripts.administrator.certificates;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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

			cg.setConnection(c);
			vtg.setConnection(c);
			mg.setConnection(c);
			eg.setConnection(c);
			dg.setConnection(c);

			c.setAutoCommit(false);

			// Listas de todos los elementos de las tablas que se necesitan para calcular
			// los certificados que se han de generar
			List<CertificateDto> ac = cg.findAll();
			List<VehicleTypeDto> avt = vtg.findAll();
			List<MechanicDto> am = mg.findAll();
			List<EnrollmentDto> ae = eg.findAll();
			List<DedicationDto> ad = dg.findAll();

			// Lista de los certificados a generar:
			List<CertificateDto> c2g = getAllPossibleCertificates(ac, avt, am, ae, ad);

			for (CertificateDto cdto : c2g) {
				// TODO: La generación debería ser atómica y al no usar una misma transacción
				// para todas las operaciones que la forman te expones a que se produzcan
				// anomalías.
				cg.addCertificate(cdto);
			}
			c.commit();

		} catch (SQLException e) {
			throw new RuntimeException("Error de conexion");
		}
		return generated;
	}

	private List<CertificateDto> getAllPossibleCertificates(List<CertificateDto> allCertificates,
			List<VehicleTypeDto> allVehicleTypes, List<MechanicDto> allMechanics, List<EnrollmentDto> allEnrollments,
			List<DedicationDto> allDedications) {

		List<CertificateDto> CertificatesToGenerate = new ArrayList<CertificateDto>();

		// Se recorren todos los mecanicos para cada tipo de vehiculo
		for (VehicleTypeDto vehicleType : allVehicleTypes) {
			for (MechanicDto mechanic : allMechanics) {

				boolean existe = false;
				for (CertificateDto certificate : allCertificates) {
					if (certificate.mechanicId == mechanic.id && certificate.vehicleTypeId == vehicleType.id) {
						existe = true;
					}
				}
				// Si el certificado no existe
				if (!existe) {

					int trainingHours = 0;
					for (EnrollmentDto enrollment : allEnrollments) {

						if (mechanic.id == enrollment.mechanic.id) {

							// Solo si se ha pasado
							if (enrollment.passed) {
								CourseDto curse = enrollment.course;
								for (DedicationDto dedication : allDedications) {
									if (vehicleType.id == dedication.vehicleTyeId) {
										trainingHours += curse.hours * dedication.percentage;
									}
								}
							}
						}
					}

					// Y si el numero de horas es suficiente
					if (trainingHours >= vehicleType.minTrainigHours) {

						// Se genera el certificado
						CertificateDto certificateToGenerate = new CertificateDto();
						certificateToGenerate.mechanicId = mechanic.id;
						certificateToGenerate.vehicleTypeId = vehicleType.id;
						CertificatesToGenerate.add(certificateToGenerate);
					}
				}

			}
		}
		return CertificatesToGenerate;
	}

}

//	public int execute() throws BusinessException {
//	int certificadosGenerados = 0;
//
//	// Has introducido parte de la lógica de negocio de la generación de
//	// certificados, que debería estar en el TS, en una consulta SQL
//	// (SQL_ALL_POSSIBLE_CERTIFICATES especialmente). Esto genera problemas de
//	// escalabilidad, mantenibilidad y legibilidad. Si cambias de base de datos vas
//	// a tener que adaptar si o si el TS ya que esa operación depende casi por
//	// completo de la consulta. Además rompes el principio básico del uso del patrón
//	// TDG. A veces es mejor sacrificar un poco de rendimiento para mejorar el
//	// diseño.
//
//	// La generación debería ser atómica y al no usar una misma transacción
//	// para todas las operaciones que la forman te expones a que se produzcan
//	// anomalías.
//
//	try (Connection c = Jdbc.getConnection();) {
//		CertificatesGateway cg = PersistenceFactory.getCertificatesGateway(); // Factoria
//		c.setAutoCommit(false);
//		cg.setConnection(c);
//		List<CertificateDto> list;
//		list = cg.getAllPossibleCertificates(); // Llamada al add mecanico de la persistencia
//		for (CertificateDto e : list) {
//			if (cg.findCertificate(e.mechanic, e.vehicleType) == null) {
//				e.obtainedAt = new Date();
//				cg.addCertificate(e);
//				certificadosGenerados++;
//			}
//		}
//		c.commit();
//	} catch (SQLException e) {
//		throw new RuntimeException("Error de conexion");
//	}
//	return certificadosGenerados;
//}
