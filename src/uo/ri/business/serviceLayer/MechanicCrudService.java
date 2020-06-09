package uo.ri.business.serviceLayer;

import java.util.List;

import uo.ri.business.dto.MechanicDto;
import uo.ri.common.BusinessException;

public interface MechanicCrudService {

	/**
	 * Add a new mechanic to the system with the data specified in the dto. The id
	 * value will be ignored
	 * 
	 * @param mecanico dto
	 * @throws BusinessException if there already exist another mechanic with the
	 *                           same dni
	 */
	void addMechanic(MechanicDto mechanic) throws BusinessException;

	/**
	 * @param idMecanico
	 * @throws BusinessException if the mechanic does not exist
	 */
	void deleteMechanic(Long idMechani) throws BusinessException;

	/**
	 * Updates values for the mechanic specified by the id field, just name and
	 * surname will be updated
	 * 
	 * @param mecanico dto, the id field, name and surname cannot be null
	 * @throws BusinessException if the mechanic does not exist
	 */
	void updateMechanic(MechanicDto mechanic) throws BusinessException;

	/**
	 * @return the list of all mechanics registered in the system without regarding
	 *         their contract status or if they have contract or not. It might be an
	 *         empty list if there is no mechanic
	 * 
	 *         DO NOT @throws BusinessException
	 */
	List<MechanicDto> findAllMechanics();

}
