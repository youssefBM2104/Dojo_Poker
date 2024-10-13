package ps5;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card>cardList;

    public Hand() {
        this.cardList = new ArrayList<>();
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
}
