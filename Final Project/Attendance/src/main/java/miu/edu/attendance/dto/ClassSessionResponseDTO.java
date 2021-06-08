package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ClassSessionResponseDTO {
    private String courseCode;
    private String courseName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String period;
    private String location;


}
