package miu.edu.attendance.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BarcodeRecord {
    @Id
    private Long id;
    private String barcodeId;
    private LocalDateTime date;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private Student student;
    @OneToOne (cascade = CascadeType.PERSIST)
    private TimeSlot timeSlot;
    @ManyToOne
    @JoinColumn(name="location_id")
    private Location location;

}
