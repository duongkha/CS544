package miu.edu.attendance.service;

import miu.edu.attendance.domain.ClassSession;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.domain.TimeSlot;
import miu.edu.attendance.dto.ClassSessionRequestDTO;
import miu.edu.attendance.dto.ClassSessionResponseDTO;
import miu.edu.attendance.repository.ClassSessionRepository;
import miu.edu.attendance.repository.CourseOfferingRepository;
import miu.edu.attendance.repository.LocationRepository;
import miu.edu.attendance.repository.TimeSlotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public boolean addClassSession(ClassSessionRequestDTO classSessionRequestDTO) {
System.out.println("insercive 1");
System.out.println(classSessionRequestDTO);
        if(classSessionRequestDTO.getDate()==null|| classSessionRequestDTO.getLocationId()==null|| classSessionRequestDTO.getCourseOfferingId()==null){
            System.out.println("insercive 2");
            return false;
        }

        Location location= locationRepository.findById(classSessionRequestDTO.getLocationId()).orElse(null);
        TimeSlot timeSlot= timeSlotRepository.findById(classSessionRequestDTO.getTimeSlotId()).orElse(null);
        CourseOffering courseOffering=  courseOfferingRepository.findById(classSessionRequestDTO.getCourseOfferingId()).orElse(null);
        LocalDate date= classSessionRequestDTO.getDate();
        ClassSession classSession=modelMapper.map(classSessionRequestDTO,ClassSession.class);

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
    public boolean updateClassSession(ClassSessionRequestDTO classSessionRequestDTO) {

        if(classSessionRequestDTO.getDate()==null|| classSessionRequestDTO.getLocationId()==null|| classSessionRequestDTO.getCourseOfferingId()==null){
            return false;
        }

        Location location= locationRepository.findById(classSessionRequestDTO.getLocationId()).orElse(null);
        TimeSlot timeSlot= timeSlotRepository.findById(classSessionRequestDTO.getTimeSlotId()).orElse(null);
        CourseOffering courseOffering=  courseOfferingRepository.findById(classSessionRequestDTO.getCourseOfferingId()).orElse(null);
        LocalDate date= classSessionRequestDTO.getDate();
        ClassSession classSession= classSessionRepository.findById(classSessionRequestDTO.getId()).orElse(null);
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
    public ClassSessionResponseDTO findTClassSessionByID(Long id) {


        ClassSession classSession= classSessionRepository.findById(id).orElseGet(null);
        ClassSessionResponseDTO classSessionResponseDTO = new ClassSessionResponseDTO();
        classSessionResponseDTO.setCourseCode(classSession.getCourseOffering().getCourse().getCourseCode());
        classSessionResponseDTO.setCourseName(classSession.getCourseOffering().getCourse().getCourseName());
        classSessionResponseDTO.setDate(classSession.getDate());
        classSessionResponseDTO.setStartTime(classSession.getTimeSlot().getStartTime());
        classSessionResponseDTO.setEndTime(classSession.getTimeSlot().getEndTime());
        classSessionResponseDTO.setPeriod(classSession.getTimeSlot().getCode());
        classSessionResponseDTO.setLocation(classSession.getLocation().getDescription());

                      return classSessionResponseDTO;
    }

    @Override
    public List<ClassSessionResponseDTO> getAllClassSessions() {


        List<ClassSession> classSessions=classSessionRepository.findAll();
        List<ClassSessionResponseDTO> classSessionResponseDTOS = new ArrayList<>();
      ClassSessionResponseDTO classSessionResponseDTO =null;
        for(ClassSession classSession:classSessions){
            classSessionResponseDTO = new ClassSessionResponseDTO();

            classSessionResponseDTO.setCourseCode(classSession.getCourseOffering().getCourse().getCourseCode());
            classSessionResponseDTO.setCourseName(classSession.getCourseOffering().getCourse().getCourseName());
            classSessionResponseDTO.setDate(classSession.getDate());
            classSessionResponseDTO.setStartTime(classSession.getTimeSlot().getStartTime());
            classSessionResponseDTO.setEndTime(classSession.getTimeSlot().getEndTime());
            classSessionResponseDTO.setPeriod(classSession.getTimeSlot().getCode());
            classSessionResponseDTO.setLocation(classSession.getLocation().getDescription());

            classSessionResponseDTOS.add(classSessionResponseDTO);
        }



        return classSessionResponseDTOS;
    }
}
