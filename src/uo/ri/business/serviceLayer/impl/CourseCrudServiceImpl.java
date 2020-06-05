package uo.ri.business.serviceLayer.impl;

import java.util.List;
import java.util.Optional;

import uo.ri.business.dto.CourseDto;
import uo.ri.business.dto.VehicleTypeDto;
import uo.ri.business.serviceLayer.CourseCrudService;
import uo.ri.business.transactionScripts.administrator.course.AddCourse;
import uo.ri.business.transactionScripts.administrator.course.DeleteCourse;
import uo.ri.business.transactionScripts.administrator.course.FindAllCourses;
import uo.ri.business.transactionScripts.administrator.course.FindCourseById;
import uo.ri.business.transactionScripts.administrator.course.UpdateCourse;
import uo.ri.business.transactionScripts.administrator.vehicleTypes.FindAllVehicleTypes;
import uo.ri.common.BusinessException;

public class CourseCrudServiceImpl implements CourseCrudService {

	@Override
	public CourseDto registerNew(CourseDto dto) throws BusinessException {
		AddCourse ac = new AddCourse(dto);
		return ac.execute();
	}

	@Override
	public void updateCourse(CourseDto dto) throws BusinessException {
		UpdateCourse uc = new UpdateCourse(dto);
		uc.execute();
	}

	@Override
	public void deleteCourse(Long id) throws BusinessException {
		DeleteCourse dc = new DeleteCourse(id);
		dc.execute();
	}

	@Override
	public List<CourseDto> findAllCourses() throws BusinessException {
		FindAllCourses fac = new FindAllCourses();
		return fac.execute();
	}

	@Override
	public List<VehicleTypeDto> findAllVehicleTypes() throws BusinessException {
		FindAllVehicleTypes favt = new FindAllVehicleTypes();
		return favt.execute();
	}

	@Override
	public Optional<CourseDto> findCourseById(Long cId) throws BusinessException {
		FindCourseById fcbi = new FindCourseById(cId);
		return fcbi.execute();
	}

}
