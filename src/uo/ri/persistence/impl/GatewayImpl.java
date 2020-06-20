package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.persistence.Gateway;

public class GatewayImpl implements Gateway {

    protected Connection c;

    @Override
    public void setConnection(Connection c) throws SQLException {
	this.c = c;
    }

}
