package ps5.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.player.Card;
import ps5.player.Hand;
import ps5.player.enums.*;

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
        assertEquals(CardColor.TR, card.getCardColor());
        assertEquals(CardValue.TEN, card.getCardValue());

        card = handScanner.getCardFromString("ACa");
        assertNotNull(card);
        assertEquals(CardColor.CA, card.getCardColor());
        assertEquals(CardValue.A, card.getCardValue());

        card = handScanner.getCardFromString("7Co");
        assertNotNull(card);
        assertEquals(CardColor.CO, card.getCardColor());
        assertEquals(CardValue.SEVEN, card.getCardValue());

        card = handScanner.getCardFromString("RPi");
        assertNotNull(card);
        assertEquals(CardColor.PI, card.getCardColor());
        assertEquals(CardValue.R, card.getCardValue());
    }

    @Test
    void testHandInitialization() {
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Co"));
        hand.addCardToHand(handScanner.getCardFromString("RPi"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertEquals(5, hand.getCardList().size());

        assertEquals(new Card(CardColor.TR, CardValue.TEN), hand.getCardList().get(0));
        assertEquals(new Card(CardColor.CA, CardValue.A), hand.getCardList().get(1));
        assertEquals(new Card(CardColor.CO, CardValue.SEVEN), hand.getCardList().get(2));
        assertEquals(new Card(CardColor.PI, CardValue.R), hand.getCardList().get(3));
        assertEquals(new Card(CardColor.CO, CardValue.TWO), hand.getCardList().get(4));
    }

    @Test
    void testHandScan() {
        String input = "10Tr ACa 7Co RPi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertTrue(handScanner.handScan(hand, 1));

        assertEquals(5, hand.getCardList().size());
        assertEquals(new Card(CardColor.TR, CardValue.TEN), hand.getCardList().get(0));
        assertEquals(new Card(CardColor.CA, CardValue.A), hand.getCardList().get(1));
        assertEquals(new Card(CardColor.CO, CardValue.SEVEN), hand.getCardList().get(2));
        assertEquals(new Card(CardColor.PI, CardValue.R), hand.getCardList().get(3));
        assertEquals(new Card(CardColor.CO, CardValue.TWO), hand.getCardList().get(4));

        input = "10Tr 10Tr 7Co RPi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(handScanner.handScan( new Hand(),1));
    }

    @Test
    void testHandScan_WithLessCards() {
        String input = "10Tr ACa";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertFalse(handScanner.handScan(hand, 1));


        assertEquals(0, hand.getCardList().size());
    }

}
