package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.MechanicCrudService;
import uo.ri.business.dto.MechanicDto;
import uo.ri.business.impl.administrator.AddMechanic;
import uo.ri.business.impl.administrator.DeleteMechanic;
import uo.ri.business.impl.administrator.UpdateMechanic;
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
	public void updateMechanic(MechanicDto mechanic) throws BusinessException {
		UpdateMechanic um = new UpdateMechanic(mechanic);
		um.execute();

	}

	@Override
	public MechanicDto findMechanicById(Long id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MechanicDto> findActiveMechanics() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
