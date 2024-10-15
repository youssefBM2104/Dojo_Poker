package ps5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private HandScanner handScanner;
    private Hand hand;
    private GameEngine gameEngine;

    @BeforeEach
    void setup() {


    }

    @Test
    void isFullColor0() {
        handScanner = new HandScanner();
        hand = new Hand();

        hand.addCardToHand(handScanner.getCardFromString("9Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("6Co"));
        hand.addCardToHand(handScanner.getCardFromString("RPi"));
        hand.addCardToHand(handScanner.getCardFromString("1Co"));
    }

    @Test
    void isFullColor2() {
        handScanner = new HandScanner();
        hand = new Hand();

        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Co"));
    }

    @Test
    void isFullColor3() {
        handScanner = new HandScanner();
        hand = new Hand();

        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ATr"));
        hand.addCardToHand(handScanner.getCardFromString("7Tr"));
        hand.addCardToHand(handScanner.getCardFromString("RTr"));
        hand.addCardToHand(handScanner.getCardFromString("2Tr"));
    }

    @Test
    void isFullColor4() {
        handScanner = new HandScanner();
        gameEngine = new GameEngine();
    }


    @Test
    void isBrelan() {
        hand = new Hand();

        hand.addCardToHand(handScanner.getCardFromString("10Tr"));
        hand.addCardToHand(handScanner.getCardFromString("ACa"));
        hand.addCardToHand(handScanner.getCardFromString("7Ca"));
        hand.addCardToHand(handScanner.getCardFromString("RCa"));
        hand.addCardToHand(handScanner.getCardFromString("2Ca"));
    }

}