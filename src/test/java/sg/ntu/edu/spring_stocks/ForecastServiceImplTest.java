package sg.ntu.edu.spring_stocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

import sg.ntu.edu.spring_stocks.exceptions.ForecastNotFoundException;
import sg.ntu.edu.spring_stocks.pojo.Forecast;
import sg.ntu.edu.spring_stocks.repository.ForecastRepository;
import sg.ntu.edu.spring_stocks.services.ForecastServiceImpl;

@SpringBootTest
public class ForecastServiceImplTest {
    @Mock
    private ForecastRepository forecastRepository;

    @InjectMocks
    ForecastServiceImpl forecastServiceImpl;

    @Test
    public void createForecastTest() {

        Forecast forecast = Forecast.builder().name("AMZN").targetHigh(265.00f).targetLow(180.00f).roe(21.2f).
        forecast("Strong buy").build();

        when((forecastRepository.save(forecast))).thenReturn(forecast);

        Forecast savedForecast = forecastServiceImpl.createForecast(forecast);

        assertEquals(forecast, savedForecast, "The saved forecast should be the same as the new forecast.");
        verify(forecastRepository, times(1)).save(forecast);
    }

    @Test
    public void getForecastTest() {

        Forecast forecast = Forecast.builder().name("AMZN").targetHigh(265.00f).targetLow(180.00f).roe(21.2f).
        forecast("Strong buy").build();

        when((forecastRepository.findById(forecast.getName()))).thenReturn(Optional.of(forecast));

        Forecast retrievedForecast = forecastServiceImpl.getForecast(forecast.getName());

        assertEquals(forecast, retrievedForecast);
    }

    @Test
    public void getForecastNotFoundTest() {

        String name = "AMZN";
        when(forecastRepository.findById(name)).thenReturn(Optional.empty());

        assertThrows(ForecastNotFoundException.class, () -> forecastServiceImpl.getForecast(name));
    }

    @Test 
    public void getAllForecastsTest() {

        Forecast forecast = Forecast.builder().name("AMZN").targetHigh(265.00f).targetLow(180.00f).roe(21.2f).
        forecast("Strong buy").build();

        List<Forecast> allForecasts = Arrays.asList(forecast);

        when(forecastRepository.findAll()).thenReturn(new ArrayList<>(allForecasts));

        ArrayList<Forecast> retrievedAllForecasts = forecastServiceImpl.getAllForecasts();

        assertEquals(allForecasts, retrievedAllForecasts);

    }
    
}
