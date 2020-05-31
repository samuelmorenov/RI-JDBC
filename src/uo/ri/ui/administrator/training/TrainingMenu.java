package uo.ri.ui.administrator.training;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.administrator.training.action.GenerateCertificatesAction;
import uo.ri.ui.administrator.training.curse.CourseCrudMenu;

public class TrainingMenu extends BaseMenu {

	public TrainingMenu() {
		menuOptions = new Object[][] { { "Manager > Training management", null },

				{ "Course management", 			CourseCrudMenu.class },
				{ "Attendance registration", 	NotYetImplementedAction.class }, // TO-DO AttendanceMenu.class },
				{ "Reports", 					ReportsMenu.class }, 
				{ "", null },
				{ "Certificate generation", 	GenerateCertificatesAction.class },

		};
	}

}
