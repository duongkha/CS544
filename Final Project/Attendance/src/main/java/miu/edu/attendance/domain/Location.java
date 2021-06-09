package miu.edu.attendance.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
   
    @OneToOne (cascade = CascadeType.PERSIST)
    private ClassSession session;
    
    @OneToMany(mappedBy = "location")
    private List<BarcodeRecord> barcodeRecords = new ArrayList<>();

}
