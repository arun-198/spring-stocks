package sg.ntu.edu.spring_stocks.exceptions;

public class ForecastNotFoundException extends RuntimeException{
    public ForecastNotFoundException(String name) {
        super("Could not find stock with name: " + name);
    }
    
}
