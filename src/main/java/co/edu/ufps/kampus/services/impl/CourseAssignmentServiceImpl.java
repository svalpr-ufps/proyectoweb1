@Service
public class CourseAssignmentService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public void assignSubjectToTeacher(UUID subjectId, UUID teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new RuntimeException("Subject not found"));
        Teacher teacher = teacherRepository.findById(teacherId)
            .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.assignSubject(subject);

        teacherRepository.save(teacher); // cascades to subject
    }
}