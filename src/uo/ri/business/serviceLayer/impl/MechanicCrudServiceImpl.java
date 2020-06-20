package uo.ri.business.serviceLayer.impl;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.business.serviceLayer.MechanicCrudService;
import uo.ri.business.transactionScripts.administrator.mechanic.CRUD.AddMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.CRUD.DeleteMechanic;
import uo.ri.business.transactionScripts.administrator.mechanic.CRUD.ListMechanics;
import uo.ri.business.transactionScripts.administrator.mechanic.CRUD.UpdateMechanic;
import uo.ri.common.BusinessException;

public class MechanicCrudServiceImpl implements MechanicCrudService {

    @Override
    public void addMechanic(MechanicDto mechanic) throws BusinessException {
	AddMechanic am = new AddMechanic(mechanic);
	am.execute();
    }

    @Override
    public void deleteMechanic(Long idMechanic) throws BusinessException {
	DeleteMechanic dm = new DeleteMechanic(idMechanic);
	dm.execute();

    }

    @Override
    public void updateMechanic(MechanicDto mechanic)
	    throws BusinessException {
	UpdateMechanic um = new UpdateMechanic(mechanic);
	um.execute();

    }

    @Override
    public List<MechanicDto> findAllMechanics() {
	ListMechanics lm = new ListMechanics();
	return lm.execute();
    }

}
