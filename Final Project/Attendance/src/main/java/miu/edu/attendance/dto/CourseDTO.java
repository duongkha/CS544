package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {

    //private String courseCode;
    private Long id;
    private String courseCode;
    private String courseName;
    private String abbreviation;
    private String credit;


    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", credit='" + credit + '\'' +
                '}';
    }
}
