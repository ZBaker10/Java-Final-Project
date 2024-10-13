import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;
    //create deck
    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] values = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        //nitialize the deck with all 52 cards
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                cards.add(new Card(suit, ranks[i], values[i]));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
    //split the deck
    public ArrayList<ArrayList<Card>> splitDeck() {
        ArrayList<Card> playerDeck = new ArrayList<>(cards.subList(0, cards.size() / 2));
        ArrayList<Card> computerDeck = new ArrayList<>(cards.subList(cards.size() / 2, cards.size()));
        ArrayList<ArrayList<Card>> splitDecks = new ArrayList<>();
        splitDecks.add(playerDeck);
        splitDecks.add(computerDeck);
        return splitDecks;
    }
}