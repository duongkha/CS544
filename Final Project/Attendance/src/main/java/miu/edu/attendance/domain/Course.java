package miu.edu.attendance.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Course {
   @Id
   @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseCode;
    private String courseName;

    @OneToMany (mappedBy = "course",cascade = CascadeType.PERSIST)
    private List<miu.edu.attendance.domain.CourseOffering> courseOfferings;

}
