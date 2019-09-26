package uo.ri.ui.administrador;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrador.action.AddMechanicAction;
import uo.ri.ui.administrador.action.DeleteMechanicAction;
import uo.ri.ui.administrador.action.ListMechanicsAction;
import uo.ri.ui.administrador.action.UpdateMechanicAction;

public class MecanicosMenu extends BaseMenu {

	public MecanicosMenu() {
		menuOptions = new Object[][] { { "Administrador > Gestión de mecánicos", null },
				{ "Añadir mecánico", AddMechanicAction.class },
				{ "Modificar datos de mecánico", UpdateMechanicAction.class },
				{ "Eliminar mecánico", DeleteMechanicAction.class },
				{ "Listar mecánicos", ListMechanicsAction.class }, };
	}
}
