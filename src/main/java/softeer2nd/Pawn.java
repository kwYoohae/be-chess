package softeer2nd;

public class Pawn {

    private final String WHITE_COLOR = "white";

    private final String color;

    public Pawn() {
        this.color = WHITE_COLOR;
    }
    public Pawn(final String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }
}
