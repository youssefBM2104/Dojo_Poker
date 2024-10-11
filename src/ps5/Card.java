package ps5;

public class Card {
    private final String cardColor;
    private final String cardSuit;

    public Card(String cardColor, String cardSuit) {
        this.cardColor = cardColor;
        this.cardSuit = cardSuit;
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getCardSuit() {
        return cardSuit;
    }

}
