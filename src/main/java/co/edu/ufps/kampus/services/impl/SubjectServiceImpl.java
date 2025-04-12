package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.dtos.request.SubjectRequestDTO;
import co.edu.ufps.kampus.dtos.response.SubjectResponseDTO;
import co.edu.ufps.kampus.entities.*;
import co.edu.ufps.kampus.exceptions.ResourceNotFoundException;
import co.edu.ufps.kampus.exceptions.BusinessException;
import co.edu.ufps.kampus.repositories.*;
import co.edu.ufps.kampus.services.SubjectService;
import co.edu.ufps.kampus.mappers.SubjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectMapper mapper;

    @Override
    public SubjectResponseDTO createSubject(SubjectRequestDTO request) {
        validateSubjectRequest(request);

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + request.getCourseId()));

        Subject subject = mapper.toEntity(request);
        subject.setCourse(course);

        return mapper.toDTO(subjectRepository.save(subject));
    }

    @Override
    public SubjectResponseDTO getSubjectById(UUID id) {
        return subjectRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return List.of();
    }


    public List<SubjectResponseDTO> findAll() {
        return subjectRepository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subject> findByCode(String code) {
        return subjectRepository.findByCode(code);
    }

    @Override
    public Subject save(Subject subject) {
        return null;
    }

    @Override
    public List<SubjectResponseDTO> getSubjectsByCourse(UUID courseId) {
        return subjectRepository.findByCourseId(courseId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectResponseDTO updateSubject(UUID id, SubjectRequestDTO request) {
        validateSubjectRequest(request);

        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + id));

        mapper.updateEntityFromDTO(request, existingSubject);

        return mapper.toDTO(subjectRepository.save(existingSubject));
    }

    @Override
    public void delete(UUID id) {
        if (!subjectRepository.existsById(id)) {
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

    @Override
    public List<Subject> findSubjectsByCourseId(UUID courseId) {
        return List.of();
    }

    @Override
    public SubjectResponseDTO assignTeacher(UUID subjectId, UUID teacherId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + teacherId));

        subject.setTeacher(teacher);
        return mapper.toDTO(subjectRepository.save(subject));
    }

    @Override
    public Optional<Subject> findById(UUID id) {
        return Optional.empty();
    }

    private void validateSubjectRequest(SubjectRequestDTO request) {
        if (subjectRepository.existsByCode(request.getCode())) {
            throw new BusinessException("Subject code already exists");
        }

        if (request.getCapacity() != null && request.getCapacity() <= 0) {
            throw new BusinessException("Capacity must be a positive number");
        }
    }
}