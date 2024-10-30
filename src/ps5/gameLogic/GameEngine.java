package ps5.gameLogic;


import ps5.player.Hand;
import ps5.player.enums.HandId;


public class GameEngine {


    public HandId whoWon(Hand hand1, Hand hand2){
        if (hand1.getHandPriority().ordinal() > hand2.getHandPriority().ordinal()){
            return HandId.HAND_1;
        }
        else if(hand1.getHandPriority().ordinal() < hand2.getHandPriority().ordinal()){
            return HandId.HAND_2;
        }
        else{
            if(hand1.getHighestCard().ordinal() > hand2.getHighestCard().ordinal()){
                return HandId.HAND_1;
            }
            else if(hand1.getHighestCard().ordinal() < hand2.getHighestCard().ordinal()){
                return HandId.HAND_2;
            }
            else{
                return HandId.DEFAULT;// TODO: KEEP ITTERATING ON THE REST OF HAND UNTIL YOU FIND A BIGGER CARD (POSSIBLE RECURSION)
            }
        }
    }
}
