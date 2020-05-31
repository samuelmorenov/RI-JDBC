package uo.ri.ui.administrator.training.curse.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.CourseCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class RemoveCourseAction implements Action {

	@Override
	public void execute() throws BusinessException {

		// Ask the user for data
		Long cId = Console.readLong("Course id");

		// Invoke the service
		CourseCrudService cs = ServiceFactory.forCourseCrudService();
		cs.deleteCourse( cId );

		// Show result
		Console.println("Course removed");
	}

}
