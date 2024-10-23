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

    public CardValue getHighestCard() {
        return highestCard;
    }

    public HandPriority getHandPriority() {
        return handPriority;
    }


    public CardValue maxCardValue(){

        Card maxCard = cardList.getFirst();
        for(Card card : cardList) {
            if(card.supTo(maxCard)){
                maxCard=card;
            }
        }
        return maxCard.getCardValue();
    }

    public boolean isPaire(){
        Map<CardValue,Integer> hashmap =getHashMapFromHand();

        for (Map.Entry<CardValue, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue() >= 2) {
                handPriority = HandPriority.PAIRE;
                highestCard = entry.getKey();
                return true;
            }
        }
        return false;

    }

    public boolean isBrelan(){
        Map<CardValue,Integer> hashmap =getHashMapFromHand();
        for (Map.Entry<CardValue, Integer> entry : hashmap.entrySet()) {
            if (entry.getValue() >= 3) {
                handPriority = HandPriority.BRELAN;
                highestCard = entry.getKey();
                return true;
            }
        }
        return false;

    }

    public boolean isFullColor(){
        Card firstCard = cardList.getFirst();
        for(Card card : cardList){
            if(!(firstCard.isSameColor(card))){
                return false;
            }
        }
        handPriority = HandPriority.COULEUR;
        highestCard = maxCardValue();
        return true;
    }

    private Map<CardValue,Integer> getHashMapFromHand(){
        Map<CardValue,Integer> hashMap = new HashMap<>();

        for (Card card : cardList) {
            CardValue cardValue = card.getCardValue();
            hashMap.put(cardValue, hashMap.getOrDefault(cardValue, 0) + 1);
        }

        return hashMap;
    }

}
