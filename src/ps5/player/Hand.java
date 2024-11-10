package ps5.player;

import ps5.player.enums.CardValue;
import ps5.player.enums.HandPriority;

import java.util.*;

public class Hand   {
    private List<Card> cardList;
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


    public CardValue maxCardValueFromList(){

        Card maxCard = cardList.getFirst();
        for(Card card : cardList) {
            if(card.supTo(maxCard)){
                maxCard=card;
            }
        }
        return maxCard.getCardValue();
    }

    public boolean isPaire(){
        return checkForNTuple(2,HandPriority.PAIRE);

    }

    public boolean isCarre() {
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNTuple(4,HandPriority.CARRE);
    }

    public boolean isBrelan(){
        return checkForNTuple(3,HandPriority.BRELAN);

    }

    public boolean isFullColor(){
        Card firstCard = cardList.getFirst();
        for(Card card : cardList){
            if(!(firstCard.isSameColor(card))){
                return false;
            }
        }
        handPriority = HandPriority.COULEUR;
        highestCard = maxCardValueFromList();
        return true;
    }

    public boolean isFullHouse(){
        if (hashMapFromHand.size()!=2){
            return false;
        }
        return checkForNTuple(3,HandPriority.FULL_HOUSE);

    }

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

        handPriority = HandPriority.ROYALE_FLUSH;
        highestCard = CardValue.A;

        return true;
    }

    //Detecter 2 doubles, dans highest card mettre le double le plus haut
    //
    public boolean isDoublePaire(){

        if(hashMapFromHand.size() != 3){
            return false;
        }
        int counter=0;
        CardValue temp=CardValue.TWO;

        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {

            if (entry.getValue() == 2 ){
                counter++;
                if(entry.getKey().ordinal()>temp.ordinal()) {
                    temp = entry.getKey();
                }
            }
        }

        if (counter==2){ // j'ai trouvé 2 paires
            handPriority = HandPriority.DOUBLE_PAIRE;
            highestCard = temp;
            return true;
        }
        return false;
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
                this.handPriority = handPriority;
                highestCard = entry.getKey();
                return true;
            }
        }
        return false;
    }

    public void runAllPossibleHands(){
        if(isRoyalFlush()) return;
        if(isQuinteFlush()) return;
        if(isCarre()) return;
        if(isFullHouse()) return;
        if(isFullColor()) return;
        if(isSuite()) return;
        if(isBrelan()) return;
        if(isDoublePaire()) return;
        if(isPaire()) return;
        // else none of the above
        highestCard = maxCardValueFromList();
    }



    public void nextHighestCard(){
        removeCardFromList(highestCard);
        switch (handPriority){
            case FULL_HOUSE:
                setHighestCardFromNextPair();
                break;
            case DOUBLE_PAIRE:
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

            handPriority = HandPriority.SUITE;
            highestCard = cardList.getLast().getCardValue();

        return true;
    }

    public boolean isQuinteFlush(){
        if(isSuite() && isFullColor()){
            handPriority = HandPriority.FLUSH;
            highestCard = cardList.getLast().getCardValue();
            return true;
        }
        return false;
    }
    public CardValue getTripleValue() {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 3) {
                return entry.getKey();
            }
        }
        return null;
    }

    public CardValue getPairValue() {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 2) {
                return entry.getKey();
            }
        }
        return null;
    }
}


