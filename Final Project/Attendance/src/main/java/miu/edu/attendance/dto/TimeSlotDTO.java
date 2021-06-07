package miu.edu.attendance.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotDTO {
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private String code;  
}
