package softeer2nd;

public class Pawn {

    private final String color;

    public Pawn(final String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = "white";
    }

    public String getColor() {
        return color;
    }
}
