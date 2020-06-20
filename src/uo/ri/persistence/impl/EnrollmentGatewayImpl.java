package uo.ri.persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.EnrollmentDto;
import uo.ri.conf.Conf;
import uo.ri.conf.Err;
import uo.ri.persistence.EnrollmentGateway;

public class EnrollmentGatewayImpl extends GatewayImpl
	implements EnrollmentGateway {

    @Override
    public List<EnrollmentDto> findAll() {
	List<EnrollmentDto> list = null;
	EnrollmentDto dto = null;
	Statement st = null;
	ResultSet rs = null;
	String SQL =
		Conf.getInstance().getProperty("SQL_FIND_ALL_ENROLLMENTS");

	try {
	    st = c.createStatement();
	    rs = st.executeQuery(SQL);
	    list = new ArrayList<EnrollmentDto>();
	    while (rs.next()) {
		dto = new EnrollmentDto();
		dto.id = rs.getLong("ID");
		dto.attendance = rs.getInt("ATTENDANCE");
		dto.passed = rs.getBoolean("PASSED");
		dto.courseId = rs.getLong("COURSE_ID");
		dto.mechanicId = rs.getLong("MECHANIC_ID");
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
