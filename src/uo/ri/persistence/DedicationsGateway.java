package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.DedicationDto;

public interface DedicationsGateway extends Gateway {

    List<DedicationDto> findAll();

}
