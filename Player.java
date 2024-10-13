import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    //player hand
    public Player(String name, ArrayList<Card> hand) {
        this.name = name;
        this.hand = hand;
    }
    //draw a card
    public Card drawCard() {
        if (hand.size() > 0) {
            return hand.remove(0);
        }
        return null;
    }

    public int handSize() {
        return hand.size();
    }

    public void addCards(ArrayList<Card> wonCards) {
        hand.addAll(wonCards);
    }

    public String getName() {
        return name;
    }
}