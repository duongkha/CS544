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
public class Student implements Serializable {
    private static final long serialVersionUID = 7359591984285268537L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "approved")
    private boolean approved;

    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "barcode")
    private String barcode;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    User user;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="Registration")
    private List<CourseOffering> courseOfferings;
    @OneToMany(mappedBy = "student",cascade = CascadeType.PERSIST)
    private List<BarcodeRecord> barcodeRecords;
}