package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.MechanicDto;

public interface MechanicGateway {

	void add(MechanicDto mechanic);
	void delete(Long id);
	void update(MechanicDto mechanic);
	List<MechanicDto> findAll();
	
	MechanicDto findByDNI(String dni);
	MechanicDto findById(Long idMechanic);
}
