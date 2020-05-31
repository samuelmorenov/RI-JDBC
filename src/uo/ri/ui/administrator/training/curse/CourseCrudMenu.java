package uo.ri.ui.administrator.training.curse;

import alb.util.menu.BaseMenu;
import uo.ri.ui.administrator.training.curse.action.AddCourseAction;
import uo.ri.ui.administrator.training.curse.action.ListCoursesAction;
import uo.ri.ui.administrator.training.curse.action.RemoveCourseAction;
import uo.ri.ui.administrator.training.curse.action.UpdateCourseAction;

public class CourseCrudMenu extends BaseMenu {

	public CourseCrudMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Training management > Course CRUD", null},
			
			{ "Add", 			AddCourseAction.class }, 
			{ "Update", 		UpdateCourseAction.class }, 
			{ "Remove", 		RemoveCourseAction.class }, 
			{ "List all", 		ListCoursesAction.class },
		};
	}

}
