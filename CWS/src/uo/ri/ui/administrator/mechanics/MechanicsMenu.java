package uo.ri.ui.administrator.mechanics;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.mechanics.action.AddMechanicAction;
import uo.ri.ui.administrator.mechanics.action.DeleteMechanicAction;
import uo.ri.ui.administrator.mechanics.action.ListMechanicsAction;
import uo.ri.ui.administrator.mechanics.action.UpdateMechanicAction;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Mechanics management", null},
			
			{ "Add mechanic", 				AddMechanicAction.class }, 
			{ "Update mechanic", 	UpdateMechanicAction.class }, 
			{ "Delete mechanic", 				DeleteMechanicAction.class }, 
			{ "List mechanics", 				ListMechanicsAction.class },
		};
	}

}
