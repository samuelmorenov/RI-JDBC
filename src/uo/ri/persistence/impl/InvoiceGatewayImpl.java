package uo.ri.persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.InvoiceGateway;

public class InvoiceGatewayImpl extends GatewayImpl implements InvoiceGateway {

	@Override
	public void testRepairs(List<Long> workOrderIDS) {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_CHECK_WORKORDER_STATUS");
			pst = c.prepareStatement(SQL);

			for (Long workOrderID : workOrderIDS) {
				pst.setLong(1, workOrderID);

				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new RuntimeException("Workorder " + workOrderID + " doesn't exist");
				}

				String status = rs.getString(1);
				if (!"FINISHED".equalsIgnoreCase(status)) {
					throw new RuntimeException("Workorder " + workOrderID + " is not finished yet");
				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
		}

	}

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
			throw new RuntimeException(e); //TODO: Comprobar si hay que hacer esto
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long createInvoice(long numberInvoice, Date dateInvoice, double vat, double total) {

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

			// TODO: Esto habria que hacerlo fuera de la parte de persistencia
			// TODO: Ver si el numberInvoice es unico o no
			return getGeneratedKey(numberInvoice); // New invoice id

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(pst);
		}
	}

	private long getGeneratedKey(long numberInvoice) {
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
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			String SQL = Conf.getInstance().getProperty("SQL_LAST_INVOICE_NUMBER");
			pst = c.prepareStatement(SQL);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, next
			} else { // there is none yet
				return 1L;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(rs, pst);
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
				// TODO_ Lanzas BusinessException en persistencia (testRepairs).
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