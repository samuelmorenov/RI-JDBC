package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.action.*;

public class MechanicsMenu extends BaseMenu {

	public MechanicsMenu() {
		menuOptions = new Object[][] {

				{ "Añadir Mecanico", AddMechanicAction.class },

				{ "Borrar Mecanico", DeleteMechanicAction.class },

				{ "Listar Mecanicos", ListMechanicsAction.class },

				{ "Actualizar Mecacnico", UpdateMechanicAction.class }

		};

	}

}