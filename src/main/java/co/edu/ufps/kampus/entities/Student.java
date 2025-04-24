package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter @Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {

    @Column(unique = true, length = 20)
    private String studentCode
;
    private LocalDate enrollmentDate;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private AcademicRecord academicRecord;
}