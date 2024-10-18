package ps5.player;

import ps5.player.enums.CardValue;
import ps5.player.enums.HandPriority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {
    private List<Card>cardList;
    private HandPriority handPriority;
    private CardValue highestCard;

    public Hand() {
        this.cardList = new ArrayList<>();
        handPriority =HandPriority.MAX_CARD_IN_HAND;
        highestCard = CardValue.TWO;

    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void addCardToHand(Card card){
        this.cardList.add(card);
    }

    public boolean isBrelan(Hand hand){
        Map<CardValue,Integer> hashmap = new HashMap<>();

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
                hand.handPriority = HandPriority.BRELAN;
                // get highest card in the brelan
                return true;
            }
        }
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
        hand.handPriority = HandPriority.COULEUR;
        return true;
    }
}
