package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.InvoiceGateway;

public class InvoiceGatewayImpl extends GatewayImpl implements InvoiceGateway {

	@Override
	public void updateWorkOrderStatus(List<Long> breakdownIds, String status) {

		PreparedStatement pst = null;
		try {
			String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_STATUS");
			pst = c.prepareStatement(SQL);

			for (Long breakdownId : breakdownIds) {
				pst.setString(1, status);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void linkWorkorderInvoice(long invoiceId, List<Long> workOrderIDS) {

		PreparedStatement pst = null;
		try {
			String SQL = Conf.getInstance().getProperty("SQL_WORKORDER_INVOICE_ASSOC");
			pst = c.prepareStatement(SQL);

			for (Long breakdownId : workOrderIDS) {
				pst.setLong(1, invoiceId);
				pst.setLong(2, breakdownId);

				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) {

		PreparedStatement pst = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_INSERT_INVOICE");
			pst = c.prepareStatement(SQL);
			pst.setLong(1, numberInvoice);
			pst.setDate(2, new java.sql.Date(dateInvoice.getTime()));
			pst.setDouble(3, vat);
			pst.setDouble(4, total);
			pst.setString(5, "NOT_YET_PAID");

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long getGeneratedKey(long numberInvoice) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_RETRIEVE_GENERATED_KEY");
			pst = c.prepareStatement(SQL);
			pst.setLong(1, numberInvoice);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public long generateInvoiceNumber() {
		Statement st = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_LAST_INVOICE_NUMBER");
			st = c.createStatement();
			rs = st.executeQuery(SQL);

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, st);
		}
	}

	@Override
	public void updateWorkorderTotal(Long workOrderID, double total) {
		PreparedStatement pst = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_UPDATE_WORKORDER_AMOUNT");
			pst = c.prepareStatement(SQL);
			pst.setDouble(1, total);
			pst.setLong(2, workOrderID);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public double checkTotalParts(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_PARTS_TOTAL");
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // There is no part replaced in this breakdown
			}

			return rs.getDouble(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public double checkTotalLabor(Long workOrderID) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_LABOR_TOTAL");
			pst = c.prepareStatement(SQL);
			pst.setLong(1, workOrderID);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new RuntimeException("Workorder does not exist or it can not be charged");
			}

			return rs.getDouble(1);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
