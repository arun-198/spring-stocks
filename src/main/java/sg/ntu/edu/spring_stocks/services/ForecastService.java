package sg.ntu.edu.spring_stocks.services;

import java.util.ArrayList;

import sg.ntu.edu.spring_stocks.pojo.Forecast;

public interface ForecastService {

    Forecast createForecast(Forecast forecast);
    Forecast getForecast(String name);
    ArrayList<Forecast> getAllForecasts();
    Forecast updateForecast(String name, Forecast forecast);
    void deleteForecast(String name);
}
