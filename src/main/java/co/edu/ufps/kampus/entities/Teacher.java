package co.edu.ufps.kampus.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

<<<<<<< HEAD
    @ManyToMany
    @JoinTable(name = "teacher_subjects")
    private List<Subject> subjects = new ArrayList<>();

    public List<Subject> getSubjects() {
        return subjects != null ? List.copyOf(subjects) : List.of();
    }

    protected void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
=======
    private List<Subject> subjects = new ArrayList<>();

    public void assignSubject(Subject subject) {
        subjects.add(subject);
        subject.setTeacher(this);
>>>>>>> d33ba7bdd950ff74c95fb6a1542f3624fbd38871
    }
}