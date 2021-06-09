package miu.edu.attendance.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AttendanceRecordDTO {
    private String barcodeId;
    private LocalDateTime date;
    private Boolean present;

}
