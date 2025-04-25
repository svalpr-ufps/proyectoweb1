package co.edu.ufps.kampus.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

public interface ReportService {
    byte[] generatePerformanceReport(UUID studentId, LocalDate startDate, LocalDate endDate) throws IOException;
}