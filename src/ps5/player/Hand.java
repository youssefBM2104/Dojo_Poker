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
//        if (cardList.size()==5){
//            runAllPossibleHands();
//        }

    }

    public CardValue getHighestCard() {
        return highestCard;
    }

    public HandPriority getHandPriority() {
        return handPriority;
    }


    public CardValue maxCardValueFromList(){

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
        return checkForNTuple(2,HandPriority.PAIRE);

    }

    @Override
    public boolean isCarre() {
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNTuple(4,HandPriority.CARRE);
    }

    @Override
    public boolean isBrelan(){
        return checkForNTuple(3,HandPriority.BRELAN);

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
            highestCard = maxCardValueFromList();
        }
        return true;
    }

    @Override
    public boolean isFullHouse(){
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNTuple(3,HandPriority.FULL_HOUSE);

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

    //Detecter 2 doubles, dans highest card mettre le double le plus haut
    //
    @Override
    public boolean isDoublePaire(){

        if(hashMapFromHand.size() != 3){
            return false;
        }
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {

            if (entry.getValue() == 2 && entry.getKey().ordinal()>this.highestCard.ordinal()) {
                    highestCard = entry.getKey();

            }
        }
        handPriority = HandPriority.DEUX_PAIRES;
        return true;
    }


    private void updateHashMap(Card card){
        CardValue cardValue = card.getCardValue();
        hashMapFromHand.put(cardValue, hashMapFromHand.getOrDefault(cardValue, 0) + 1);
    }

    private boolean checkForNTuple(int n, HandPriority handPriority){

        if (!hashMapFromHand.containsValue(n)){
            return false;
        }

        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == n) {
                if (handPriority.ordinal()>this.handPriority.ordinal()){
                    this.handPriority = handPriority;
                    highestCard = entry.getKey();
                }
                return true;
            }
        }
        return false;
    }

    //TODO find a better alternative to stop executing the moment we find the highest priority
    public void runAllPossibleHands(){
        for (Method method : HandInterface.class.getMethods()){
            try {
                 method.invoke(this);
            }catch (Exception e){
                throw new RuntimeException("this should never happen",e);
            }
        }
        if (handPriority==HandPriority.MAX_CARD_IN_HAND){
            highestCard = maxCardValueFromList();
        }
    }



    public void nextHighestCard(){
        removeCardFromList(highestCard);
        switch (handPriority){
            case FULL_HOUSE:
                setHighestCardFromNextPair();
                break;
            case DEUX_PAIRES:
                removePairFromHashMap(highestCard);
                if (hashMapFromHand.size()==2){
                    setHighestCardFromNextPair();
                }
                else {
                    highestCard = maxCardValueFromList();
                }
                break;
            default:
                highestCard = maxCardValueFromList();
                break;
        }
    }

    public void removeCardFromList(CardValue cardValue){
        cardList.removeIf(card -> card.getCardValue().equals(cardValue));
    }

    public void removePairFromHashMap(CardValue cardValue){
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue()==2 && entry.getKey()==cardValue) {
                hashMapFromHand.remove(cardValue);
                break;
            }
        }
    }

    public void setHighestCardFromNextPair(){
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 2) {
                highestCard = entry.getKey();
            }
        }
    }
    private void triCardListe() {
        int lengthL = cardList.size();
        for (int i = 1; i < lengthL; i++) {
            Card cardToInsert = cardList.get(i);
            int j = i - 1;


            while (j >= 0 && cardList.get(j).supTo(cardToInsert)) {
                cardList.set(j + 1, cardList.get(j));
                j--;
            }

            cardList.set(j + 1, cardToInsert);
        }
    }





    @Override
    public boolean isSuite() {


        this.triCardListe();
        if(this.hashMapFromHand.size() != 5) {
            return false;
        }

        for (int i=0;i<cardList.size()-1;i++) {
            if (cardList.get(i+1).getCardValue().ordinal()!=cardList.get(i).getCardValue().ordinal()+1){
                return false;
            }
        }

        if (handPriority.ordinal()<HandPriority.SUITE.ordinal()){
            handPriority = HandPriority.SUITE;
            highestCard = cardList.getLast().getCardValue();
        }

        return true;
    }

    @Override
    public boolean isQuinteFlush(){
        if(isSuite() && isFullColor()){
            if (handPriority.ordinal()<HandPriority.FLUSH.ordinal()){
                handPriority = HandPriority.FLUSH;
                highestCard = cardList.getLast().getCardValue();
            }
            return true;
        }
        return false;
    }

}


