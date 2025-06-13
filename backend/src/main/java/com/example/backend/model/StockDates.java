package com.example.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StockDates {
    private LocalDate latestReport;
    private LocalDate upcomingReport;

    public StockDates(LocalDate latestReport, LocalDate upcomingReport) {
        this.latestReport = latestReport;
        this.upcomingReport = upcomingReport;
    }

    public LocalDate getLatestReport() {
        return latestReport;
    }

    public void setLatestReport(LocalDate latestReport) {
        this.latestReport = latestReport;
    }

    public LocalDate getUpcomingReport() {
        return upcomingReport;
    }

    public void setUpcomingReport(LocalDate upcomingReport) {
        this.upcomingReport = upcomingReport;
    }
}
