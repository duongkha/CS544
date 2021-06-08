package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.ClassSessionDTO;
import miu.edu.attendance.repository.ClassSessionRepository;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.LocationRepository;
import miu.edu.attendance.repository.TimeSlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassSessionServiceImpl implements ClassSessionService {

    @Autowired
    ClassSessionRepository classSessionRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    TimeSlotRepository timeSlotRepository;
    @Autowired
    CourseOfferingRepository courseOfferingRepository;
    @Autowired
    ModelMapper modelMapper;



    @Override
    public boolean addClassSession(ClassSessionDTO classSessionDTO) {
System.out.println("insercive 1");
System.out.println(classSessionDTO);
        if(classSessionDTO.getDate()==null||classSessionDTO.getLocationId()==null||classSessionDTO.getCourseOfferingId()==null){
            System.out.println("insercive 2");
            return false;
        }

        Location location= locationRepository.findById(classSessionDTO.getLocationId()).orElse(null);
        TimeSlot timeSlot= timeSlotRepository.findById(classSessionDTO.getTimeSlotId()).orElse(null);
        CourseOffering courseOffering=  courseOfferingRepository.findById(classSessionDTO.getCourseOfferingId()).orElse(null);
        LocalDate date= LocalDate.parse(classSessionDTO.getDate());
        ClassSession classSession=modelMapper.map(classSessionDTO,ClassSession.class);

        if(location!=null&& timeSlot!=null&& courseOffering!=null&& date!=null){
            System.out.println("insercive 3");
            classSession.setLocation(location);
            classSession.setDate(date);
            classSession.setTimeSlot(timeSlot);
            classSession.setCourseOffering(courseOffering);

            // set the assocuation classess
            timeSlot.getClassSessions().add(classSession);
            courseOffering.getClassSessions().add(classSession);
            classSessionRepository.save(classSession);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateClassSession(ClassSessionDTO classSessionDTO) {

        if(classSessionDTO.getDate()==null||classSessionDTO.getLocationId()==null||classSessionDTO.getCourseOfferingId()==null){
            return false;
        }

        Location location= locationRepository.findById(classSessionDTO.getLocationId()).orElse(null);
        TimeSlot timeSlot= timeSlotRepository.findById(classSessionDTO.getTimeSlotId()).orElse(null);
        CourseOffering courseOffering=  courseOfferingRepository.findById(classSessionDTO.getCourseOfferingId()).orElse(null);
        LocalDate date= LocalDate.parse(classSessionDTO.getDate());
        ClassSession classSession= classSessionRepository.findById(classSessionDTO.getId()).orElse(null);
        if(classSession==null){
            return false;
        }

        if(location!=null&& timeSlot!=null&& courseOffering!=null&& date!=null){
            classSession.setLocation(location);
            classSession.setDate(date);
            classSession.setTimeSlot(timeSlot);
            classSession.setCourseOffering(courseOffering);
            classSessionRepository.save(classSession);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteClassSession(Long id) {
        if(classSessionRepository.findById(id)!=null){
            classSessionRepository.deleteById(id);
            return true;
        }

        return false;
    }

    @Override
    public ClassSession findTClassSessionByID(Long id) {

        ClassSession classSessions= classSessionRepository.findById(id).orElseGet(null);
        System.out.println("test"+classSessions.getCourseOffering().getCourse().getCourseName());

return classSessions;
    }

    @Override
    public List<ClassSession> getAllClassSessions() {

        List<ClassSession> classSessions=classSessionRepository.findAll();
        System.out.println("test get all"+classSessions.get(0).getDate()+classSessions.get(0).getLocation().getDescription() );
       // return classSessionRepository.findAll().stream().map(classsession->
               // modelMapper.map(classsession,ClassSessionDTO.class)).collect(Collectors.toList());
        return classSessions;
    }
}
