package uo.ri.ui.administrator.training.reports;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.training.reports.action.ListTrainingByVehicleTypeAction;

public class ReportsMenu extends BaseMenu {

	public ReportsMenu() {
		menuOptions = new Object[][] { 
			
				{ "Manager > Training management > Reports", null },

				//{ "Training of a mechanic", NotYetImplementedAction.class },
				// TO-DO ListTrainingOfMechanicAction.class },
				{ "Training by vehicle types", ListTrainingByVehicleTypeAction.class },
				//{ "Certifications by vehicle type", NotYetImplementedAction.class },
				// TO-DO ListCertificationsByVehicleTypeAction.class }
		};
	}

}
