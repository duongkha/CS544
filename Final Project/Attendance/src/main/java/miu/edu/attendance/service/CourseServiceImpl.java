package miu.edu.attendance.service;

import com.zhaofujun.automapper.AutoMapper;
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

@Autowired
    AutoMapper autoMapper;




     @Transactional(readOnly = true)
    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses=courseRepository.findAll();
        List<CourseDTO> courseDTOS= courses.stream().map(course->modelMapper.map(course,CourseDTO.class)).collect(Collectors.toList());
        return courseDTOS;

    }

    @Override
    public boolean addCourse(Course course) {
        courseRepository.save(course);
            return true;
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        Course course1=null;

        System.out.println(courseDTO);
            course1=courseRepository.findById(courseDTO.getId()).orElseGet(null);
            if(course1!=null){
                if(courseDTO.getCourseCode()!=null){
                    course1.setCourseCode(courseDTO.getCourseCode());
                }
                if(courseDTO.getCourseName()!=null){
                    course1.setCourseName(courseDTO.getCourseName());
                }
                if(courseDTO.getAbbreviation()!=null){
                    course1.setAbbreviation(courseDTO.getAbbreviation());
                }
                if(courseDTO.getCredit()!=null){
                    course1.setCredit(courseDTO.getCredit());
                }

                courseRepository.save(course1);
                return true;
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
