package ps5;

import java.util.Objects;

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

    public boolean supTo(Card card){
        return this.getCardIntValue() > card.getCardIntValue();
    }

    public boolean areValuesEquals(Card card){
        return this.getCardIntValue() == card.getCardIntValue();
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if(o == null || getClass()!=o.getClass()){
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(cardColor, card.cardColor) && Objects.equals(cardValue, card.cardValue) && cardIntValue == card.cardIntValue;
    }

    @Override
    public int hashCode(){
        return Objects.hash(cardColor,cardValue,cardIntValue);
    }
}
