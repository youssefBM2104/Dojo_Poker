package ps5.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.io.HandScanner;
import ps5.player.enums.CardColor;
import ps5.player.enums.CardValue;
import ps5.player.enums.HandPriority;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private HandScanner handScanner;
    private Hand hand;
    @BeforeEach
    void setUp() {
        handScanner = new HandScanner();
        hand = new Hand();
    }


    @Test
    void testIsFullColor() {
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Co"));
        hand.addCardToHand(handScanner.getCardFromString("RPi"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertFalse(hand.isFullColor());
        assertEquals(HandPriority.MAX_CARD_IN_HAND,hand.getHandPriority());


        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertFalse(hand.isFullColor());
        assertEquals(HandPriority.MAX_CARD_IN_HAND,hand.getHandPriority());



        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Tr"));

        assertTrue(hand.isFullColor());
        assertEquals(HandPriority.COULEUR,hand.getHandPriority());
        assertEquals(CardValue.A, hand.getHighestCard());


        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("9Ca"));
        hand.addCardToHand(handScanner.getCardFromString("7Ca"));
        hand.addCardToHand(handScanner.getCardFromString("RCa"));
        hand.addCardToHand(handScanner.getCardFromString("2Ca"));

        assertFalse(hand.isFullColor());
        assertEquals(HandPriority.MAX_CARD_IN_HAND,hand.getHandPriority());

    }


    @Test
    void testIsBrelan(){
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isBrelan());
        assertEquals(HandPriority.BRELAN, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());


        hand = new Hand();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isBrelan());
        assertNotEquals(HandPriority.BRELAN, hand.getHandPriority());


        hand = new Hand();

        input = "10Tr 10Ca";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isBrelan());
        assertNotEquals(HandPriority.BRELAN, hand.getHandPriority());

    }

    @Test
    void testMaxCardValue() {
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.TEN, hand.maxCardValue());

        hand = new Hand();

        input = "7Tr ACa 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.A, hand.maxCardValue());

        hand = new Hand();

        input = "7Tr ACa";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.A, hand.maxCardValue());

        hand = new Hand();

        input = "7Tr DCa VCo RPi";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.R, hand.maxCardValue());

    }

    @Test
    void testIsPaire(){
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isPaire());
        assertNotEquals(HandPriority.PAIRE, hand.getHandPriority());


        hand = new Hand();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isPaire());
        assertEquals(HandPriority.PAIRE, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());


        hand = new Hand();

        input = "10Tr 10Ca";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isPaire());
        assertEquals(HandPriority.PAIRE, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());

        hand = new Hand();

        input = "10Tr";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isPaire());
        assertNotEquals(HandPriority.PAIRE, hand.getHandPriority());

        hand = new Hand();

        input = "10Tr 9Ca 8Ca 7Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isPaire());
        assertNotEquals(HandPriority.PAIRE, hand.getHandPriority());

    }

    @Test
    void testIsFull() {
        String input = "10Tr 10Ca 7Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isFullHouse());
        assertEquals(HandPriority.FULL, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());

        hand = new Hand();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL, hand.getHandPriority());

        hand = new Hand();

        input = "10Tr 10Ca 7Co 8Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL, hand.getHandPriority());

        hand = new Hand();


        input = "10Tr 10Ca 10Co 8Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL, hand.getHandPriority());


    }


}