package ps5.gameLogic;


import ps5.player.Card;
import ps5.player.Hand;
import ps5.player.HandInterface;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

public class GameEngine {

    public void runAllPossibleHands(Hand hand){
        boolean result = false;
        for (Method method : HandInterface.class.getMethods()){
            try {
                result = (Boolean) method.invoke(hand);
            }catch (Exception e){
                System.out.println("This should never happen");
                return;
            }
            if (result){
                break;
            }
        }

    }
}
