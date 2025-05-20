package com.example.backend.controller;


import com.example.backend.service.StockService;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;


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
