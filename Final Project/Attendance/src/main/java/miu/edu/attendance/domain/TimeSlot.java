package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
    @NotNull
    private String code;  //Am / Pm

    @OneToMany (mappedBy = "timeSlot",cascade = CascadeType.PERSIST)
    private List<ClassSession> classSessions;


public boolean addClassSession (ClassSession classSession){
    if(classSessions.add(classSession)){
        classSession.setTimeSlot(this);
        return true;
    }
    return false;

}

public boolean removeClassSession (ClassSession classSession){
    classSessions= classSessions.stream().filter(c->c.getId()!=classSession.getId()).collect(Collectors.toList());
    classSession.setTimeSlot(null);
    return true;
}


}
