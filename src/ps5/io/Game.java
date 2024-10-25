package ps5.io;

import ps5.gameLogic.GameEngine;
import ps5.player.Hand;
import ps5.player.enums.HandId;

public class Game {

    public static void main(String[] args) {
        HandScanner handScanner = new HandScanner();

        boolean ok1;
        Hand hand1;
        do {
            hand1 = new Hand();
            ok1 = handScanner.handScan(hand1, 1);
        } while (!ok1);

        boolean ok2;
        handScanner = new HandScanner();
        Hand hand2;
        do {
            hand2 = new Hand();
            ok2 = handScanner.handScan(hand2, 2);
        } while (!ok2);

        hand1.runAllPossibleHands();
        hand2.runAllPossibleHands();

        GameEngine gameEngine = new GameEngine();
        HandId winner = gameEngine.whoWon(hand1,hand2);
        switch (winner){
            case HAND_1:
                System.out.println("HAND 1 WON");
                break;
            case HAND_2:
                System.out.println("HAND 2 WON");
                break;
            case DEFAULT :
                System.out.println("EQUALITY");
                break;
        }
    }


}
