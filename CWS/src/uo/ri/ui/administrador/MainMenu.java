package uo.ri.ui.administrador;

import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { { "Administrador", null }, { "Gestión de mecánicos", MecanicosMenu.class } };
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
