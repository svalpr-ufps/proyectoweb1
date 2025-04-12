@RestController
@RequestMapping("/assignments")
public class CourseAssignmentController {

    @Autowired
    private CourseAssignmentService courseAssignmentService;

    @PostMapping("/subject-to-teacher")
    public ResponseEntity<?> assignSubjectToTeacher(@RequestParam UUID subjectId, @RequestParam UUID teacherId) {
        courseAssignmentService.assignSubjectToTeacher(subjectId, teacherId);
        return ResponseEntity.ok("Subject assigned to teacher successfully.");
    }
}
