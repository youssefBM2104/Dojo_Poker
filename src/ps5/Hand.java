package ps5;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardList = new ArrayList<>();

    public Hand(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
