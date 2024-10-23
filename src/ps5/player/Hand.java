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
    Map<CardValue,Integer> hashMapFromHand;

    public Hand() {
        this.cardList = new ArrayList<>();
        handPriority =HandPriority.MAX_CARD_IN_HAND;
        highestCard = CardValue.TWO;
        hashMapFromHand =   new HashMap<>();

    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void addCardToHand(Card card){
        this.cardList.add(card);
        updateHashMap(card);
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
        return checkForNtuple(2,HandPriority.PAIRE);

    }

    public boolean isBrelan(){
        return checkForNtuple(3,HandPriority.BRELAN);

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

    public boolean isFullHouse(){
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNtuple(3,HandPriority.FULL);

    }

    private void updateHashMap(Card card){
        CardValue cardValue = card.getCardValue();
        hashMapFromHand.put(cardValue, hashMapFromHand.getOrDefault(cardValue, 0) + 1);
    }

    private boolean checkForNtuple(int n,HandPriority handPriority){
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == n) {
                this.handPriority = handPriority;
                highestCard = maxCardValue();
                return true;
            }
        }
        return false;
    }
}
