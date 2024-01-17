package headfirst.design.factory.factorymethod;

public class ChicagoPizzaStore extends PizzaStore{
    @Override
    protected Pizza createPizza(String type) {

        if(type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if(type.equals("veggie")) {
            return new ChicagoStyleVeggiePizza();
        } else {
            return null;
        }
    }
}
