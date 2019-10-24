package uo.ri.ui.administrator;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.action.GenerateCertificatesAction;

public class TrainingMenu extends BaseMenu {
	public TrainingMenu() {
		menuOptions = new Object[][] { { "Manager > Training management", null },
				{ "Generacion de Certificados", GenerateCertificatesAction.class } };
	}
}
