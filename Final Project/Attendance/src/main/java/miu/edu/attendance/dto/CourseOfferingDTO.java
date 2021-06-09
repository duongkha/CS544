package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CourseOfferingDTO {
    private Long id;
    private String courseCode;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
}
