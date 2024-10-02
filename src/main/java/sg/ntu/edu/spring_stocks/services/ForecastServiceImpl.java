package sg.ntu.edu.spring_stocks.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import sg.ntu.edu.spring_stocks.exceptions.ForecastNotFoundException;
import sg.ntu.edu.spring_stocks.pojo.Forecast;
import sg.ntu.edu.spring_stocks.repository.ForecastRepository;

@Primary
@Service
public class ForecastServiceImpl implements ForecastService{

    private final Logger logger = LoggerFactory.getLogger(ForecastServiceImpl.class);

    private ForecastRepository forecastRepository;

    public ForecastServiceImpl(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }
    
    @Override
    public Forecast createForecast(Forecast forecast) {
        return forecastRepository.save(forecast);
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Forecast getForecast(String name) {
        Optional<Forecast> optionalForecast = forecastRepository.findById(name);
        if (optionalForecast.isPresent()) {
            Forecast foundForecast = optionalForecast.get();
            logger.info("🟢 getForecast service request was successful");
            return foundForecast;
        }
        logger.error("🔴 getForecast service request was unsuccessful");
        throw new ForecastNotFoundException(name);
        // return forecastRepository.findById(name).orElseThrow(()-> new ForecastNotFoundException(name));
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Forecast> getAllForecasts() {
        List<Forecast> allForecasts = forecastRepository.findAll();
        return (ArrayList<Forecast>) allForecasts;
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Forecast updateForecast(String name, Forecast forecast) {
        Optional<Forecast> optionalForecast = forecastRepository.findById(name);
        if (optionalForecast.isPresent()) {
            Forecast forecastToUpdate = optionalForecast.get();
            forecastToUpdate.setName(forecast.getName());
            forecastToUpdate.setRoe(forecast.getRoe());
            forecastToUpdate.setTargetHigh(forecast.getTargetHigh());
            forecastToUpdate.setTargetLow(forecast.getTargetLow());
            forecastToUpdate.setForecast(forecast.getForecast());
            logger.info("🟢 updateForecast service request was successful");
            return forecastRepository.save(forecastToUpdate);
        } 
        logger.error("🔴 updateForecast service request was unsuccessful");
        throw new ForecastNotFoundException(name);
        // Forecast forecastToUpdate = forecastRepository.findById(name).orElseThrow(()-> new ForecastNotFoundException(name));
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteForecast(String name) {
        Optional<Forecast> optionalForecast = forecastRepository.findById(name);
        if (optionalForecast.isPresent()) {
            forecastRepository.deleteById(name);
            logger.info("🟢 deleteForecast service request was successful");
        } else {
            logger.error("🔴 deleteForecast service request was unsuccessful");
            throw new ForecastNotFoundException(name);
        }
        


        // forecastRepository.deleteById(name);
        // throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
