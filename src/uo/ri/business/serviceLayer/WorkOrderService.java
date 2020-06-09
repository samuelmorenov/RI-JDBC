package uo.ri.business.serviceLayer;

import java.util.List;
import java.util.Optional;

import uo.ri.business.dto.CertificateDto;
import uo.ri.business.dto.WorkOrderDto;
import uo.ri.common.BusinessException;

/**
 * This service is intended to be used by the Foreman It follows the ISP
 * principle (@see SOLID principles from RC Martin)
 */
public interface WorkOrderService {

	/**
	 * Registers a new work order out of the data received. Only this fields will be
	 * taken into account: - the vehicle id, and - the description of the work to be
	 * done The rest of fields will assigned by the service, thus any provided value
	 * will be ignored.
	 *
	 * @param dto. Just vehicle id and description.
	 *
	 * @return another dto with the provided values and service-assigned fields
	 *         filled: id, date and status
	 *
	 * @throws BusinessException if: - there is another work order for the same
	 *                           vehicle at the same date and time (timestamp), or -
	 *                           the vehicle does not exist
	 */
	WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException;

	/**
	 * Updates the description of the work order specified by the id and version
	 * fields. No other field is taken for the update. Only work orders with status
	 * of OPEN or ASSIGNED can be updated.
	 *
	 * @param dto, with work order id, version and description.
	 *
	 * @throws BusinessException if: - there is no work order with that id, or -
	 *                           there work order has not the specified version
	 *                           (optimistic lock), or - the work order is not in
	 *                           the OPEN or ASSIGNED status
	 */
	void updateWorkOrder(WorkOrderDto dto) throws BusinessException;

	/**
	 * Removes the work order form the system if it still do not have interventions.
	 *
	 * @param id, of the work order
	 *
	 * @throws BusinessException if: - the work order does not exist, or - there
	 *                           already is some intervention registered.
	 */
	void deleteWorkOrder(Long id) throws BusinessException;

	/**
	 * @param woId, id of the work order
	 * @return the optional filled if the work order exists
	 * @throws BusinessException
	 */
	Optional<WorkOrderDto> findWorkOrderById(Long woId) throws BusinessException;

	/**
	 * Returns a list of certificates (i.e, certified mechanics) for the vehicle
	 * type. Every certificate includes full mechanic data (@see MechanicDto).
	 *
	 * @param id of the vehicle type
	 *
	 * @return the list. It might be empty if no mechanic is certified for the
	 *         specified vehicle type.
	 * @throws BusinessException, DOES NOT
	 */
	List<CertificateDto> findCertificatesByVehicleTypeId(Long id);

	/**
	 * Assigns the work order to mechanic so the he/she can see what work has to
	 * today/next. Only work orders with OPEN status can be assigned. Work orders
	 * changes to ASSIGNED status when assigned.
	 *
	 * @param woId,       the work order id
	 * @param mechanicId, the mechanic one
	 *
	 * @throws BusinessException if: - the mechanic does not exist, or - the work
	 *                           order does not exist, or - the work order is not in
	 *                           OPEN status
	 */
	void assignWorkOrderToMechanic(Long woId, Long mechanicId) throws BusinessException;

}
