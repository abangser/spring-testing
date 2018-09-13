package example;

import example.weather.WeatherClient;
import example.weather.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    private final WeatherClient weatherClient;

    @Autowired
    public ExampleController(final WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/weather")
    public String weather() {
        return weatherClient.fetchWeather()
                .map(WeatherResponse::getSummary)
                .orElse("Sorry, I couldn't fetch the weather for you :(");
    }

    @GetMapping("/weather/{time}")
    public String weatherByTime(@PathVariable final String time) {
        return weatherClient.fetchWeather(time)
                .map(WeatherResponse::getSummary)
                .orElse("Sorry, I couldn't fetch the weather for you at that specific time :(");
    }
}
