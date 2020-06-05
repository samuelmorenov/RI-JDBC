package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CertificateDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CertificatesGateway;

public class CertificatesGatewayImpl extends GatewayImpl implements CertificatesGateway {

	@Override
	public List<CertificateDto> getAllPossibleCertificates() {
		List<CertificateDto> list = null;
		CertificateDto certificate = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_ALL_POSSIBLE_CERTIFICATES");
		try {
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();
			list = new ArrayList<CertificateDto>();
			while (rs.next()) {

				certificate = new CertificateDto();
				certificate.mechanic = rs.getLong("M_ID");
				certificate.vehicleType = rs.getLong("V_ID");
				list.add(certificate);

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return list;
	}

	@Override
	public CertificateDto findCertificate(Long mechanicId, Long vehicleTypeId) {
		CertificateDto certificate = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATE");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanicId);
			pst.setLong(2, vehicleTypeId);
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return certificate;
			}
			certificate = new CertificateDto();
			certificate.id = rs.getLong("id");
			certificate.mechanic = mechanicId;
			certificate.vehicleType = vehicleTypeId;
			certificate.obtainedAt = rs.getDate("date");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return certificate;
	}

	@Override
	public void addCertificate(CertificateDto certificado) {
		// Process
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_CERTIFICATE");
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, certificado.mechanic);
			pst.setLong(2, certificado.vehicleType);
			java.sql.Date sqlDate = new java.sql.Date(certificado.obtainedAt.getTime());
			pst.setDate(3, sqlDate);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<CertificateDto> getCertificatesByVehicleTypeId(Long id) {
		List<CertificateDto> list = null;
		CertificateDto certificate = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATESSELECT_BY_VEHICLETYPE");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			list = new ArrayList<CertificateDto>();
			while (rs.next()) {
				certificate = new CertificateDto();
				certificate.id = rs.getLong("ID");
				certificate.obtainedAt = rs.getDate("DATE");
				certificate.mechanic = rs.getLong("MECHANIC_ID");
				certificate.vehicleType = rs.getLong("VEHICLETYPE_ID");
				list.add(certificate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return list;
	}

}
