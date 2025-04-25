package co.edu.ufps.kampus.services;

import co.edu.ufps.kampus.entities.Attendance;
import co.edu.ufps.kampus.entities.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceReportService {
    @Autowired
    private AttendanceService attendanceService;

    public ByteArrayInputStream generateExcelReport(Student student, LocalDateTime startDate, LocalDateTime endDate) throws IOException {
        List<Attendance> attendances = attendanceService.getAttendanceReport(student, startDate, endDate);
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Asistencias");
            
            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Fecha");
            headerRow.createCell(1).setCellValue("Presente");
            headerRow.createCell(2).setCellValue("Observaciones");

            // Llenar datos
            int rowNum = 1;
            for(Attendance attendance : attendances) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(attendance.getDateTime().toString());
                row.createCell(1).setCellValue(attendance.isPresent());
                row.createCell(2).setCellValue(attendance.getObservations());
            }

            // Convertir a ByteArrayInputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }
}