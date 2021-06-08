package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;
import miu.edu.attendance.domain.CourseOffering;
import miu.edu.attendance.domain.Location;
import miu.edu.attendance.domain.TimeSlot;

import java.time.LocalDate;

@Getter
@Setter
public class ClassSessionRequestDTO {
    private Long id;
    private LocalDate date;
    private Long locationId;
    private Long timeSlotId;
    private Long courseOfferingId;


}
