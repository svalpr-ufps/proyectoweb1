package co.edu.ufps.kampus.controllers;

import co.edu.ufps.kampus.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/performance/{studentId}")
    public ResponseEntity<byte[]> generatePerformanceReport(
            @PathVariable UUID studentId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        try {
            byte[] reportBytes = reportService.generatePerformanceReport(studentId, startDate, endDate);
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte-rendimiento.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(reportBytes);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}