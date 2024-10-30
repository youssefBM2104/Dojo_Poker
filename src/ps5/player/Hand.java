package ps5.player;

import ps5.player.enums.CardValue;
import ps5.player.enums.HandPriority;

import java.lang.reflect.Method;
import java.util.*;

public class Hand implements HandInterface {
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
        if (cardList.size()==5){
            runAllPossibleHands();
        }

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

    @Override
    public boolean isPaire(){
        return checkForNtuple(2,HandPriority.PAIRE);

    }

    @Override
    public boolean isCarre() {
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNtuple(4,HandPriority.CARRE);
    }

    @Override
    public boolean isBrelan(){
        return checkForNtuple(3,HandPriority.BRELAN);

    }

    @Override
    public boolean isFullColor(){
        Card firstCard = cardList.getFirst();
        for(Card card : cardList){
            if(!(firstCard.isSameColor(card))){
                return false;
            }
        }
        if (handPriority.ordinal()<HandPriority.COULEUR.ordinal()){
            handPriority = HandPriority.COULEUR;
            highestCard = maxCardValue();
        }
        return true;
    }

    @Override
    public boolean isFullHouse(){
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNtuple(3,HandPriority.FULL_HOUSE);

    }

    @Override
    public boolean isRoyalFlush() {
        if (!isFullColor()) {
            return false;
        }

        List<CardValue> royalFlushValues = List.of(CardValue.TEN, CardValue.V, CardValue.D, CardValue.R, CardValue.A);


        for (CardValue value : royalFlushValues) {
            if (!hashMapFromHand.containsKey(value)) {
                return false;
            }
        }

        if (handPriority.ordinal()<HandPriority.ROYALE_FLUSH.ordinal()){
            handPriority = HandPriority.ROYALE_FLUSH;
            highestCard = CardValue.A;
        }

        return true;
    }


    private void updateHashMap(Card card){
        CardValue cardValue = card.getCardValue();
        hashMapFromHand.put(cardValue, hashMapFromHand.getOrDefault(cardValue, 0) + 1);
    }

    private boolean checkForNtuple(int n,HandPriority handPriority){
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == n) {
                if (handPriority.ordinal()>this.handPriority.ordinal()){
                    this.handPriority = handPriority;
                }
                highestCard = maxCardValue();
                return true;
            }
        }
        return false;
    }

    public void runAllPossibleHands(){
        for (Method method : HandInterface.class.getMethods()){
            try {
                 method.invoke(this);
            }catch (Exception e){
                throw new RuntimeException("this should never happen",e);
            }
        }
        if (handPriority==HandPriority.MAX_CARD_IN_HAND){
            highestCard = maxCardValue();
        }
    }

}
