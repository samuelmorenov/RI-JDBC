package uo.ri.business.transactionScripts.administrator;

import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

public class AddMechanic {

	private MechanicDto mechanic;

	public AddMechanic(MechanicDto mechanic) {
		this.mechanic = mechanic;
	}

	public void execute() throws BusinessException {
		MechanicGateway mg = new MechanicGatewayImpl();
		if (mg.findByDNI(mechanic.dni) != null)
			throw new BusinessException("Ya existe un mecanico con ese DNI");
		mg.add(mechanic);
	}
}
