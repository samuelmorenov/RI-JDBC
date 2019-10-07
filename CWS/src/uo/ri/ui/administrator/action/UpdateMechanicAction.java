package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.MechanicCrudServiceImpl;
import uo.ri.common.BusinessException;

public class UpdateMechanicAction implements Action {



	@Override
	public void execute() throws BusinessException {
		MechanicDto mechanic =  new MechanicDto();
		// Get info
		mechanic.id = Console.readLong("Type mechahic id to update"); 
		mechanic.name = Console.readString("Name"); 
		mechanic.surname = Console.readString("Surname");
		
		MechanicCrudService mcs = new MechanicCrudServiceImpl();
		mcs.updateMechanic(mechanic);
		
		// Print result
		Console.println("Mechanic updated");
	}

}