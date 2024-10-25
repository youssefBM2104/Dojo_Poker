package ps5.player;

import ps5.player.enums.CardColor;
import ps5.player.enums.CardValue;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {


    @org.junit.jupiter.api.Test
    void testComparaison(){
        Card card1 = new Card(CardColor.TR, CardValue.R);
        Card card2 = new Card(CardColor.TR, CardValue.SEVEN);
        Card card3 = new Card(CardColor.TR, CardValue.A);
        Card card4 = new Card(CardColor.PI, CardValue.R);

        assertNotEquals(card1, card4);
        assertTrue(card1.areValuesEquals(card4));
        assertTrue(card3.supTo(card4));
        assertFalse(card1.supTo(card3));
        assertFalse(card2.supTo(card1));
        assertTrue(card3.supTo(card2));
        assertFalse(card3.supTo(card3));

    }



}