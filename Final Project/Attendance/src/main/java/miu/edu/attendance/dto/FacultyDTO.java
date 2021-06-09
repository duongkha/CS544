package miu.edu.attendance.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import miu.edu.attendance.domain.CourseOffering;

@Getter
@Setter
public class FacultyDTO {
    private long id;
    private String firstName;
    private String lastName;
    private boolean approved;
    private String department;
    private String title;
    private List<CourseOffering> courseOfferings;
    private UserDTO user;
}
