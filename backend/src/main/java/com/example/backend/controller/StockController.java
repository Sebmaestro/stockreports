package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:3000") // tillåt React att anropa API:t
public class StockController {
    
    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }


    @GetMapping("/getReportDates/{ticker}")
    public String getReportDates(@PathVariable String ticker) {
        System.out.println("Received ticker: " + ticker);
        return stockService.getReportDates(ticker).toString();
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit!");
        return "test endpoint hit!";
    }
    
}
