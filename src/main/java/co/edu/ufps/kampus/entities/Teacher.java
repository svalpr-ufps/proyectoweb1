package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "teachers")
@Getter @Setter
@PrimaryKeyJoinColumn(name = "user_id")
public class Teacher extends User {

    @Column(unique = true, length = 20)
    private String teacherCode;

    @Column(length = 50)
    private String academicDegree;

    private LocalDate hireDate;

    @Column(length = 100)
    private String specialization;

    private List<Subject> subjects = new ArrayList<>();

    public void assignSubject(Subject subject) {
        subjects.add(subject);
        subject.setTeacher(this);
    }
}