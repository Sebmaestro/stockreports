package com.example.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.backend.model.Stock;


public interface StockRepository extends JpaRepository<Stock, Integer> {
    // Här kan du lägga till anpassade metoder för att hämta aktier baserat på olika kriterier
    // Exempel: List<Stock> findByUserId(Long userId);
    
}
