package uo.ri.business.transactionScripts.administrator.vehicleTypes;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.conf.Err;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.VehicleTypesGateway;

public class FindAllVehicleTypes {

    public FindAllVehicleTypes() {

    }

    public List<VehicleTypeDto> execute() {
	try (Connection c = Jdbc.getConnection();) {
	    VehicleTypesGateway vtg =
		    PersistenceFactory.getVehicleTypesGateway();
	    c.setAutoCommit(false);
	    vtg.setConnection(c);
	    List<VehicleTypeDto> aux = vtg.findAll();
	    c.commit();
	    return aux;
	} catch (SQLException e) {
	    Err.transactionScripts(e);
	    return null;
	}
    }

}
