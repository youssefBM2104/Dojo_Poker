package ps5.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ps5.gameLogic.GameEngine;
import ps5.player.Hand;
import ps5.player.enums.HandId;

import java.io.ByteArrayInputStream;


import static org.junit.jupiter.api.Assertions.*;

class OutputHandlerTest {

    private OutputHandler outputHandler;
    private Hand hand1;
    private Hand hand2;
    private HandScanner handScanner;
    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        outputHandler = new OutputHandler();
        hand1 = new Hand();
        hand2 = new Hand();
        handScanner = new HandScanner();
        gameEngine = new GameEngine();
    }

    @ParameterizedTest
    @CsvSource({
            "'RTr 10Co 7Pi 4Ca 2Co', 'DCo 9Ca 6Tr 3Pi 5Tr','Main 1 gagne avec la plus haute carte R'",

            "'VTr VCo 7Pi 4Ca 2Co', 'RCa 9Co 6Tr 3Pi 5Tr', 'Main 1 gagne avec une paire de Vs'",

            " 'RCa RCo 6Tr 3Pi 5Tr', 'VTr DTr 6Tr 4Tr 2Tr','Main 2 gagne avec une couleur de TR et la carte la plus haute D'",

            "'10Tr 10Ca 10Co 8Pi 8Ca', '10Tr 10Ca 10Co 8Pi 8Ca', 'Égalité'",

            "'10Tr 10Ca RCo 4Pi 3Ca', '10Pi 10Co DTr ACa 2Tr', 'Main 2 gagne avec la plus forte carte A'",

            "'ATr RTr DTr VTr 10Tr', 'APi RPi DPi VPi 10Pi',  'Égalité'",

            "'10Tr 10Ca 10Co 8Pi 8Ca', 'RCa 2Ca 9Ca 6Ca 3Ca', 'Main 1 gagne avec un full de 10s et 8s'",

            "'RTr RCa 8Co 8Pi 2Co', 'RPi RCo 7Tr 7Ca 3Pi', 'Main 1 gagne avec la plus forte carte 8'",

            "'10Tr 9Tr 8Tr 7Tr 6Tr', 'RCa 2Ca 9Ca 6Ca 3Ca',  'Main 1 gagne avec une quinte flush se terminant par 10'",

            "'5Tr 6Tr 7Tr 8Tr 9Tr', '3Pi 4Pi 5Pi 6Pi 7Pi', 'Main 1 gagne avec une quinte flush se terminant par 9'",

            "'RTr DTr VTr 10Tr 9Tr', 'DCo VCo 10Co 9Co 8Co',  'Main 1 gagne avec une quinte flush se terminant par R'",

            "'10Tr 10Ca 10Co 10Pi 2Co', '9Tr 9Ca 9Co 9Pi 3Ca', 'Main 1 gagne avec un carré de 10s'",

            "'ATr RTr DTr VTr 10Tr', '2Pi 3Pi 4Pi 5Pi 6Pi','Main 1 gagne avec une quinte flush royale'",

            "'10Tr 10Ca 10Co 5Pi 3Co', '9Tr 9Ca 9Co 4Pi 2Co', 'Main 1 gagne avec un brelan de 10s'",

            "'10Tr 10Ca 10Co 5Pi 3Co', '10Tr 10Ca 10Co 5Pi 4Co', 'Main 2 gagne avec la plus forte carte 4'",

            "'5Tr 6Tr 7Tr 8Tr 9Co', '9Tr 9Ca 9Co 4Pi ACa', 'Main 1 gagne avec une suite se terminant par 9'",

            "'5Tr 6Tr 7Tr 8Tr 9Tr', '2Pi 3Pi 4Pi 5Pi 6Pi',  'Main 1 gagne avec une quinte flush se terminant par 9'",

            "'RTr RCa RCo 2Pi 2Ca', 'RCa RPi RTr 8Co 8Ca','Main 2 gagne avec la plus forte carte 8'",

            "'RTr RCa RPi VTr VCa', '10Tr 10Ca 10Co 8Pi 8Ca', 'Main 1 gagne avec un full de Rs et Vs'",

            "'RTr RCa APi VTr VCa', '10Tr 10Ca ACo 8Pi 8Ca', 'Main 1 gagne avec deux paires, la plus haute de R'",
            "'ATr RTr 10Tr VTr DTr','5Tr 6Tr 7Tr 8Tr 9Tr','Main 1 gagne avec une quinte flush royale'"
    })
    void testShowWinner(String input1, String input2, String expectedOutput) {
        System.setIn(new ByteArrayInputStream(input1.getBytes()));
        handScanner.handScan(hand1, 1);

        System.setIn(new ByteArrayInputStream(input2.getBytes()));
        handScanner.handScan(hand2, 2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();

        boolean[] flagArr = {true};

        HandId expectedWinner =gameEngine.whoWon(hand1, hand2, flagArr);
        String result = outputHandler.showWinner(expectedWinner, hand1, hand2, flagArr);

        assertEquals(expectedOutput, result);
    }
}