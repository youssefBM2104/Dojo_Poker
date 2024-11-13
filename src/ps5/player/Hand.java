package ps5.player;

import ps5.player.enums.CardValue;
import ps5.player.enums.HandPriority;

import java.util.*;

public class Hand {
    // List of cards in the hand
    private List<Card> cardList;
    // Priority of the hand
    private HandPriority handPriority;
    // Highest card in the hand
    private CardValue highestCard;
    // Mapping of card values to their frequency in the hand
    private Map<CardValue, Integer> hashMapFromHand;

    /**
     * Constructor to initialize an empty hand with default values
     */
    public Hand() {
        this.cardList = new ArrayList<>();
        handPriority = HandPriority.MAX_CARD_IN_HAND;
        highestCard = CardValue.TWO;
        hashMapFromHand = new HashMap<>();


    }

    /**
     * @return the list of cards in the hand
     */
    public List<Card> getCardList() {
        return cardList;
    }

    /**
     * @return the map of card values and their frequencies in the hand
     */
    public Map<CardValue, Integer> getHashMapFromHand() {
        return hashMapFromHand;
    }

    /**
     * Adds a card to the hand and updates the frequency map of card values
     * @param card
     */
    public void addCardToHand(Card card) {
        this.cardList.add(card);
        updateHashMap(card);


    }

    /**
     * Returns the highest card in the hand
     * @return CardValue
     */
    public CardValue getHighestCard() {
        return highestCard;
    }

    /**
     * Returns the current hand priority
     * @return HandPriority
     */
    public HandPriority getHandPriority() {
        return handPriority;
    }


    /**
     * Finds and returns the maximum card value in the hand
     * @return CardValue
     */
    public CardValue maxCardValueFromList() {

        Card maxCard = cardList.getFirst();
        for (Card card : cardList) {
            if (card.supTo(maxCard)) {
                maxCard = card;
            }
        }
        return maxCard.getCardValue();
    }

    /**
     * Checks if the hand contains a pair
     * @return a boolean (true if pair or false if not)
     */
    public boolean isPaire() {
        return checkForNTuple(2, HandPriority.PAIRE);

    }

    /**
     * Checks if the hand contains four of a kind
     * @return a boolean (true if carre or false if not)
     */
    public boolean isCarre() {
        if (hashMapFromHand.size() != 2) {
            return false;
        }
        return checkForNTuple(4, HandPriority.CARRE);
    }

    /**
     * Checks if the hand contains three of a kind
     * @return a boolean (true if brelan or false if not)
     */
    public boolean isBrelan() {
        return checkForNTuple(3, HandPriority.BRELAN);

    }

    /**
     * Checks if all cards in the hand are of the same suit
     * @return a boolean (true if fullcolor or false if not)
     */
    public boolean isFullColor() {
        Card firstCard = cardList.getFirst();
        for (Card card : cardList) {
            if (!(firstCard.isSameColor(card))) {
                return false;
            }
        }
        handPriority = HandPriority.COULEUR;
        highestCard = maxCardValueFromList();
        return true;
    }

    /**
     * Checks if the hand contains a full house (three of a kind and a pair)
     * @return a boolean (true if full house or false if not)
     */
    public boolean isFullHouse() {
        if (hashMapFromHand.size() != 2) {
            return false;
        }
        return checkForNTuple(3, HandPriority.FULL_HOUSE);

    }

    /**
     * Checks if the hand contains a royal flush (same suit from 10 to Ace)
     * @return a boolean (true if royal flush or false if not)
     */
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


    /**
     * Checks if the hand contains two different pairs
     * @return a boolean (true if doublepaire or false if not)
     */
    public boolean isDoublePaire() {

        if (hashMapFromHand.size() != 3) {
            return false;
        }
        int counter = 0;
        CardValue temp = CardValue.TWO;

        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {

            if (entry.getValue() == 2) {
                counter++;
                if (entry.getKey().ordinal() > temp.ordinal()) {
                    temp = entry.getKey();
                }
            }
        }

        if (counter == 2) { // j'ai trouvé 2 paires
            handPriority = HandPriority.DOUBLE_PAIRE;
            highestCard = temp;
            return true;
        }
        return false;
    }


