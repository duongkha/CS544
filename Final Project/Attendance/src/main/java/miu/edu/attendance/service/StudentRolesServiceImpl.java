package miu.edu.attendance.service;

import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.dto.StudentRegistrationDTO;
import miu.edu.attendance.repository.BarcodeRecordRepository;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentRolesServiceImpl implements StudentRolesService {

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Autowired
    BarcodeRecordRepository barcodeRecordRepository;
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public List <AttendanceRecordDTO> getAttendanceRecordForCourse(Long studentid, Long courseofferingid) {
       Student student= studentRepository.findStudentById(studentid);
       CourseOffering courseOffering= courseOfferingRepository.findById(courseofferingid).orElse(null);
       // get student from courseoffering and check if the above student is in the list and get the students attendance recorde
      return courseOffering.getStudents().stream().filter(st->st.getStudentId().equalsIgnoreCase(student.getStudentId())).
               map(st->st.getBarcodeRecords()).flatMap(br->br.stream()).map(br->
              new AttendanceRecordDTO(br.getBarcodeId(),br.getDate(),br.getTimeSlot()!=null)).collect(Collectors.toList());

    }

    @Override
    public List<CourseOfferingDTO> getAllCourseOfferings(Long studentid) {
        return  studentRepository.findStudentById(studentid).getCourseOfferings().stream().map(crs->modelMapper.map(crs,CourseOfferingDTO.class)).collect(Collectors.toList());
    }



    @Override
    public boolean register(StudentRegistrationDTO studentRegistrationDTO) {
System.out.println("text0");
        Student student= studentRepository.findById(studentRegistrationDTO.getId()).orElse(null);

        if(student!=null){
            System.out.println("test");
            CourseOffering courseOffering= courseOfferingRepository.findAll().
                    stream().filter(course->course.getCourse().getCourseCode().equalsIgnoreCase(studentRegistrationDTO.getCourseCode()))
                    .collect(Collectors.toList()).get(0);

            if(courseOffering!=null){
                System.out.println("test2");
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
