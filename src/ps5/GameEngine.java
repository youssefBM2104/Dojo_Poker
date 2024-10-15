package ps5;

import java.util.List;

public class GameEngine {
    public boolean isBrelan(Hand hand){
        return false;
    }

    public boolean isFullColor(Hand hand){
        List<Card> cardList = hand.getCardList();
        Card firstCard = cardList.getFirst();
        for(Card card : cardList){
            if(!(firstCard.isSameColor(card))){
                return false;
            }
        }
        return true;
    }
}
