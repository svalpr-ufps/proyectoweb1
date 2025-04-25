package co.edu.ufps.kampus.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ufps.kampus.entities.Grade;
import co.edu.ufps.kampus.repositories.GradeRepository;
import co.edu.ufps.kampus.services.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public byte[] generatePerformanceReport(UUID studentId, LocalDate startDate, LocalDate endDate) throws IOException {
        // 1. Obtener datos del estudiante y sus calificaciones
        List<Grade> grades = ((Object) gradeRepository).findByAcademicRecord_Student_IdAndEvaluation_DateBetween(
            studentId, startDate, endDate
        );

        // 2. Crear documento PDF
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                // 3. Cabecera del reporte
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Reporte de Rendimiento Académico");
                contentStream.endText();

                // 4. Detalles del estudiante
                contentStream.setFont(PDType1Font.HELVETICA, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 650);
                contentStream.showText("Estudiante: " + grades.get(0).getAcademicRecord().getStudent().getFullName());
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Período: " + startDate + " a " + endDate);
                contentStream.endText();

                // 5. Tabla de calificaciones
                int yPosition = 600;
                for (Grade grade : grades) {
                    contentStream.beginText();
                    contentStream.newLineAtOffset(100, yPosition);
                    contentStream.showText(
                        grade.getEvaluation().getSubject().getName() + ": " + grade.getScore() + 
                        " (" + (grade.getScore() >= 3.0 ? "Aprobado" : "Reprobado") + ")"
                    );
                    contentStream.endText();
                    yPosition -= 20;
                }
            }

            // 6. Guardar PDF en byte[]
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}