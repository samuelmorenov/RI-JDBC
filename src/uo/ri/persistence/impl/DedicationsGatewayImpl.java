package uo.ri.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.DedicationDto;
import uo.ri.conf.Conf;
import uo.ri.conf.Err;
import uo.ri.persistence.DedicationsGateway;

public class DedicationsGatewayImpl extends GatewayImpl implements DedicationsGateway {

	@Override
	public List<DedicationDto> findAll() {
		List<DedicationDto> list = null;
		DedicationDto dto = null;
		Statement st = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_DEDICATIONS");

		try {
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			list = new ArrayList<DedicationDto>();
			while (rs.next()) {
				dto = new DedicationDto();
				dto.id = rs.getLong("ID");
				dto.percentage = rs.getInt("PERCENTAGE");
				dto.courseId = rs.getLong("COURSE_ID");
				dto.vehicleTyeId = rs.getLong("VEHICLETYPE_ID");
				list.add(dto);
			}
		} catch (SQLException e) {
			Err.persistence(e);
		} finally {
			Jdbc.close(rs, st);
		}
		return list;
	}

}
