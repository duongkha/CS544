package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
   @NotNull
    private LocalDate date;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @NotNull
    private TimeSlot timeSlot;

    @ManyToOne (cascade = CascadeType.PERSIST)
    @NotNull
    private CourseOffering courseOffering;


    @OneToOne (cascade = CascadeType.PERSIST)
    @NotNull
    private Location location;

}
