package co.edu.ufps.kampus.controllers;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.ufps.kampus.services.ReportService;
import io.swagger.v3.oas.models.media.MediaType;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping(value = "/performance", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePerformanceReport(
        @RequestParam UUID studentId,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) throws IOException {
        byte[] pdfBytes = reportService.generatePerformanceReport(studentId, startDate, endDate);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_rendimiento.pdf")
            .body(pdfBytes);
    }
}