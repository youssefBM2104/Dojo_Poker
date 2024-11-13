package ps5.io;

import ps5.player.Hand;
import ps5.player.enums.HandId;

/**
 * Responsible for all the output messages
 */
public class OutputHandler {
    /**
     * Builds a custom winning message depending on who won and how they won
     * @param winner HandId of the corresponding winner
     * @param hand1 The first hand
     * @param hand2 The second hand
     * @param flag Indicates if the winning hand won because of it's handPriority or because it had the next highest card
     * @return The String of the output message
     */
    public String showWinner(HandId winner, Hand hand1, Hand hand2, boolean[] flag) {
        String message;
        if (flag[0]) {
            message = switch (winner) {
                case HAND_1 -> buildWinningMessage("Main 1", hand1);
                case HAND_2 -> buildWinningMessage("Main 2", hand2);
                case DEFAULT -> "Égalité";
            };
        } else {
            message = switch (winner) {
                case HAND_1 -> "Main 1 gagne avec la plus forte carte " + hand1.getHighestCard();
                case HAND_2 -> "Main 2 gagne avec la plus forte carte " + hand2.getHighestCard();
                case DEFAULT -> "Égalité";
            };
        }
        return message;
    }

    /**
     * Builds a custom winning message
     * @param handLabel The hand label of the winner
     * @param hand The winning Hand
     * @return The String of the winning message
     */
    public String buildWinningMessage(String handLabel, Hand hand) {
        String highestCard = hand.getHighestCard().toString();
        String message;
        switch (hand.getHandPriority()) {
            case PAIRE:
                message = handLabel + " gagne avec une paire de " + highestCard + "s";
                break;
            case DOUBLE_PAIRE:
                message = handLabel + " gagne avec deux paires, la plus haute de " + highestCard;
                break;
            case BRELAN:
                message = handLabel + " gagne avec un brelan de " + highestCard + "s";
                break;
            case SUITE:
                message = handLabel + " gagne avec une suite se terminant par " + highestCard;
                break;
            case COULEUR:
                message = handLabel + " gagne avec une couleur de " + hand.getCardList().getFirst().getCardColor() + " et la carte la plus haute " + highestCard;
                break;
            case FULL_HOUSE:
                String triple = hand.getTripleValue().toString();
                String pair = hand.getPairValue().toString();
                message = handLabel + " gagne avec un full de " + triple + "s et " + pair + "s";
                break;
            case CARRE:
                message = handLabel + " gagne avec un carré de " + highestCard + "s";
                break;
            case FLUSH:
                message = handLabel + " gagne avec une quinte flush se terminant par " + highestCard;
                break;
            case ROYALE_FLUSH:
                message = handLabel + " gagne avec une quinte flush royale";
                break;
            default:
                message = handLabel + " gagne avec la plus haute carte " + highestCard;
                break;
        }
        return message;
    }
}