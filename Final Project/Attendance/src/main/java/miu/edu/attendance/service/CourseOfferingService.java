package miu.edu.attendance.service;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.dto.CourseOfferingDTO;

import java.util.List;

public interface CourseOfferingService {

    public boolean addCourseOffering (CourseOfferingDTO course);
    public boolean updateCourseOffering (CourseOfferingDTO course);
    public boolean deleteCourseOfferingByID (Long id);
    public CourseOfferingDTO findCourseOfferingByID( Long id);
    public List<CourseOfferingDTO> getAllCourseOffering();
}
