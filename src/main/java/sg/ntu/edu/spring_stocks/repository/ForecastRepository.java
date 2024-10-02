package sg.ntu.edu.spring_stocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.ntu.edu.spring_stocks.pojo.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, String> {
    
}
