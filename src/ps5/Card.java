package ps5;

public class Card {
    private final String cardColor;
    private final String cardValue;
    private int cardIntValue;


    public Card(String cardColor, String cardValue) {
        this.cardColor = cardColor;
        this.cardValue = cardValue;
        this.convStringValueToInt();
    }

    public void convStringValueToInt() {
        switch (cardValue) {
            case "V" -> cardIntValue = 11;
            case "D" -> cardIntValue = 12;
            case "R" -> cardIntValue = 13;
            case "A" -> cardIntValue = 14;
            default -> cardIntValue = Integer.parseInt(cardValue);
        }
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getCardValue() {
        return cardValue;
    }

    public int getCardIntValue() {
        return cardIntValue;
    }

}
