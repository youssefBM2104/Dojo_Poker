package ps5.gameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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

    @ParameterizedTest
    @CsvSource({
            "'RTr 10Co 7Pi 4Ca 2Co', 'DCo 9Ca 6Tr 3Pi 5Tr', HAND_1",

            "'VTr VCo 7Pi 4Ca 2Co', 'RCa 9Co 6Tr 3Pi 5Tr', HAND_1",

            " '9Co 9Pi 7Tr 7Ca 3Pi', '10Tr 10Ca 8Co 8Pi 2Ca',HAND_2",

            "'10Tr 10Ca 10Co 8Pi 8Ca', 'RCa 2Ca 9Ca 6Ca 3Ca', HAND_1",

            "'ATr RTr DTr VTr 10Tr', 'APi RPi DPi VPi 10Pi', DEFAULT",

            "'10Tr 10Ca RCo 4Pi 3Ca', '10Pi 10Co DTr ACa 2Tr', HAND_2",

            "'2Tr 3Tr 4Tr 5Tr 6Tr', '2Pi 3Pi 4Pi 5Pi 6Pi', DEFAULT",

            "'2Tr 3Tr 4Tr 5Tr 6Tr', '5Pi 6Pi 7Pi 8Pi 9Pi', HAND_2",

            "'RTr RCa 8Co 8Pi 2Co', 'RPi RCo 7Tr 7Ca 3Pi', HAND_1",

            "'ATr 10Tr 8Tr 5Tr 3Tr', 'ATr 10Tr 8Tr 4Tr 5Tr', HAND_2",

            "'10Tr 10Ca 10Co 8Pi 8Ca', '10Pi 10Tr 10Ca 7Co 7Pi', HAND_1",

            "'RTr DTr VTr 10Tr 9Tr', 'DCo VCo 10Co 9Co 8Co', HAND_1",

            "'8Tr 8Ca 4Co 4Pi 2Co', '8Pi 8Co 4Tr 4Ca 3Pi', HAND_2",

            "'10Tr 10Ca 10Co 10Pi 2Co', '9Tr 9Ca 9Co 9Pi 3Ca', HAND_1",

            " '10Pi 10Tr 10Ca 10Co 3Pi', '10Tr 10Ca 10Co 10Pi 4Co',HAND_2",

            "'10Tr 10Ca 10Co 5Pi 3Co', '9Tr 9Ca 9Co 4Pi 2Co', HAND_1",

            "'10Tr 10Ca 10Co 5Pi 4Co', '10Pi 10Tr 10Ca 3Co 2Co', HAND_1",

            "'10Tr 10Ca 10Co 5Pi 4Co', '10Pi 10Tr 10Ca 5Co 4Co', DEFAULT"
    })
    void testWhoWon(String input1, String input2, HandId expectedWinner) {
        // Set input and scan for Player 1's hand
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        // Set input and scan for Player 2's hand
        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        // Determine hand strengths for both players
        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();

        boolean[] flag={true};
        // Assert the expected winner
        assertEquals(expectedWinner, gameEngine.whoWon(hand1, hand2 ,flag));
    }

}