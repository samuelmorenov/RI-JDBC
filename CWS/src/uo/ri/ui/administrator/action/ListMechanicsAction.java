package uo.ri.ui.administrator.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.administrator.ListMechanics;
import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;
import uo.ri.ui.util.Printer;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nList of mechanics \n");
		List<MechanicDto> mechanics = null;
		ListMechanics lm = new ListMechanics();
		mechanics = lm.execute();

		// Print result
		for (MechanicDto m : mechanics) {
			Printer.printMechanic(m);
		}
	}
}
