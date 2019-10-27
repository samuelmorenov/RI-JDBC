package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class ForemanMain extends BaseMenu {

	public ForemanMain() {
		menuOptions = new Object[][] { 
			{ "Foreman", null },
			{ "Client reception ", 		NotYetImplementedAction.class }, 
			{ "Client management", 		NotYetImplementedAction.class },
			{ "Vehicle management", 	NotYetImplementedAction.class },
			{ "Client history review", 	NotYetImplementedAction.class }, 
		};
	}

	public static void main(String[] args) {
		new ForemanMain().execute();
	}

}
