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
@Table(name = "student")
public class Student  extends Person implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private long id;

    //@Column(name = "approved")
//    private boolean approved;


//    private String firstName;
//    private String lastName;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id")
//    User user;

    @Column(name = "studentId")
    private String studentId;

    private String barcodeId;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="Registration")
    private List<CourseOffering> courseOfferings;
    @OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST)
    private List<BarcodeRecord> barcodeRecords;

    public boolean addCourseOfferings(CourseOffering courseOffering){
        if(courseOfferings.add(courseOffering)){
            courseOffering.getStudents().add(this);
            return true;
        }
        return false;
    }
    public boolean removeCourseOfferings(CourseOffering courseOffering){
        if(courseOfferings.remove(courseOffering)){
            courseOffering.getStudents().remove(this);
            return true;
        }
        return false;
    }
}