package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.action.AssignWorkOrderAction;
import uo.ri.ui.foreman.action.ListCertifiedMechanicsAction;
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
			{"List certified mechanics",	ListCertifiedMechanicsAction.class }, 
			{"Assign a work order",  	 	AssignWorkOrderAction.class },
		};
	}

}
