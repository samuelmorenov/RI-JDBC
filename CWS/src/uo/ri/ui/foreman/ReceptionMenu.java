package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.foreman.action.AssignWorkOrderAction;
import uo.ri.ui.foreman.action.RegisterWorkOrderAction;
import uo.ri.ui.foreman.action.RemoveWorkOrderAction;
import uo.ri.ui.foreman.action.UpdateWorkOrderAction;

public class ReceptionMenu extends BaseMenu {

	public ReceptionMenu() {
		menuOptions = new Object[][] { 
			{"Foreman > Vehicle reception", null},
			
			{"Register a work order", 	 	RegisterWorkOrderAction.class }, 
			{"Update a work order", 	 	UpdateWorkOrderAction.class },
			{"Remove a work order", 	 	RemoveWorkOrderAction.class },
			{"", null},
			{"View work order detail", 	 	NotYetImplementedAction.class },
			{"Assign a work order",  	 	AssignWorkOrderAction.class },
		};
	}

}