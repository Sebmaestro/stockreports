package com.example.backend.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String ticker;

    @Column(name = "latest_report")
    private LocalDate latestReport;

    @Column(name = "upcoming_report")
    private LocalDate upcomingReport;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }    
    

}
