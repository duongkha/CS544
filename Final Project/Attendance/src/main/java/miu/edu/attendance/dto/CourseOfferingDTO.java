package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseOfferingDTO {

    private String courseCode;
    private String courseName;
    private String startDate;
    private String endDate;
}
