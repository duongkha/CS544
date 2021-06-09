package miu.edu.attendance.service;

import miu.edu.attendance.domain.Course;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseOfferingServiceImpl implements CourseOfferingService {

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    ModelMapper modelMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CourseOfferingDTO> getAllCourseOffering() {
        List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();
        List<CourseOfferingDTO> courseOfferingDTOS = new ArrayList<>();
        CourseOfferingDTO courseOfferingDTO = null;
        for (CourseOffering course : courseOfferings) {
            courseOfferingDTO = new CourseOfferingDTO();
            courseOfferingDTO.setCourseCode(course.getCourse().getCourseCode());
            courseOfferingDTO.setCourseName(course.getCourse().getCourseName());
            courseOfferingDTO.setStartDate(course.getStartDate());
            courseOfferingDTO.setEndDate(course.getEndDate());
            courseOfferingDTOS.add(courseOfferingDTO);
        }
        return courseOfferingDTOS;
    }


    @Override
    public boolean addCourseOffering(CourseOfferingDTO courseOfferingDTO) {

        Course course = courseRepository.findAll().stream().filter(x -> x.getCourseCode().
                equalsIgnoreCase(courseOfferingDTO.getCourseCode())).collect(Collectors.toList()).get(0);
        if (course != null) {
            CourseOffering courseOffering = new
                    CourseOffering(courseOfferingDTO.getStartDate(), courseOfferingDTO.getEndDate());
            System.out.println("coursDTO" + courseOfferingDTO.getStartDate());
            //CourseOffering courseOffering= modelMapper.map(courseOfferingDTO,CourseOffering.class);
            System.out.println("courseOffering" + courseOffering.getEndDate());
            course.addCourseOffering(courseOffering);
            courseOffering.setCourse(course);
            courseOfferingRepository.save(courseOffering);


            return true;
        }
        return false;
    }


    @Override
    public boolean deleteCourseOfferingByID(Long id) {
        CourseOffering courseOffering = courseOfferingRepository.findById(id).orElse(null);
        if (courseOffering != null) {
            courseOfferingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CourseOfferingDTO findCourseOfferingByID(Long id) {

        CourseOffering courseOffering = courseOfferingRepository.findById(id).orElse(null);
        CourseOfferingDTO courseOfferingDTO = null;
        if (courseOffering != null) {
            courseOfferingDTO = new CourseOfferingDTO();
            courseOfferingDTO.setCourseCode(courseOffering.getCourse().getCourseCode());
            courseOfferingDTO.setCourseName(courseOffering.getCourse().getCourseName());
            courseOfferingDTO.setStartDate(courseOffering.getStartDate());
            courseOfferingDTO.setEndDate(courseOffering.getEndDate());
            return courseOfferingDTO;
        }
        return courseOfferingDTO;
    }


    @Override
    public boolean updateCourseOffering(CourseOfferingDTO courseOfferingDTO) {

        if (courseOfferingDTO.getId() == null) {
            return false;
        }
        CourseOffering courseOffering = courseOfferingRepository.findById(courseOfferingDTO.getId()).orElse(null);

        if (courseOffering == null) {
            return false;
        }

        if (courseOfferingDTO.getStartDate() != null) {
            courseOffering.setStartDate(courseOfferingDTO.getStartDate());
        }
        if (courseOfferingDTO.getEndDate() != null) {
            courseOffering.setEndDate(courseOfferingDTO.getEndDate());
        }

        courseOfferingRepository.save(courseOffering);
        return true;
    }

}
