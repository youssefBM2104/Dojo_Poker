package ps5.player.enums;

/**
 * All the valid Card values
 */
public enum CardValue {
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    V,
    D,
    R,
    A;

    @Override
    public String toString() {
        return switch (this) {
            case TWO -> "2";
            case THREE -> "3";
            case FOUR -> "4";
            case FIVE -> "5";
            case SIX -> "6";
            case SEVEN -> "7";
            case EIGHT -> "8";
            case NINE -> "9";
            case TEN -> "10";
            case V -> "V";
            case D -> "D";
            case R -> "R";
            case A -> "A";
        };
    }
}
