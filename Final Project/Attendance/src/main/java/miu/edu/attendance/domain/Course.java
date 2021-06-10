package miu.edu.attendance.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
   @NotNull (message = "courseCode can not be null")
   @Size(min=5,max=5, message = "size must be five charaters")
    private String courseCode;
   @NotNull
   @NotBlank
    private String courseName;
    @NotNull
    @NotBlank
    private String abbreviation;
    @NotNull
    @NotBlank
    private String credit;

    @OneToMany (mappedBy = "course",cascade = CascadeType.ALL)
    private List<CourseOffering> courseOfferings;

    public boolean addCourseOffering (CourseOffering courseOffering){
        if(courseOfferings.add(courseOffering)){
            return true;
        }
        return false;
    }


}
