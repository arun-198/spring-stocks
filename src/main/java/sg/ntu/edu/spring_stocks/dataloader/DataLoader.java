package sg.ntu.edu.spring_stocks.dataloader;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import sg.ntu.edu.spring_stocks.pojo.Forecast;
import sg.ntu.edu.spring_stocks.repository.ForecastRepository;

@Component
public class DataLoader {
    
    private ForecastRepository forecastRepository;

    public DataLoader(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    @PostConstruct
    public void loadData() {
        forecastRepository.deleteAll();

        forecastRepository.save(new Forecast("AAPL",300.00f,186.00f,147.15f,"Moderate Buy"));
        forecastRepository.save(new Forecast("GOOGL",240.00f,170.00f,30.48f,"Strong Buy"));
        forecastRepository.save(new Forecast("NVDA",200.00f,90.00f,115.52f,"Strong Buy"));
        forecastRepository.save(new Forecast("MSFT", 320.00f, 210.00f, 294.50f, "Strong Buy"));
        forecastRepository.save(new Forecast("TSLA", 420.00f, 230.00f, 311.75f, "Moderate Buy"));
        forecastRepository.save(new Forecast("AMZN", 180.00f, 125.00f, 135.45f, "Buy"));
        forecastRepository.save(new Forecast("META", 290.00f, 180.00f, 278.20f, "Moderate Buy"));
        forecastRepository.save(new Forecast("BABA", 150.00f, 80.00f, 95.30f, "Buy"));
        forecastRepository.save(new Forecast("NFLX", 450.00f, 300.00f, 360.25f, "Moderate Buy"));
        forecastRepository.save(new Forecast("ORCL", 120.00f, 70.00f, 108.90f, "Buy"));
        forecastRepository.save(new Forecast("AMD", 140.00f, 75.00f, 101.50f, "Strong Buy"));
        forecastRepository.save(new Forecast("DIS", 160.00f, 90.00f, 98.65f, "Hold"));
        forecastRepository.save(new Forecast("UBER", 60.00f, 40.00f, 49.85f, "Buy"));

    }
}
