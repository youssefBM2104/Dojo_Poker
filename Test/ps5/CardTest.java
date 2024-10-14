package ps5;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @org.junit.jupiter.api.Test
    void testConvStringValueToInt() {
        Card card1 = new Card("Tr", "R");
        Card card2 = new Card("Tr", "A");
        Card card3 = new Card("Tr", "7");
        Card card4 = new Card("Tr", "D");
        Card card5 = new Card("Tr", "V");

        assertEquals(13, card1.getCardIntValue());
        assertEquals(14, card2.getCardIntValue());
        assertEquals(7, card3.getCardIntValue());
        assertEquals(12, card4.getCardIntValue());
        assertEquals(11, card5.getCardIntValue());

    }

    @org.junit.jupiter.api.Test
    void testComparaison(){
        Card card1 = new Card("Tr", "R");
        Card card2 = new Card("Tr", "7");
        Card card3 = new Card("Tr", "A");
        Card card4 = new Card("Pi", "R");

        assertFalse(card1.equals(card4));
        assertTrue(card1.areValuesEquals(card4));
        assertTrue(card3.supTo(card4));
        assertFalse(card1.supTo(card3));
        assertFalse(card2.supTo(card1));
        assertTrue(card3.supTo(card2));
        assertFalse(card3.supTo(card3));

    }


}