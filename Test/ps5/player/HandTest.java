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
        String input = "10Tr ACa 7Co RPi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullColor());

        hand = new Hand();

        input = "10Tr ATr 7Tr RTr 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullColor());

        hand = new Hand();

        input = "10Tr ATr 7Tr RTr 2Tr";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isFullColor());
        assertEquals(HandPriority.COULEUR, hand.getHandPriority());
        assertEquals(CardValue.A, hand.getHighestCard());

        hand = new Hand();

        input = "10Tr 9Ca 7Ca RCa 2Ca";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullColor());
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

    @Test
    void testRunAllPossibleHands1(){
        //Verification d'une paire
        String paire = "10Tr 8Ca 9Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(paire.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(HandPriority.PAIRE, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands2(){
        //Verification d'une fullHouse
        String fullHouse = "10Tr 7Ca 10Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(fullHouse.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(HandPriority.FULL, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands3(){
        //Verification d'un brelan
        String brelan = "10Tr 8Ca 10Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(brelan.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(HandPriority.BRELAN, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands4(){
        //Verification d'une fullColor
        String fullColor = "10Tr 5Tr 9Tr 3Tr 7Tr";
        InputStream in = new ByteArrayInputStream(fullColor.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(HandPriority.COULEUR, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands5(){
        //Verification d'un plus haute carte
        String fullColor = "10Tr 5Ca 9Co 3Tr 7Tr";
        InputStream in = new ByteArrayInputStream(fullColor.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        assertEquals(hand.getHighestCard(), CardValue.TEN); //TODO: HIGHEST CARD IS NEVER EXECUTED
        assertEquals(HandPriority.MAX_CARD_IN_HAND, hand.getHandPriority());
    }
}