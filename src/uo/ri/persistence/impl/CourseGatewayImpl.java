package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.CourseDto;
import uo.ri.conf.Conf;
import uo.ri.persistence.CourseGateway;

public class CourseGatewayImpl extends GatewayImpl implements CourseGateway {

	@Override
	public void add(CourseDto course) {
		// Process
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_INSERT_COURSES");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, course.code);
			pst.setString(2, course.description);
			pst.setDate(3, new java.sql.Date((course.endDate).getTime()));
			pst.setInt(4, course.hours);
			pst.setString(5, course.name);
			pst.setDate(6, new java.sql.Date((course.startDate).getTime()));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long findLastId() {
		Statement st = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_LAST_ID_COURSES");
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
	}

	@Override
	public void delete(Long id) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_DELETE_COURSE");

		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public CourseDto findById(Long cId) {
		CourseDto course = null;

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		String SQL = Conf.getInstance().getProperty("SQL_FIND_COURSE_BY_ID");

		try {
			c = Jdbc.getConnection();
			pst = c.prepareStatement(SQL);
			pst.setLong(1, cId);
			rs = pst.executeQuery();

			if (rs.next() == false) {
				return course;
			}

			course = new CourseDto();
			course.id = rs.getLong("id");
			course.code = rs.getString("code");
			course.description = rs.getString("description");
			course.endDate = rs.getDate("enddate");
			course.hours = rs.getInt("hours");
			course.name = rs.getString("name");
			course.startDate = rs.getDate("startdate");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
		return course;
	}

	@Override
	public List<CourseDto> findAll() {
		List<CourseDto> courses = null;
		CourseDto course = null;
		Statement st = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_FIND_ALL_COURSES");

		try {
			st = c.createStatement();
			rs = st.executeQuery(SQL);
			courses = new ArrayList<CourseDto>();
			while (rs.next()) {
				course = new CourseDto();
				course.id = rs.getLong("id");
				course.code = rs.getString("code");
				course.description = rs.getString("description");
				course.endDate = rs.getDate("enddate");
				course.hours = rs.getInt("hours");
				course.name = rs.getString("name");
				course.startDate = rs.getDate("startdate");
				courses.add(course);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
		return courses;
	}

	@Override
	public void update(CourseDto dto) {
		PreparedStatement pst = null;
		String SQL = Conf.getInstance().getProperty("SQL_UPDATE_COURSE");
		try {
			pst = c.prepareStatement(SQL);
			pst.setString(1, dto.code);
			pst.setString(2, dto.description);
			pst.setDate(3, new java.sql.Date((dto.endDate).getTime()));
			pst.setInt(4, dto.hours);
			pst.setString(5, dto.name);
			pst.setDate(6, new java.sql.Date((dto.startDate).getTime()));
			pst.setLong(7, dto.id);

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public int getEnrolledMechanics(Long CourseId) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String SQL = Conf.getInstance().getProperty("SQL_ENROLLED_MECHANICS");
		try {
			pst = c.prepareStatement(SQL);
			pst.setLong(1, CourseId);
			rs = pst.executeQuery();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
