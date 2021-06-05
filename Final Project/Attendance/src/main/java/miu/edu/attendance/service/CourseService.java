package miu.edu.attendance.service;


import miu.edu.attendance.domain.Course;

public interface CourseService {

    public boolean addCourse (Course course);
    public boolean updateCourse (Course course);
    public boolean deleteCourseByID (Long id);
    public Course findCourseByID( Long id);


}
