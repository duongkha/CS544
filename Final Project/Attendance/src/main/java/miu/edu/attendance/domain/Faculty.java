package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "faculty")
public class Faculty extends Person implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;
    
    @Column(name = "department")
    private String department;

    @Column(name = "title")
    private String title;
    
    @OneToMany(mappedBy = "faculty",cascade = CascadeType.PERSIST)
    private List<CourseOffering> courseOfferings;

}