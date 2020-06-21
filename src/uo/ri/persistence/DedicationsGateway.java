package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.DedicationDto;

public interface DedicationsGateway extends Gateway {

    void add(DedicationDto dedicationDto);

    int insertDedication(List<DedicationDto> dedicationList);

    List<DedicationDto> findAll();

    void deleteAllWithCourseId(Long id);

}
