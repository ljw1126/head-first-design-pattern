package headfirst.design.observer.weatherobservable;

// java.util 패키지에 있는 Observable, Observer 자바 9버전 부터 deprecated
public class WeatherStation {
    public static void main(String[] args) {
        // 주제 (subject)
        WeatherData weatherData = new WeatherData();

        // 옵저버
        CurrentConditionsDisplay currentConditionsDisplay
                = new CurrentConditionsDisplay(weatherData);
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        HeatIndexDisplay heatIndexDisplay = new HeatIndexDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
