package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;

public class AdminMain extends BaseMenu {

	public AdminMain() {
		menuOptions = new Object[][] { 
			{ "Administrator", null },
			{ "Mechanics management", 			MechanicsMenu.class }, 
			{ "Spare parts management", 			SparesMenu.class },
			{ "Vehicle types management", 	VehicleTypeMenu.class },
			{ "Training management", 		TrainingMenu.class },
		};
	}

	public static void main(String[] args) {
		new AdminMain().execute();
	}

}