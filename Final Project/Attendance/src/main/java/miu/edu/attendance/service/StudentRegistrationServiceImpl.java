package miu.edu.attendance.service;

import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.StudentRegistrationDTO;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

@Autowired
StudentService studentService;
@Autowired
StudentRepository studentRepository;
@Autowired
CourseOfferingService courseOfferingService;
@Autowired
CourseOfferingRepository courseOfferingRepository;


    @Override
    public boolean register(StudentRegistrationDTO studentRegistrationDTO) {

        Student student= studentRepository.findAll().
                stream().filter(student1->student1.getStudentId().equalsIgnoreCase(studentRegistrationDTO.getStudentId()))
                .collect(Collectors.toList()).get(0);
        if(student!=null){
            CourseOffering courseOffering= courseOfferingRepository.findAll().
                    stream().filter(course->course.getCourse().getCourseCode().equalsIgnoreCase(studentRegistrationDTO.getCourseCode()))
                    .collect(Collectors.toList()).get(0);

            if(courseOffering!=null){
                student.addCourseOfferings(courseOffering);
                studentRepository.save(student);
                courseOfferingRepository.save(courseOffering);
                return true;
            }
            return false;

        }

        return false;



    }
}
