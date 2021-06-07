package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne (cascade = CascadeType.PERSIST)
    private TimeSlot timeSlot;

    @ManyToOne (cascade = CascadeType.PERSIST)
    private CourseOffering courseOffering;


}
