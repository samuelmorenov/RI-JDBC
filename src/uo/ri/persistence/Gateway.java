package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;

public interface Gateway {
    void setConnection(Connection c) throws SQLException;
}
