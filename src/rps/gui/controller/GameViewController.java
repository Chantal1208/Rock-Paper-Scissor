package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {


    public ImageView misterCrabsimg, theRockimg, thisGuyimg, bananaBackground1, bananaBackground2;
    public ImageView playersChoiceImg, aiChoiceImg;
    public Button paperBtn, rockBtn, scissorsBtn;
    public Label playerScoreLabel, aiScoreLabel;
    private Move playerMove;
    private String rock = "/rps/gui/view/images/therock.png";
    private String paper = "/rps/gui/view/images/mmm.png";
    private String scissor = "/rps/gui/view/images/crab1.png";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void gameMove(ActionEvent actionEvent) {
        Button btnPressed = (Button) actionEvent.getSource();
        if (btnPressed == scissorsBtn) {
            playersChoiceImg.setImage(new Image(scissor));
            playerMove= Move.Scissor;
        }if (btnPressed == rockBtn) {
            playersChoiceImg.setImage(new Image(rock));
            playerMove= Move.Rock;
        }if (btnPressed == paperBtn) {
            playersChoiceImg.setImage(new Image(paper));
            playerMove= Move.Paper;
        }
        startGame();
    }

    private void startGame() {
        IPlayer human = new Player("Player", PlayerType.Human);
        IPlayer bot = new Player(getRandomBotName(), PlayerType.AI);
        GameManager ge = new GameManager(human, bot);
        ge.playRound(playerMove);
        Result result = null;
        int aiWins = 0;
        int humanWins = 0;
        for (Result result1 : ge.getGameState().getHistoricResults()) {
            if (result1.getWinnerPlayer().getPlayerType() == PlayerType.Human && result1.getType() != ResultType.Tie)
                humanWins++;
            playerScoreLabel.setText(""+humanWins+"");

            if (result1.getWinnerPlayer().getPlayerType() == PlayerType.AI && result1.getType() != ResultType.Tie)
                aiWins++;
            aiScoreLabel.setText(""+aiWins+"");

            result = result1;
        }
    }

    private String getRandomBotName() {
        String[] botNames = new String[] {
                "R2D2",
                "Mr. Data",
                "3PO",
                "Bender",
                "Marvin the Paranoid Android",
                "Bishop",
                "Robot B-9",
                "HAL"
        };
        int randomNumber = new Random().nextInt(botNames.length - 1);
        return botNames[randomNumber];
    }

    private void getAIMove(){

    }

}
