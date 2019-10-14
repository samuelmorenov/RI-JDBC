package uo.ri.business.transactionScripts.administrator;

import uo.ri.business.dto.MechanicDto;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

public class AddMechanic {
	

	private MechanicDto mechanic;

	public AddMechanic(MechanicDto mechanic) {
		this.mechanic = mechanic;
	}

	public void execute() {
		MechanicGateway mg = new MechanicGatewayImpl();
		mg.add(mechanic);
	}
}
