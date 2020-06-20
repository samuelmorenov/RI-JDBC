package uo.ri.persistence;

import java.util.Date;

import uo.ri.business.dto.VehicleDto;
import uo.ri.business.dto.WorkOrderDto;

public interface WorkOrderGateway extends Gateway {

    VehicleDto findVehicleByPlate(String plate);

    void AddWorkOrder(WorkOrderDto workOrderDto);

    void delete(Long id);

    WorkOrderDto findById(Long id);

    void update(WorkOrderDto workOrderDto);

    boolean mechanicAbleToWorkOrder(Long mechanicId, Long woId);

    void AssignMechanic(Long mechanicId, Long woId);

    Long getLastId();

    int numberOfInterventios(Long woId);

    String getStatus(Long workOrderID);

    WorkOrderDto SearchWorkOrder(Long vehicleId, Date date);

}
