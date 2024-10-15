package ps5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private HandScanner handScanner;
    private Hand hand;
    private GameEngine gameEngine;

    @BeforeEach
    void setup() {
        handScanner = new HandScanner();
        hand = new Hand();
        gameEngine = new GameEngine();
    }

    @Test
    void testIsFullColor() {
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Co"));
        hand.addCardToHand(handScanner.getCardFromString("RPi"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertFalse(gameEngine.isFullColor(hand));


        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));

        assertFalse(gameEngine.isFullColor(hand));


        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Tr"));

        assertTrue(gameEngine.isFullColor(hand));


        hand = new Hand();
        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Ca"));
        hand.addCardToHand(handScanner.getCardFromString("RCa"));
        hand.addCardToHand(handScanner.getCardFromString("2Ca"));

        assertFalse(gameEngine.isFullColor(hand));
    }


    @Test
    void testIsBrelan(){
        String input = "10Tr 10Ca 7Co 10Pi 2Co";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertTrue(gameEngine.isBrelan(hand));


        hand = new Hand();

        input = "10Tr 8Ca 7Co 10Pi 2Co";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(gameEngine.isBrelan(hand));


        hand = new Hand();

        input = "10Tr 10Ca";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        handScanner.handScan(hand, 1);

        assertFalse(gameEngine.isBrelan(hand));

    }

}