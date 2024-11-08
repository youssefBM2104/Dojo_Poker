package ps5.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.io.HandScanner;
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

        setUp();

        input = "10Tr ATr 7Tr RTr 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullColor());

        setUp();

        input = "10Tr ATr 7Tr RTr 2Tr";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isFullColor());
        assertEquals(HandPriority.COULEUR, hand.getHandPriority());
        assertEquals(CardValue.A, hand.getHighestCard());

        setUp();

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


        setUp();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isBrelan());
        assertNotEquals(HandPriority.BRELAN, hand.getHandPriority());




    }

    @Test
    void testMaxCardValueFromList() {
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.TEN, hand.maxCardValueFromList());

        setUp();

        input = "7Tr ACa 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);
        assertEquals(CardValue.A, hand.maxCardValueFromList());

    }

    @Test
    void testIsPaire(){
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isPaire());
        assertNotEquals(HandPriority.PAIRE, hand.getHandPriority());


        setUp();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isPaire());
        assertEquals(HandPriority.PAIRE, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());


    }

    @Test
    void testIsFull() {
        String input = "10Tr 10Ca 7Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isFullHouse());
        assertEquals(HandPriority.FULL_HOUSE, hand.getHandPriority());
        assertEquals(CardValue.TEN,hand.getHighestCard());

        setUp();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL_HOUSE, hand.getHandPriority());

        setUp();

        input = "10Tr 10Ca 7Co 8Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL_HOUSE, hand.getHandPriority());

        setUp();


        input = "10Tr 10Ca 10Co 8Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isFullHouse());
        assertNotEquals(HandPriority.FULL_HOUSE, hand.getHandPriority());


    }
    @Test
    void testIsSuite(){
        String input = "2Tr 3Ca 4Co 5Pi 6Pi";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(hand.isSuite());
        assertEquals(HandPriority.SUITE, hand.getHandPriority());
        assertEquals(CardValue.SIX,hand.getHighestCard());

        setUp();

        input = "10Tr 10Ca 10Co 8Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isSuite());
        assertNotEquals(HandPriority.SUITE, hand.getHandPriority());

        setUp();

        input = "VTr DCa RCo APi ACo";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(hand.isSuite());
        assertNotEquals(HandPriority.SUITE, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands1(){
        //Verification d'une paire
        String paire = "10Tr 8Ca 9Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(paire.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(HandPriority.PAIRE, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands2(){
        //Verification d'une fullHouse
        String fullHouse = "10Tr 7Ca 10Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(fullHouse.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);
        hand.runAllPossibleHands();

        assertEquals(HandPriority.FULL_HOUSE, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands3(){
        //Verification d'un brelan
        String brelan = "10Tr 8Ca 10Co 10Pi 7Pi";
        InputStream in = new ByteArrayInputStream(brelan.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(HandPriority.BRELAN, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands4(){
        //Verification d'une fullColor
        String fullColor = "10Tr 5Tr 9Tr 3Tr 7Tr";
        InputStream in = new ByteArrayInputStream(fullColor.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(HandPriority.COULEUR, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands5(){
        //Verification d'une plus haute carte
        String handString = "10Tr 5Ca 9Co 3Tr 7Tr";
        InputStream in = new ByteArrayInputStream(handString.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);
        hand.runAllPossibleHands();
        assertEquals(CardValue.TEN, hand.getHighestCard());
        assertEquals(HandPriority.MAX_CARD_IN_HAND, hand.getHandPriority());
    }
    @Test
    void testRunAllPossibleHands6(){
        String royalFlush = "10Tr VTr DTr RTr ATr";
        InputStream in = new ByteArrayInputStream(royalFlush.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(CardValue.A, hand.getHighestCard());
        assertEquals(HandPriority.ROYALE_FLUSH, hand.getHandPriority());
    }

    @Test
    void testRunAllPossibleHands7(){
        String carre = "10Tr 10Ca 10Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(carre.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(CardValue.TEN, hand.getHighestCard());
        assertEquals(HandPriority.CARRE, hand.getHandPriority());
    }
    @Test
    void testRunAllPossibleHands8(){
        //verification d'une suite
        String suite = "2Tr 3Ca 4Co 5Pi 6Pi";
        InputStream in = new ByteArrayInputStream(suite.getBytes());
        System.setIn(in);

        handScanner.handScan(hand, 1);

        hand.runAllPossibleHands();
        assertEquals(CardValue.SIX, hand.getHighestCard());
        assertEquals(HandPriority.SUITE, hand.getHandPriority());
    }
    @Test
    void testIsRoyalFlush() {
        String input = "10Tr VTr DTr RTr ATr";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertTrue(hand.isRoyalFlush());
        assertEquals(HandPriority.ROYALE_FLUSH, hand.getHandPriority());
        assertEquals(CardValue.A, hand.getHighestCard());

        setUp();

        input = "10Tr VTr DTr RTr 9Tr";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isRoyalFlush());
        assertNotEquals(HandPriority.ROYALE_FLUSH, hand.getHandPriority());

        setUp();

        input = "10Tr 9Tr 8Tr 7Tr 6Tr";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isRoyalFlush());
        assertNotEquals(HandPriority.ROYALE_FLUSH, hand.getHandPriority());

        setUp();

        input = "10Tr VCa DTr RCo ACo";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isRoyalFlush());
        assertNotEquals(HandPriority.ROYALE_FLUSH, hand.getHandPriority());

        setUp();

        input = "3Tr 5Ca 7Pi 9Co DCo";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isRoyalFlush());
        assertEquals(HandPriority.MAX_CARD_IN_HAND, hand.getHandPriority());
    }

    @Test
    void testIsCarre(){
        String input = "10Tr 10Ca 10Co 10Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertTrue(hand.isCarre());
        assertEquals(HandPriority.CARRE, hand.getHandPriority());
        assertEquals(CardValue.TEN, hand.getHighestCard());

        setUp();

        input = "10Tr 10Ca 9Co 10Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isCarre());
        assertNotEquals(HandPriority.CARRE, hand.getHandPriority());

        setUp();

        input = "VTr VCa VCo VPi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertTrue(hand.isCarre());
        assertEquals(HandPriority.CARRE, hand.getHandPriority());
        assertEquals(CardValue.V, hand.getHighestCard());

        setUp();

        input = "10Tr 10Ca 9Co 9Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isCarre());
        assertNotEquals(HandPriority.CARRE, hand.getHandPriority());

        setUp();

        input = "10Tr 10Ca 10Co 8Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        assertFalse(hand.isCarre());
        assertNotEquals(HandPriority.CARRE, hand.getHandPriority());
    }
    @Test
    void testNextHighestCard_FullHouse() {
        String input = "10Tr 10Ca 10Co 8Pi 8Co";  // Full House: 10s over 8s
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        hand.isFullHouse();  // Confirms this is identified as a Full House
        assertEquals(CardValue.TEN, hand.getHighestCard());

        hand.nextHighestCard();
        assertEquals(CardValue.EIGHT, hand.getHighestCard(), "Expected the next highest card to be the second pair in Full House");
    }

    @Test
    void testNextHighestCard_DeuxPaires() {
        String input = "10Tr 10Ca 8Co 8Pi 7Co";  // Two pairs: 10s and 8s
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        hand.isDoublePaire();
        assertEquals(CardValue.TEN, hand.getHighestCard());

        hand.nextHighestCard();
        assertEquals(CardValue.EIGHT, hand.getHighestCard(), "Expected the next highest card to be the second pair in Two Pairs");

        hand.nextHighestCard();
        assertEquals(CardValue.SEVEN, hand.getHighestCard(), "Expected the next highest card after removing pairs");
    }

    @Test
    void testNextHighestCard_MaxCardInHand() {
        String input = "10Tr 9Ca 8Co 7Pi 6Co";  // Single cards, no pairs or full house
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);


        hand.runAllPossibleHands();
        hand.nextHighestCard();
        assertEquals(CardValue.NINE, hand.getHighestCard(), "Expected the highest card to be 9 after removing the 10");

        hand.nextHighestCard();
        assertEquals(CardValue.EIGHT, hand.getHighestCard(), "Expected the highest card to be 8 after removing the 9");
    }

    @Test
    void testRemoveCardFromList() {
        String input = "10Tr 10Ca 7Co 7Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        hand.removeCardFromList(CardValue.TEN);
        assertFalse(hand.getCardList().stream().anyMatch(card -> card.getCardValue() == CardValue.TEN), "Expected all 10s to be removed from the hand");
    }

    @Test
    void testRemovePairFromHashMap() {
        String input = "10Tr 10Ca 8Co 8Pi 2Co";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        hand.removePairFromHashMap(CardValue.TEN);
        assertFalse(hand.hashMapFromHand.containsKey(CardValue.TEN), "Expected the pair of 10s to be removed from the map");
    }

    @Test
    void testSetHighestCardFromNextPair() {
        String input = "10Tr 10Ca 8Co 8Pi 2Co";  // Two pairs
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        handScanner.handScan(hand, 1);

        hand.setHighestCardFromNextPair();
        assertEquals(CardValue.TEN, hand.getHighestCard(), "Expected the highest card to be set to 10 as it is the highest pair");
    }
}