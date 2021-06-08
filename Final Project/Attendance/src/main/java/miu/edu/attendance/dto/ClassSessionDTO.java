package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.domain.TimeSlot;

import java.time.LocalDate;

@Getter
@Setter
public class ClassSessionDTO {
    private Long id;
    private String date;
    private Long locationId;
    private Long timeSlotId;
    private Long courseOfferingId;



   private String location;
   private String timeSlot;
   private CourseOffering courseOffering;



}
