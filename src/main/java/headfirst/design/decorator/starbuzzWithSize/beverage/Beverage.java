package headfirst.design.decorator.starbuzzWithSize.beverage;

public abstract class Beverage {
    public enum Size {
        TALL,
        GRANDE,
        VENTI;
        public boolean isTall() {
            return this == TALL;
        }

        public boolean isGrande() {
            return this == GRANDE;
        }

        public boolean isVenti() {
            return this == VENTI;
        }
    };
    String description = "제목없음";
    Size size = Size.TALL;

    public String getDescription() {
        return description;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public abstract double cost();
}
