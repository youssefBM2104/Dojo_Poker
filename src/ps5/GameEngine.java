package ps5;


import java.util.HashMap;
import java.util.Map;

public class GameEngine {
    public boolean isBrelan(Hand hand){
        Map<String,Integer> hashmap = new HashMap<>();

        for (Card card : hand.getCardList()){
            if (hashmap.containsKey(card.getCardValue())){
                hashmap.put(card.getCardValue(), hashmap.get(card.getCardValue())+1);
            }
            else{
                hashmap.put(card.getCardValue(),1);
            }
        }

        for (Integer n: hashmap.values()){
            if (n==3){
                return true;
            }
        }
        return false;

    }
    public boolean isFullColor(Hand hand){
        return false;
    }
}
