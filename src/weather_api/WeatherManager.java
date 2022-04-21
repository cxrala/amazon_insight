package weather_api;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WeatherManager {

    public static void main(String[] args) {
        WeatherFetcher weatherFetcher = new WeatherFetcher();
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(weatherFetcher, 0, 1, TimeUnit.SECONDS);
    }

}
