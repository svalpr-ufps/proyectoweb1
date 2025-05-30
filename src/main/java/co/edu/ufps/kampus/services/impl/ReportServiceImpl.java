package co.edu.ufps.kampus.services.impl;

import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.repositories.GradeRepository;
import co.edu.ufps.kampus.services.ReportService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReportServiceImpl implements ReportService {

    private final GradeRepository gradeRepository;

    @Autowired
    public ReportServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public byte[] generatePerformanceReport(UUID studentId, LocalDate startDate, LocalDate endDate) throws IOException {
        List<Grade> grades = gradeRepository.findByAcademicRecord_Student_IdAndEvaluation_DateBetween(
            studentId, startDate, endDate
        );

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // Configuración de fuentes
                PDType1Font headerFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
                
                // Cabecera
                contentStream.setFont(headerFont, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Reporte de Rendimiento Académico");
                contentStream.endText();

                // Información del estudiante
                contentStream.setFont(bodyFont, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 650);
                
                String studentName = grades.isEmpty() ? "N/A" : 
                    grades.get(0).getAcademicRecord().getStudent().getFullName();
                    
                contentStream.showText("Estudiante: " + studentName);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Período: " + startDate + " a " + endDate);
                contentStream.endText();

                // Tabla de calificaciones
                int yPosition = 600;
                for (Grade grade : grades) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yPosition);
                    
                    String subjectName = grade.getEvaluation() == null || 
                                        grade.getEvaluation().getSubject() == null ? 
                                        "N/A" : grade.getEvaluation().getSubject().getName();
                    
                    Double score = grade.getScore() != null ? grade.getScore() : 0.0;
                    
                    contentStream.showText(
                        String.format("%s: %.2f (%s)", 
                            subjectName, 
                            score,
                            score >= 3.0 ? "Aprobado" : "Reprobado")
                    );
                    contentStream.endText();
                    yPosition -= 20;
                }
            }

            // Convertir a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}