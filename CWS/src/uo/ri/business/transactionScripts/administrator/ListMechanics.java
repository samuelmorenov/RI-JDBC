package uo.ri.business.transactionScripts.administrator;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

public class ListMechanics {

	public List<MechanicDto> execute() {

		MechanicGateway mg = new MechanicGatewayImpl();
		return mg.findAll();
	}
}
