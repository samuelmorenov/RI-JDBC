package uo.ri.ui.administrator.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.MechanicCrudService;
import uo.ri.business.impl.MechanicCrudServiceImpl;
import uo.ri.common.BusinessException;

public class DeleteMechanicAction implements Action {


	@Override
	public void execute() throws BusinessException {
		Long idMechanic = Console.readLong("Type mechanic id "); 
		
		MechanicCrudService mcs = new MechanicCrudServiceImpl();
		mcs.deleteMechanic(idMechanic);
		
		Console.println("Mechanic deleted");
	}

}
