package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacultyDTO {
    private long id;
    private int accumulatedPoints;
    UserDTO user;
}
