package miu.edu.attendance.service;

import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Faculty;
import miu.edu.attendance.domain.Student;
import miu.edu.attendance.dto.AttendanceRecordDTO;
import miu.edu.attendance.dto.CourseOfferingDTO;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyRolesServiceImpl implements FacultyRolesService {


    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    CourseOfferingRepository courseOfferingRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public boolean addCourseToFaculty(Long facultyId, Long courseofferingId) {

       Faculty faculty= facultyRepository.findById(facultyId).orElse(null);
        CourseOffering courseOffering= courseOfferingRepository.findById(courseofferingId).orElse(null);

     if(faculty!=null&&courseOffering!=null){
         faculty.addCourseOffering(courseOffering);
         return true;
     }
        return false;
    }

    @Override
    public List<AttendanceRecordDTO> findAllAttendanceByFacultyIdAndCourseOffering(Long facultyId, Long courseOfferingId) {

        List<Student> students= courseOfferingRepository.findAll().stream().filter(c->c.getId()==courseOfferingId).map(cf->cf.getStudents()).
                flatMap(st->st.stream()).collect(Collectors.toList());

        System.out.println(students);
        return students.stream().map(st->st.getBarcodeRecords()).flatMap(brs->brs.stream()).
                map(br->new AttendanceRecordDTO(br.getBarcodeId(),br.getDate(),br.getTimeSlot()!=null)).collect(Collectors.toList());


    }

    @Override
    public List<CourseOfferingDTO> findAllCourseOfferingsByFacultyId(Long facultyid) {
       Faculty faculty= facultyRepository.findById(facultyid).orElse(null);
       if(faculty!=null){
           return faculty.getCourseOfferings().stream().map(c->modelMapper.map(c,CourseOfferingDTO.class)).collect(Collectors.toList());
       }
       return null;
    }
}
