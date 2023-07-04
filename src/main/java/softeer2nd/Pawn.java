package softeer2nd;

public class Pawn {

    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    private final String color;

    public Pawn() {
        this(WHITE_COLOR);
    }
    public Pawn(final String color) {
        this.color = color;
    }


    public String getColor() {
        return color;
    }
}