    /**
     * Updates the map of card values with the frequency of each card in the hand
     * @param card The card to be added to the hashmap
     */
    private void updateHashMap(Card card) {
        CardValue cardValue = card.getCardValue();
        hashMapFromHand.put(cardValue, hashMapFromHand.getOrDefault(cardValue, 0) + 1);
    }

    /**
     * Checks if the hand contains a specified number of identical cards
     * @param n The number of occurrences
     * @param handPriority The handPriority to be applied to the hand
     * @return a boolean (true if in map or false if not)
     */
    private boolean checkForNTuple(int n, HandPriority handPriority) {

        if (!hashMapFromHand.containsValue(n)) {
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

    /**
     * Runs through all possible hands to determine the best hand ranking
     */
    public void runAllPossibleHands() {
        if (isRoyalFlush()) return;
        if (isQuinteFlush()) return;
        if (isCarre()) return;
        if (isFullHouse()) return;
        if (isFullColor()) return;
        if (isSuite()) return;
        if (isBrelan()) return;
        if (isDoublePaire()) return;
        if (isPaire()) return;
        // else none of the above
        highestCard = maxCardValueFromList();
    }


    /**
     * Advances to the next highest card based on the current hand priority
     */
    public void nextHighestCard() {
        removeCardFromList(highestCard);
        switch (handPriority) {
            case FULL_HOUSE:
                setHighestCardFromNextPair();
                break;
            case DOUBLE_PAIRE:
                removePairFromHashMap(highestCard);
                if (hashMapFromHand.size() == 2) {
                    setHighestCardFromNextPair();
                } else {
                    highestCard = maxCardValueFromList();
                }
                break;
            default:
                highestCard = maxCardValueFromList();
                break;
        }
    }

    /**
     * Removes a card from the list by its value
     * @param cardValue the cardValue of the card to be removed from the list
     */
    public void removeCardFromList(CardValue cardValue) {
        cardList.removeIf(card -> card.getCardValue().equals(cardValue));
    }

    /**
     * Removes a specified pair from the map of card values
     * @param cardValue  the cardValue of the pair to be removed from the hashmap
     */
    public void removePairFromHashMap(CardValue cardValue) {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 2 && entry.getKey() == cardValue) {
                hashMapFromHand.remove(cardValue);
                break;
            }
        }
    }

    /**
     * Sets the highest card for the next found pair
     */
    public void setHighestCardFromNextPair() {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 2) {
                highestCard = entry.getKey();
            }
        }
    }

    /**
     * Sorts the list of cards by their value
     */
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


    /**
     * Checks if the hand contains a straight (five consecutive cards)
     * @return a boolean (true if suite or false if not)
     */
    public boolean isSuite() {


        if (this.hashMapFromHand.size() != 5) {
            return false;
        }

        this.triCardListe();
        for (int i = 0; i < cardList.size() - 1; i++) {
            if (cardList.get(i + 1).getCardValue().ordinal() != cardList.get(i).getCardValue().ordinal() + 1) {
                return false;
            }
        }

        handPriority = HandPriority.SUITE;
        highestCard = cardList.getLast().getCardValue();

        return true;
    }

    /**
     * Checks if the hand contains a straight flush (five consecutive cards of the same suit)
     * @return a boolean (true if quinte or false if not)
     */
    public boolean isQuinteFlush() {
        if (isSuite() && isFullColor()) {
            handPriority = HandPriority.FLUSH;
            highestCard = cardList.getLast().getCardValue();
            return true;
        }
        return false;
    }

    /**
     * @return the value of a three-of-a-kind if present
     */
    public CardValue getTripleValue() {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 3) {
                return entry.getKey();
            }
        }
        return null;
    }


    /**
     * @return the value of a pair if present
     */
    public CardValue getPairValue() {
        for (Map.Entry<CardValue, Integer> entry : hashMapFromHand.entrySet()) {
            if (entry.getValue() == 2) {
                return entry.getKey();
            }
        }
        return null;
    }
}


