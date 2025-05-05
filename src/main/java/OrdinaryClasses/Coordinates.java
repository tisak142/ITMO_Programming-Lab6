package OrdinaryClasses;

public class Coordinates {
    private Double x;
    private long y;

    public Coordinates(Double x, long y) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        } else {
            this.x = x;
        }
        if (y > 860) {
            throw new IllegalArgumentException("Y cannot be greater than 860");
        } else {
            this.y = y;
        }
    }

    public void setX(Double x) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        } else {
            this.x = x;
        }
    }

    public void setY(long y) {
        if (y > 860) {
            throw new IllegalArgumentException("Y cannot be greater than 860");
        } else {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
