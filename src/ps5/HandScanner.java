package ps5;

import java.util.Scanner;

public class HandScanner {

    public  void handScan(Hand hand, int handNumber) {
        // Create a scanner to read from standard input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a line of text
        System.out.print("Main "+ handNumber +": ");
        String line = scanner.nextLine();

        // Split the line into words
        String[] cardsString = line.split("\\s+");  // Uses "\\s+" to split by one or more spaces

        for (String cardString : cardsString) {
            hand.addCardToHand(getCardFromString(cardString));
        }

        // Close the scanner
        scanner.close();
    }

    public Card getCardFromString(String cardString){
        String cardValue="";
        String cardColor="";
        if (cardString.length() == 4){
            cardValue = cardString.substring(0,2);
            cardColor = cardString.substring(2);
        }
        else{
            cardValue = cardString.substring(0,1);
            cardColor = cardString.substring(1);
        }

        return new Card(cardColor,cardValue);
    }


}
