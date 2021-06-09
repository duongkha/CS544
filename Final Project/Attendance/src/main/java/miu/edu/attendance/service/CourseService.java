package miu.edu.attendance.service;


import miu.edu.attendance.domain.Course;
import miu.edu.attendance.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    public boolean addCourse (Course course);
    public boolean updateCourse (CourseDTO courseDTO);
    public boolean deleteCourseByID (Long id);
    public Course findCourseByID( Long id);
    public List<CourseDTO> getAllCourses();


}
