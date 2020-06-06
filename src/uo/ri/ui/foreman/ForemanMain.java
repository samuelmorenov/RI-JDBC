package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;

public class ForemanMain extends BaseMenu {

	public ForemanMain() {
		menuOptions = new Object[][] { 
			{ "Foreman", null },
			{ "Client reception ", 		ReceptionMenu.class }, 
			//{ "Client management", 		NotYetImplementedAction.class },
			//{ "Vehicle management", 	NotYetImplementedAction.class },
			//{ "Client history review", 	NotYetImplementedAction.class }, 
		};
	}

	public static void main(String[] args) {
		new ForemanMain().execute();
	}

}
