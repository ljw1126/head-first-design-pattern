package headfirst.design.observer.weatherobservable;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("현재 상태 : 온도 " + temperature + "F, 습도 " + humidity + "%");
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData) { // 단점. 형 체크가 필요하네
            WeatherData weatherData = (WeatherData)  o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
