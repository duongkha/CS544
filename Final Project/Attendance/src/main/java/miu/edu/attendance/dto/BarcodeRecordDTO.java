package miu.edu.attendance.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BarcodeRecordDTO {
    private String barcodeId;
    private LocalDateTime date;
    private Long locationId;

}
