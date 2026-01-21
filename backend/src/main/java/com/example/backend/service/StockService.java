package com.example.backend.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.backend.model.Stock;
import com.example.backend.model.StockDates;
import com.example.backend.repository.StockRepository;
import com.example.backend.model.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.*;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    private final String ninjaKey = "LE1nw3XIL/RgGi3CEeygOA==PrOgklZv3qU54kT9";

    public StockDates getReportDates(String ticker) {
        List<LocalDate> upcoming = callApiForDates(ticker, true);
        List<LocalDate> past = callApiForDates(ticker, false);
        List<LocalDate> allDates = new ArrayList<>();
        allDates.addAll(past);
        allDates.addAll(upcoming);
        return parseClosestDates(allDates);
    }

    private List<LocalDate> callApiForDates(String ticker, boolean upcoming) {
        String url = "https://api.api-ninjas.com/v1/earningscalendar?ticker=" + ticker + "&show_upcoming=" + upcoming;
        List<LocalDate> dates = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-Api-Key", ninjaKey)
                .GET()
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONArray array = new JSONArray(response.body());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String dateStr = obj.optString("date");
                    dates.add(LocalDate.parse(dateStr));
                }
            } else {
                System.out.println("API error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return dates;
    }

    private StockDates parseClosestDates(List<LocalDate> dates) {
        LocalDate today = LocalDate.now();
        LocalDate closestBefore = null;
        LocalDate closestAfter = null;

        for (LocalDate date : dates) {
            if (date.isBefore(today)) {
                if (closestBefore == null || date.isAfter(closestBefore)) {
                    closestBefore = date;
                }
            } else if (date.isAfter(today)) {
                if (closestAfter == null || date.isBefore(closestAfter)) {
                    closestAfter = date;
                }
            }
        }

        return new StockDates(closestBefore, closestAfter);
    }

    public Stock createStock(Stock stock) {
        //stock.setTicker(stock.getTicker());
        System.out.println("stocken är" + stock);
        System.out.println("big testing");
        StockDates dates = getReportDates(stock.getTicker());
        stock.setLatestReport(dates.getLatestReport());
        stock.setUpcomingReport(dates.getUpcomingReport());
        //stock.setUser(user);
  
        
        

        return stockRepository.save(stock);
    }
}
