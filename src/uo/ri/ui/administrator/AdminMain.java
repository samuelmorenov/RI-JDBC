package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.mechanics.MechanicsMenu;
import uo.ri.ui.administrator.training.TrainingMenu;

public class AdminMain extends BaseMenu {

	public AdminMain() {
		menuOptions = new Object[][] { 
			{ "Administrator", null },
			{ "Mechanics management", 		MechanicsMenu.class }, 
			{ "Spare parts management",		NotYetImplementedAction.class  },
			{ "Vehicle types management", 	NotYetImplementedAction.class  },
			{ "Training management", 		TrainingMenu.class },
		};
	}

	public static void main(String[] args) {
		new AdminMain().execute();
	}

}