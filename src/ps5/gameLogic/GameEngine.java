package ps5.gameLogic;


import ps5.player.Hand;
import ps5.player.enums.HandId;
import ps5.player.enums.HandPriority;


/**
 * Determines the winner between 2 hands
 */
public class GameEngine {


    /**
     * Checks both hands given into arguments and gives who won
     * @param hand1 first hand
     * @param hand2 second hand
     * @param flag boolean flag for the output
     * @return HandId of the winning hand (DEFAULT if it's equality)
     */
    public HandId whoWon(Hand hand1, Hand hand2, boolean[] flag) {


        // HANDS ARENT EQUAL
        if (hand1.getHandPriority().ordinal() > hand2.getHandPriority().ordinal()) {
            return HandId.HAND_1;
        } else if (hand1.getHandPriority().ordinal() < hand2.getHandPriority().ordinal()) {
            return HandId.HAND_2;
        }
        if (hand1.getHighestCard().ordinal() > hand2.getHighestCard().ordinal()) {
            return HandId.HAND_1;
        } else if (hand1.getHighestCard().ordinal() < hand2.getHighestCard().ordinal()) {
            return HandId.HAND_2;
        }

        // HANDS ARE EQUAL
        if (hand1.getHandPriority() == HandPriority.ROYALE_FLUSH) {
            return HandId.DEFAULT;
        }

        if (hand1.getHandPriority() == HandPriority.FULL_HOUSE && hand1.getCardList().size() == 2) {
            return HandId.DEFAULT;
        }
        if (hand1.getCardList().size() == 1) {
            return HandId.DEFAULT;
        }

        //removing the highest card from the hand to check the next highest one depending on the hand type
        hand1.nextHighestCard();
        hand2.nextHighestCard();

        flag[0] = false;
        return whoWon(hand1, hand2, flag);
    }

}
