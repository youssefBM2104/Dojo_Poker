package ps5.io;

import ps5.player.Card;
import ps5.player.Hand;
import ps5.player.enums.CardColor;
import ps5.player.enums.CardValue;

import java.util.Scanner;

/**
 * Responsible for all the inputs
 */
public class HandScanner {

    /**
     * Generates a Hand from a given input
     * @param hand the hand to be filled
     * @param handNumber the number associated to the hand
     * @return true if hand was successfully generated false otherwise
     */
    public boolean handScan(Hand hand, int handNumber) {
        // Create a scanner to read from standard input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a line of text
        System.out.print("Main " + handNumber + ": ");
        String line = scanner.nextLine();

        if (line.isEmpty()) {
            System.out.println("No cards entered.");
            return false;
        }
        // Split the line into words
        String[] cardsString = line.split("\\s+");  // Uses "\\s+" to split by one or more spaces

        if (cardsString.length != 5) {
            System.out.println("Please provide exactly 5 cards!");
            return false;
        }
        for (String cardString : cardsString) {
            try {
                Card card = getCardFromString(cardString);
                if (hand.getCardList().contains(card)) {
                    System.out.println("Please provide exactly 5 distinct cards!");
                    return false;
                }
                hand.addCardToHand(card);
            } catch (IllegalArgumentException e) {
                System.out.println("Please provide exactly 5 valid cards!");
                return false;
            }
        }

        return true;
    }

    /**
     * Converts a String into a Card
     * @param cardString card in String format
     * @return the card converted from a String
     * @throws IllegalArgumentException throws an exception if the String isn't a valid card
     */
    public Card getCardFromString(String cardString) throws IllegalArgumentException {
        CardValue cardValue;
        CardColor cardColor;

        if (cardString.length() == 4 && cardString.contains("10")) {
            cardValue = CardValue.TEN; // Always TEN when length is 4
            cardColor = switch (cardString.substring(2)) {
                case "Pi" -> CardColor.PI;
                case "Tr" -> CardColor.TR;
                case "Co" -> CardColor.CO;
                case "Ca" -> CardColor.CA;
                default -> throw new IllegalArgumentException();
            };
        } else {
            cardValue = switch (cardString.charAt(0)) {
                case '2' -> CardValue.TWO;
                case '3' -> CardValue.THREE;
                case '4' -> CardValue.FOUR;
                case '5' -> CardValue.FIVE;
                case '6' -> CardValue.SIX;
                case '7' -> CardValue.SEVEN;
                case '8' -> CardValue.EIGHT;
                case '9' -> CardValue.NINE;
                case 'V' -> CardValue.V;
                case 'D' -> CardValue.D;
                case 'R' -> CardValue.R;
                case 'A' -> CardValue.A;
                default -> throw new IllegalArgumentException();
            };

            cardColor = switch (cardString.substring(1)) {
                case "Pi" -> CardColor.PI;
                case "Tr" -> CardColor.TR;
                case "Co" -> CardColor.CO;
                case "Ca" -> CardColor.CA;
                default -> throw new IllegalArgumentException();
            };
        }

        return new Card(cardColor, cardValue);
    }
}


