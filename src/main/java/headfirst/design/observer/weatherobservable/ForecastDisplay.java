package headfirst.design.observer.weatherobservable;

import java.util.Observable;
import java.util.Observer;

public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f;
    private float lastPressure;

    public ForecastDisplay(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.print("기상예보: ");
        if (currentPressure > lastPressure) {
            System.out.println("날씨가 좋아지고 있습니다!");
        } else if (currentPressure == lastPressure) {
            System.out.println("지금과 비슷할 것 같습니다");
        } else if (currentPressure < lastPressure) {
            System.out.println("쌀쌀하며 비가 올 것 같습니다");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData) o;
            lastPressure = currentPressure;
            currentPressure = weatherData.getPressure();
            display();
        }
    }
}
