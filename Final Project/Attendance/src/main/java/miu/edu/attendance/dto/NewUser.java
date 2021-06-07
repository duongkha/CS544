package miu.edu.attendance.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NewUser extends UserDTO {
    private String studentId;
    private String barcodeId;
    private String department;
    private String password;
}
