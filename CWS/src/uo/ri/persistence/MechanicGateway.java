package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {

	void setConnection(Connection c) throws SQLException;

	void add(MechanicDto mechanic);

	void delete(Long id);

	void update(MechanicDto mechanic);

	List<MechanicDto> findAll();

	MechanicDto findByDNI(String dni);

	MechanicDto findById(Long idMechanic);
}
