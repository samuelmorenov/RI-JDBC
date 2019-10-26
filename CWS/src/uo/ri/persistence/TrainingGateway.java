package uo.ri.persistence;

import java.util.List;

import uo.ri.business.dto.TrainingHoursRow;

public interface TrainingGateway extends Gateway {

	List<TrainingHoursRow> getTrainingHoursRowList();

}
