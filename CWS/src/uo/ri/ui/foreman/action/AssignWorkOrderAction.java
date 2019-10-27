package uo.ri.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.serviceLayer.WorkOrderService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServiceFactory;

public class AssignWorkOrderAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Long woId = Console.readLong("Work order id");
		Long mId = Console.readLong("Mechanic id");

		WorkOrderService as = ServiceFactory.getWorkOrderService();
		as.assignWorkOrderToMechanic(woId, mId);

		Console.println("\nAssignation done");
	}
}
