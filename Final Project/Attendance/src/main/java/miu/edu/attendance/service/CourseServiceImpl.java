package miu.edu.attendance.service;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.dto.CourseDTO;
import miu.edu.attendance.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
@Autowired
ModelMapper modelMapper;





    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses=courseRepository.findAll();
        List<CourseDTO> courseDTOS= courses.stream().map(course->modelMapper.map(course,CourseDTO.class)).collect(Collectors.toList());
        return courseDTOS;

    }


    @Override
    public boolean addCourse(Course course) {

        if(courseRepository.save(course)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        Course course1=null;
        if(course!=null && course.getId()!=null){
            course1=courseRepository.findById(course.getId()).orElseGet(null);
            if(course1!=null){
                System.out.println("course service");
                modelMapper.map(course, course1);
                courseRepository.save(course1);
                return true;
            }
            else return false;
        }

  return false;

    }

    @Override
    public boolean deleteCourseByID(Long id) {
        if(courseRepository.findById(id)!=null){
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Course findCourseByID(Long id) {
       return courseRepository.findById(id).orElseGet(null);
    }




}
