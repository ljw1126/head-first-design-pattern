package headfirst.design.factory.simplefactory;

public class ClamPizza extends Pizza{
    @Override
    void prepare() {
        super.prepare();
        System.out.println("Add clam");
    }

    @Override
    void bake() {
        super.bake();
    }

    @Override
    void cut() {
        super.cut();
    }

    @Override
    void box() {
        super.box();
    }
}
