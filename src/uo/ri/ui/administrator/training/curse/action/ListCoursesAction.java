package uo.ri.ui.administrator.training.curse.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.CourseDto;
import uo.ri.business.serviceLayer.CourseCrudService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListCoursesAction implements Action {

	@Override
	public void execute() throws BusinessException {

		CourseCrudService as = ServiceFactory.forCourseCrudService();
		List<CourseDto> courses = as.findAllCourses();

		Console.println("\nList of courses\n");
		for(CourseDto c : courses) {
			Printer.printCourse( c );
		}

	}
}
