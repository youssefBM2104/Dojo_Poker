package ps5.player;

import ps5.player.enums.CardColor;
import ps5.player.enums.CardValue;

import java.util.Objects;

/**
 * Represents a playing card with a specific color and value.
 */
public class Card {
    private final CardColor cardColor;
    private final CardValue cardValue;

    /**
     * Constructs a card with the specified color and value.
     *
     * @param cardColor the color of the card
     * @param cardValue the value of the card
     */
    public Card(CardColor cardColor, CardValue cardValue) {
        this.cardColor = cardColor;
        this.cardValue = cardValue;
    }

    /**
     * @return the color of the card
     */
    public CardColor getCardColor() {
        return cardColor;
    }

    /**
     * @return the value of the card
     */
    public CardValue getCardValue() {
        return cardValue;
    }


    /**
     * Determines if this card has a higher value than the specified card
     *
     * @param card the card to compare with the current card
     * @return true if the current card's value is higher than the specified one
     */
    public boolean supTo(Card card) {
        return this.cardValue.ordinal() > card.cardValue.ordinal();
    }

    /**
     * Checks if this card has the same color as the specified card.
     *
     * @param card the card to compare with the current card
     * @return
     */

    public boolean isSameColor(Card card) {
        return this.getCardColor().equals(card.getCardColor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(cardColor, card.cardColor) && Objects.equals(cardValue, card.cardValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardColor, cardValue);
    }

}
