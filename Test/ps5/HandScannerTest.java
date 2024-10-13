package ps5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HandScannerTest {
    private HandScanner handScanner;
    private Hand hand;

    @BeforeEach
    public void setUp() {
        handScanner = new HandScanner();
        hand = new Hand();
    }

    @Test
    void getCardFromString() {
        Card card = handScanner.getCardFromString("10Tr");
        assertNotNull(card);
        assertEquals("Tr", card.getCardColor());
        assertEquals("10", card.getCardValue());
        card = handScanner.getCardFromString("ACa");
        assertNotNull(card);
        assertEquals("Ca", card.getCardColor());
        assertEquals("A", card.getCardValue());
        card = handScanner.getCardFromString("7Co");
        assertNotNull(card);
        assertEquals("Co", card.getCardColor());
        assertEquals("7", card.getCardValue());
        card = handScanner.getCardFromString("RPi");
        assertNotNull(card);
        assertEquals("Pi", card.getCardColor());
        assertEquals("R", card.getCardValue());
    }

    @Test
    void testHandInitialization() {

        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Co"));
        hand.addCardToHand(handScanner.getCardFromString("RPi"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertEquals(5, hand.getCardList().size());

        assertEquals(new Card("Tr", "10"), hand.getCardList().get(0));
        assertEquals(new Card("Ca", "A"), hand.getCardList().get(1));
        assertEquals(new Card("Co", "7"), hand.getCardList().get(2));
        assertEquals(new Card("Pi", "R"), hand.getCardList().get(3));
        assertEquals(new Card("Co", "2"), hand.getCardList().get(4));
    }

    @Test
    void testHandScan() {
        String input = "10Tr ACa 7Co RPi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(5, hand.getCardList().size());
        assertEquals(new Card("Tr", "10"), hand.getCardList().get(0));
        assertEquals(new Card("Ca", "A"), hand.getCardList().get(1));
        assertEquals(new Card("Co", "7"), hand.getCardList().get(2));
        assertEquals(new Card("Pi", "R"), hand.getCardList().get(3));
        assertEquals(new Card("Co", "2"), hand.getCardList().get(4));
    }

    @Test
    void testHandScan_WithLessCards() {
        String input = "10Tr ACa";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(2, hand.getCardList().size());
        assertEquals(new Card("Tr", "10"), hand.getCardList().get(0));
        assertEquals(new Card("Ca", "A"), hand.getCardList().get(1));
    }
}