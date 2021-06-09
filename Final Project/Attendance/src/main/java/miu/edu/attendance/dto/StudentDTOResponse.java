package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTOResponse {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;

}
