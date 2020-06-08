package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				certificate.mechanicId = rs.getLong("M_ID");
				certificate.vehicleTypeId = rs.getLong("V_ID");
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

		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATE");

		try {

			pst = c.prepareStatement(SQL);
			pst.setLong(1, mechanicId);
			pst.setLong(2, vehicleTypeId);
			rs = pst.executeQuery();
			if (rs.next() == false) {

				return certificate;
			}
			certificate = new CertificateDto();
			certificate.id = rs.getLong("id");
			certificate.mechanicId = mechanicId;
			certificate.vehicleTypeId = vehicleTypeId;
			certificate.obtainedAt = rs.getDate("date");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return certificate;
	}

	@Override
	public List<CertificateDto> getCertificatesByVehicleTypeId(Long id) {
		List<CertificateDto> list = null;
		CertificateDto certificate = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_CERTIFICATESSELECT_BY_VEHICLETYPE");

		try {

			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			rs = pst.executeQuery();
			list = new ArrayList<CertificateDto>();
			while (rs.next()) {
				certificate = new CertificateDto();
				certificate.id = rs.getLong("ID");
				certificate.obtainedAt = rs.getDate("DATE");
				certificate.mechanicId = rs.getLong("MECHANIC_ID");
				certificate.vehicleTypeId = rs.getLong("VEHICLETYPE_ID");
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

	@Override
	public List<CertificateDto> findAll() {
		List<CertificateDto> list = null;
		CertificateDto dto = null;
		Statement st = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_CERTIFICATES");

		try {
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			list = new ArrayList<CertificateDto>();
			while (rs.next()) {
				dto = new CertificateDto();
				dto.id = rs.getLong("ID");
				dto.obtainedAt = rs.getDate("DATE");
				dto.mechanicId = rs.getLong("MECHANIC_ID");
				dto.vehicleTypeId = rs.getLong("VEHICLETYPE_ID");
				list.add(dto);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
		return list;
	}

	@Override
	public void addCertificate(CertificateDto certificado) {
		// Process
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_CERTIFICATE");
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, certificado.mechanicId);
			pst.setLong(2, certificado.vehicleTypeId);
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
	public int insertCertificates(List<CertificateDto> certificateList) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_CERTIFICATE");
		try {
        	pst = c.prepareStatement(SQL);
            int[] count;
            for (CertificateDto certificado : certificateList) {
    			pst.setLong(1, certificado.mechanicId);
    			pst.setLong(2, certificado.vehicleTypeId);
    			java.sql.Date sqlDate = new java.sql.Date(certificado.obtainedAt.getTime());
    			pst.setDate(3, sqlDate);
                pst.addBatch();
            }
            count = pst.executeBatch();
            return count.length;
            
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}
}
