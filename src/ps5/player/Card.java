package ps5.player;

import ps5.player.enums.CardColor;
import ps5.player.enums.CardValue;

import java.util.Objects;

public class Card {
    private final CardColor cardColor;
    private final CardValue cardValue;


    public Card(CardColor cardColor, CardValue cardValue) {
        this.cardColor = cardColor;
        this.cardValue = cardValue;
    }


    public CardColor getCardColor() {
        return cardColor;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public boolean supTo(Card card){
        return this.cardValue.ordinal() > card.cardValue.ordinal();
    }

    public boolean isSameValue(Card card){
        return this.cardValue.ordinal() == card.cardValue.ordinal();
    }

    public boolean isSameColor(Card card){
        return this.getCardColor().equals(card.getCardColor());
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
        return Objects.equals(cardColor, card.cardColor) && Objects.equals(cardValue, card.cardValue);
    }

    @Override
    public int hashCode(){
        return Objects.hash(cardColor,cardValue);
    }

}
