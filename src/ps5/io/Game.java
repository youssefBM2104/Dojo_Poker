package ps5.io;

import ps5.gameLogic.GameEngine;
import ps5.player.Hand;
import ps5.player.enums.HandId;

public class Game {
    private static void showWinner(HandId winner,Hand hand1,Hand hand2){
        String message="";
        switch (winner){
            case HAND_1:
                message = "La main 1 " + "gagne avec "+ hand1.getHandPriority().name();
                break;
            case HAND_2:
                message = "La main 2 " + "gagne avec "+ hand2.getHandPriority().name();
                break;
            case DEFAULT:
                message = "Egalite";
                break;

        }
        System.out.println(message);
    }

    public static void main(String[] args) {
        HandScanner handScanner = new HandScanner();
        GameEngine gameEngine = new GameEngine();
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


        //TODO: add how the hand won on the output
        HandId winner = gameEngine.whoWon(hand1,hand2);
        Game.showWinner(winner,hand1,hand2);
    }


}
