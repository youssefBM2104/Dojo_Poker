package ps5.gameLogic;


import ps5.player.Card;
import ps5.player.Hand;
import ps5.player.HandInterface;
import ps5.player.enums.HandId;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

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
                return HandId.DEFAULT;
            }
        }
    }
}
