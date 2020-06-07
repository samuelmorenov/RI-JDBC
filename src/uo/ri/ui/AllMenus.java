package uo.ri.ui;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.mechanics.action.AddMechanicAction;
import uo.ri.ui.administrator.mechanics.action.DeleteMechanicAction;
import uo.ri.ui.administrator.mechanics.action.ListMechanicsAction;
import uo.ri.ui.administrator.mechanics.action.UpdateMechanicAction;
import uo.ri.ui.administrator.training.action.GenerateCertificatesAction;
import uo.ri.ui.administrator.training.curse.action.AddCourseAction;
import uo.ri.ui.administrator.training.curse.action.ListCoursesAction;
import uo.ri.ui.administrator.training.curse.action.RemoveCourseAction;
import uo.ri.ui.administrator.training.curse.action.UpdateCourseAction;
import uo.ri.ui.administrator.training.reports.action.ListTrainingByVehicleTypeAction;
import uo.ri.ui.cashier.action.WorkOrderBillingAction;
import uo.ri.ui.foreman.action.AssignWorkOrderAction;
import uo.ri.ui.foreman.action.ListCertifiedMechanicsAction;
import uo.ri.ui.foreman.action.RegisterWorkOrderAction;
import uo.ri.ui.foreman.action.RemoveWorkOrderAction;
import uo.ri.ui.foreman.action.UpdateWorkOrderAction;

public class AllMenus extends BaseMenu {

	public AllMenus() {
		menuOptions = new Object[][] {

				{ "Admin", null },

				{ "CRUD Mecanicos(*)", null }, 
				{ "Add mechanic", AddMechanicAction.class },
				{ "Update mechanic", UpdateMechanicAction.class }, 
				{ "Delete mechanic", DeleteMechanicAction.class },
				{ "List mechanics", ListMechanicsAction.class }, 
				{ "", null },

				{ "Generacion de certificados (**)", null }, 
				{ "", GenerateCertificatesAction.class }, 
				{ "", null },

				{ "CRUD Cursos(**)", null }, 
				{ "Add", AddCourseAction.class }, 
				{ "Update", UpdateCourseAction.class },
				{ "Remove", RemoveCourseAction.class }, 
				{ "List all", ListCoursesAction.class }, 
				{ "", null },

				{ "Listado de mecánicos que han asistido a formación por tipo de vehículo (**)", null },
				{ "", ListTrainingByVehicleTypeAction.class }, 
				{ "", null },

				{ "Foreman", null }, 
				{ "CRUD de órdenes de trabajo (**)", null },
				{ "Register a work order", RegisterWorkOrderAction.class },
				{ "Update a work order", UpdateWorkOrderAction.class },
				{ "Remove a work order", RemoveWorkOrderAction.class },
				{ "List certified mechanics", ListCertifiedMechanicsAction.class },
				{ "Assign a work order", AssignWorkOrderAction.class }, 
				{ "", null },

				{ "Cashier", null }, 
				{ "Generar Factura (*)", null }, 
				{ "", WorkOrderBillingAction.class }, };
	}

	public static void main(String[] args) {
		new AllMenus().execute();
	}

}