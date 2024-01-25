package headfirst.design.observer.weather;

public class StatisticsDisplay implements Observer, DisplayElement{
    private WeatherData weatherData;
    private float maxTemp = 0.0f;
    private float minTemp = 200;
    private float tempSum = 0.0f;
    private int numReadings;

    public StatisticsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings)
                + "/" + maxTemp + "/" + minTemp);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        tempSum += temp;
        numReadings += 1;

        if(temp > maxTemp) { // 최대 온도 갱신
            maxTemp = temp;
        }

        if(temp < minTemp) { // 최소 온도 갱신
            maxTemp = temp;
        }

        display();
    }
}
