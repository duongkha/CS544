package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @ManyToMany(mappedBy = "courseOfferings")
    private List<Student> students;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Faculty faculty;
    @OneToMany(cascade = CascadeType.ALL)
    private List<ClassSession> classSessions;
    @ManyToOne (cascade = CascadeType.PERSIST)
    @NotNull
    private Course course;

    public CourseOffering(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public boolean addStudent (Student student){
        if(students.add(student)){
            student.getCourseOfferings().add(this);
            return true;
        }
        return false;
    }

    public boolean removeStudent (Student student){
        if(students.remove(student)){
            student.getCourseOfferings().remove(this);
            return true;
        }
        return false;
    }
}
