package ps5.gameRunner;

import ps5.gameLogic.GameEngine;
import ps5.io.HandScanner;
import ps5.io.OutputHandler;
import ps5.player.Hand;
import ps5.player.enums.HandId;

public class Game {
    public static void main(String[] args) {
        HandScanner handScanner = new HandScanner();
        GameEngine gameEngine = new GameEngine();
        OutputHandler outputHandler = new OutputHandler();
        boolean ok1;
        Hand hand1;
        do {
            hand1 = new Hand();
            ok1 = handScanner.handScan(hand1, 1);
        } while (!ok1);

        boolean ok2;
        Hand hand2;
        do {
            hand2 = new Hand();
            ok2 = handScanner.handScan(hand2, 2);
        } while (!ok2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();


        boolean[] handPriorityFlag = {true};

        HandId winner = gameEngine.whoWon(hand1, hand2, handPriorityFlag);
        System.out.println(outputHandler.showWinner(winner, hand1, hand2, handPriorityFlag));
    }


}
