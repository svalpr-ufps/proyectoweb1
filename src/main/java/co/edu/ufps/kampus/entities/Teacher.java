package co.edu.ufps.kampus.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    }

    public void assignSubject(Subject subject){
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        subject.setTeacher(this);
    }
}