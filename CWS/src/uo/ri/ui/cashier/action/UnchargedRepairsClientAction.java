package uo.ri.ui.cashier.action;

import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class UnchargedRepairsClientAction implements Action {

	/**
	 * Process:
	 * 
	 *   - Ask customer dni
	 *    
	 *   - Display all uncharged breakdowns  
	 *   		(status <> 'CHARGED'). For each breakdown, display 
	 *   		id, date, status, amount and description
	 */
	@Override
	public void execute() throws BusinessException {
		// TODO ...
	}

}
