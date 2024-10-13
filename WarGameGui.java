import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WarGameGUI extends Application {
    private Player player;
    private Player computer;
    private Label playerCardLabel, computerCardLabel, roundWinnerLabel, playerScoreLabel, computerScoreLabel;
    private Deck deck;
    private Button dealButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //initialize the deck and players
        deck = new Deck();
        ArrayList<ArrayList<Card>> splitDecks = deck.splitDeck();
        player = new Player("Player", splitDecks.get(0));
        computer = new Player("Computer", splitDecks.get(1));

        //set up GUI components
        playerCardLabel = new Label("Player's Card: ");
        computerCardLabel = new Label("Computer's Card: ");
        roundWinnerLabel = new Label("Winner of this round: ");
        playerScoreLabel = new Label("Player's cards remaining: " + player.handSize());
        computerScoreLabel = new Label("Computer's cards remaining: " + computer.handSize());

        dealButton = new Button("Deal Cards");
        dealButton.setOnAction(e -> handleDealButton());

        VBox layout = new VBox(10, playerCardLabel, computerCardLabel, roundWinnerLabel, playerScoreLabel, computerScoreLabel, dealButton);
        Scene scene = new Scene(layout, 300, 250);

        // Set up stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("War Card Game");
        primaryStage.show();
    }

    private void handleDealButton() {
        if (player.handSize() > 0 && computer.handSize() > 0) {
            //draw one card from each player
            Card playerCard = player.drawCard();
            Card computerCard = computer.drawCard();

            //update the GUI to show the drawn cards
            playerCardLabel.setText("Player's Card: " + playerCard);
            computerCardLabel.setText("Computer's Card: " + computerCard);

            //compare the card values to determine the round winner
            if (playerCard.getValue() > computerCard.getValue()) {
                roundWinnerLabel.setText("Winner of this round: Player");
                player.addCards(new ArrayList<>(java.util.Arrays.asList(playerCard, computerCard)));
            } else if (playerCard.getValue() < computerCard.getValue()) {
                roundWinnerLabel.setText("Winner of this round: Computer");
                computer.addCards(new ArrayList<>(java.util.Arrays.asList(playerCard, computerCard)));
            } else {
                //tie if both cards are equal
                roundWinnerLabel.setText("It's a tie! Cards are discarded.");
            }

            //update the score labels
            playerScoreLabel.setText("Player's cards remaining: " + player.handSize());
            computerScoreLabel.setText("Computer's cards remaining: " + computer.handSize());

            //check if the game is over
            if (player.handSize() == 0) {
                roundWinnerLabel.setText("Game Over! Computer Wins!");
                dealButton.setDisable(true);
            } else if (computer.handSize() == 0) {
                roundWinnerLabel.setText("Game Over! Player Wins!");
                dealButton.setDisable(true);
            }
        }
    }
}