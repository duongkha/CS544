package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private long id;

    private String studentId;
    
    private boolean approved;

    UserDTO user;
}
