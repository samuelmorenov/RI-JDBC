package uo.ri.ui.administrator.action;

import java.util.Comparator;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.dto.TrainingHoursRow;
import uo.ri.business.serviceLayer.CourseReportService;
import uo.ri.conf.ServiceFactory;
import uo.ri.ui.util.Printer;

public class ListTrainingByVehicleTypeAction implements Action {

	@Override
	public void execute() throws Exception {

		CourseReportService rs = ServiceFactory.forCourseReportService();
		List<TrainingHoursRow> rows = rs.findTrainingByVehicleTypeAndMechanic();

		Console.println("Training by vehicle type");
		rows.sort( new TVTRComparator() );
		rows.forEach( r ->
			Printer.printTrainingHoursRow( r )
		);
	}

	/**
	 * The sorting can be done in the query, but is also frequently done
	 * at the presentation layer
	 */
	private class TVTRComparator implements Comparator<TrainingHoursRow> {

		@Override
		public int compare(TrainingHoursRow a,
				TrainingHoursRow b) {

			int res = a.vehicleTypeName.compareTo( b.vehicleTypeName );
			if ( res == 0)  {
				res = a.mechanicFullName.compareTo( b.mechanicFullName);
			}
			return res;
		}

	}

}
