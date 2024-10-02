package sg.ntu.edu.spring_stocks.controller;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import sg.ntu.edu.spring_stocks.exceptions.ForecastNotFoundException;
import sg.ntu.edu.spring_stocks.pojo.Forecast;
import sg.ntu.edu.spring_stocks.services.ForecastService;
import sg.ntu.edu.spring_stocks.services.ForecastServiceImpl;




@RestController
@RequestMapping("/forecast")
public class ForecastController {

    private final Logger logger = LoggerFactory.getLogger(ForecastController.class);

    private ForecastService forecastService;

    public ForecastController(ForecastServiceImpl forecastService) {
        this.forecastService = forecastService;
    }

    @PostMapping("")
    public ResponseEntity<Forecast> createForecast(@Valid @RequestBody Forecast forecast) {
        logger.info("createForecast API called.");
        Forecast newForecast = forecastService.createForecast(forecast);
        return ResponseEntity.status(HttpStatus.CREATED).body(newForecast);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Forecast>> getAllForecasts() {
        logger.info("getAllForecasts API called.");
        ArrayList<Forecast> allForecasts = forecastService.getAllForecasts();
        return ResponseEntity.status(HttpStatus.OK).body(allForecasts);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getForecast(@PathVariable String name) {
        logger.info("getForecast API called.");
        try {
            Forecast foundForecast = forecastService.getForecast(name.toUpperCase());
            return ResponseEntity.status(HttpStatus.OK).body(foundForecast);
        } catch (ForecastNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        
        // Forecast foundForecast = forecastService.getForecast(name.toUpperCase());
        // return ResponseEntity.status(HttpStatus.OK).body(foundForecast);
    }


    @PutMapping("/{name}")
    public ResponseEntity<Object> updateForecast(@PathVariable String name, @RequestBody Forecast forecast) {
        logger.info("updateForecast API called.");
        try {
            Forecast updatedForecast = forecastService.updateForecast(name.toUpperCase(), forecast);
            return ResponseEntity.status(HttpStatus.OK).body(updatedForecast);
        } catch (ForecastNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        // Forecast updatedForecast = forecastService.updateForecast(name, forecast);
        // return ResponseEntity.status(HttpStatus.OK).body(updatedForecast);
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<Object> deleteForecast(@PathVariable String name) {
        logger.info("deleteForecast API called.");
        try {
            forecastService.deleteForecast(name.toUpperCase());
            return ResponseEntity.status(HttpStatus.OK).body("Forecast for stock name " + name.toUpperCase() + " was deleted successfully!");
        } catch (ForecastNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        
    }    
    
    
}
