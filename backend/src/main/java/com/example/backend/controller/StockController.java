package com.example.backend.controller;


import com.example.backend.model.Stock;
import com.example.backend.service.StockService;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getReportDates(@PathVariable String ticker) {
        System.out.println("Received ticker: " + ticker);
        return ResponseEntity.ok(stockService.getReportDates(ticker));        
    }

    @PostMapping("/test")
    public ResponseEntity<?> test(@RequestBody String body) {
        System.out.println("Test endpoint hit!");
        System.out.println("Body: " + body);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Stock stock) {
        Stock createdStock = stockService.createStock(stock);
        System.out.println("hej");
        return ResponseEntity.ok(createdStock);
    }
    
}
