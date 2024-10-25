package ps5.gameLogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ps5.io.HandScanner;
import ps5.player.Hand;
import ps5.player.enums.HandId;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
    void whoWon1() {
        //Tester qui gagne entre un brelan et une paire

        String brelan = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(brelan.getBytes());
        System.setIn(in);

        handScanner.handScan(hand1,1);

        String paire = "3Tr 10Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(paire.getBytes());
        System.setIn(in);
        handScanner.handScan(hand2,2);

        assertEquals(HandId.HAND_1,gameEngine.whoWon(hand1,hand2));
    }

    @Test
    void whoWon2() {
        //Tester qui gagne entre une couleur et une paire
        String color = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(color.getBytes());
        System.setIn(in);

        handScanner.handScan(hand1,1);

        String paire  = "3Tr 10Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(paire.getBytes());
        System.setIn(in);
        handScanner.handScan(hand2,2);
    }
}