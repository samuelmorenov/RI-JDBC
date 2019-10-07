package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.AddMechanic;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {

		MechanicDto mechanic = new MechanicDto();

		// Get info
		mechanic.dni = Console.readString("Dni");
		mechanic.name = Console.readString("Name");
		mechanic.surname = Console.readString("Surname");

		AddMechanic am = new AddMechanic(mechanic);
		am.execute();

		// Print result
		Console.println("Mechanic added");
	}

}
