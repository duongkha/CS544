package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRegistrationDTO {

    private Long Id;
    private String firstName;
    private String lastName;
    private String courseCode;

}
