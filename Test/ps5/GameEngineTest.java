package ps5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GameEngineTest {

    private HandScanner handScanner;
    private Hand hand;
    private GameEngine gameEngine;

    @BeforeEach
    void setup(){
        hand = new Hand();
        handScanner = new HandScanner();
        gameEngine = new GameEngine();
    }


    @Test
    void isBrelan() {

    }
}