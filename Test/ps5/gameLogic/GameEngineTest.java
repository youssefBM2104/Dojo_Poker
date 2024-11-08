package ps5.gameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.io.HandScanner;
import ps5.player.Hand;
import ps5.player.enums.HandId;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {
    Hand hand1;
    Hand hand2;
    GameEngine gameEngine;
    HandScanner handScanner;

    @BeforeEach
    void setUp() {
        hand1 = new Hand();
        hand2 = new Hand();
        gameEngine = new GameEngine();
        handScanner = new HandScanner();
    }

    @Test
    void testWhoWon_HighCard() {
        String input1 = "RTr 10Co 7Pi 4Ca 2Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "DCo 9Ca 6Tr 3Pi 5Tr";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_PairVsHighCard() {
        String input1 = "VTr VCo 7Pi 4Ca 2Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "RCa 9Co 6Tr 3Pi 5Tr";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();
        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }
    //TODO: uncomment tests when all methods are implemented plus check for more scenarios if needed
    @Test
    void testWhoWon_TwoPairs() {
        String input1 = "10Tr 10Ca 8Co 8Pi 2Ca";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "9Co 9Pi 7Tr 7Ca 3Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_FullHouseVsCouleur() {
        String input1 = "10Tr 10Ca 10Co 8Pi 8Ca";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "RCa 2Ca 9Ca 6Ca 3Ca";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();
        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_QuinteFlushRoyale() {
        String input1 = "ATr RTr DTr VTr 10Tr";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "APi RPi DPi VPi 10Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.DEFAULT, gameEngine.whoWon(hand1, hand2));
    }
    @Test
    void testWhoWon_Pair_SamePairDifferentKickers() {
        String input1 = "10Tr 10Ca RCo 4Pi 3Ca";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "10Pi 10Co DTr ACa 2Tr";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_2, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_TwoPairs_SameHighPairDifferentLowPair() {
        String input1 = "RTr RCa 8Co 8Pi 2Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        Hand hand2 = new Hand();
        String input2 = "RPi RCo 7Tr 7Ca 3Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_Couleur_SameCouleurDifferentHighestCard() {
        String input1 = "ATr 10Tr 8Tr 5Tr 3Tr";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "ATr 10Tr 8Tr 4Tr 5Tr";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_2, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_FullHouse_SameTripleDifferentPair() {
        String input1 = "10Tr 10Ca 10Co 8Pi 8Ca";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "10Pi 10Tr 10Ca 7Co 7Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }

//    @Test
//    void testWhoWon_QuinteFlush_SameQuinteFlushDifferentHighestCard() {
//        String input1 = "RTr DTr VTr 10Tr 9Tr";
//        System.setIn(new ByteArrayInputStream(input1.getBytes()));
//        handScanner.handScan(hand1, 1);
//
//        String input2 = "DCo VCo 10Co 9Co 8Co";
//        System.setIn(new ByteArrayInputStream(input2.getBytes()));
//        handScanner.handScan(hand2, 2);
//
//        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
//    }

    @Test
    void testWhoWon_TwoPairs_SamePairsDifferentRemainingCard() {
        String input1 = "8Tr 8Ca 4Co 4Pi 2Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "8Pi 8Co 4Tr 4Ca 3Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();

        assertEquals(HandId.HAND_2, gameEngine.whoWon(hand1, hand2));
    }

    @Test
    void testWhoWon_Carre_SameFourDifferentKicker() {
        String input1 = "10Tr 10Ca 10Co 10Pi 2Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "9Tr 9Ca 9Co 9Pi 3Ca";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }
    @Test
    void testWhoWon_Carre_SameFourSameKicker() {
        String input1 = "10Tr 10Ca 10Co 10Pi 4Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "10Pi 10Tr 10Ca 10Co 3Pi";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }
    @Test
    void testWhoWon_Brelan_DifferentValues() {
        String input1 = "10Tr 10Ca 10Co 5Pi 3Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "9Tr 9Ca 9Co 4Pi 2Co";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }
    @Test
    void testWhoWon_Brelan_SameValueDifferentKicker() {
        String input1 = "10Tr 10Ca 10Co 5Pi 4Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "10Pi 10Tr 10Ca 3Co 2Co";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.HAND_1, gameEngine.whoWon(hand1, hand2));
    }
    @Test
    void testWhoWon_Brelan_SameValueSameKicker() {
        String input1 = "10Tr 10Ca 10Co 5Pi 4Co";
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        String input2 = "10Pi 10Tr 10Ca 5Co 4Co";
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        assertEquals(HandId.DEFAULT, gameEngine.whoWon(hand1, hand2));
    }
}