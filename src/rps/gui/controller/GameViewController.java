package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {
    public Label whoWinsLabel;
    private PlayerType type;
    public ImageView misterCrabsimg, theRockimg, thisGuyimg, bananaBackground1, bananaBackground2;
    public ImageView playersChoiceImg, aiChoiceImg;
    public Button paperBtn, rockBtn, scissorsBtn;
    public Label playerScoreLabel, aiScoreLabel;
    private Move playerMove;
    private String rock = "/rps/gui/view/images/therock.png";
    private String paper = "/rps/gui/view/images/mmm.png";
    private String scissor = "/rps/gui/view/images/crab1.png";
    private IPlayer human;
    private IPlayer bot;
    private GameManager ge;


    public GameViewController() {
        human = new Player("Player", PlayerType.Human);
        bot = new Player(getRandomBotName(), PlayerType.AI);
        ge = new GameManager(human, bot);
    }


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
    int globalaiWins = 0;
    int globalhumanWins = 0;
    private void startGame() {
        ge.playRound(playerMove);
        //Result result = null;


        //int aiWins = 0;
        //int humanWins = 0;
        for (Result result1 : ge.getGameState().getHistoricResults()) {
            if(result1.getWinnerPlayer().getPlayerType()==PlayerType.Human) {
                if(result1.getLoserMove()==Move.Rock) {
                    aiChoiceImg.setImage(new Image(rock));
                }
                if(result1.getLoserMove()==Move.Paper) {
                    aiChoiceImg.setImage(new Image(paper));
                }
                if(result1.getLoserMove()==Move.Scissor) {
                    aiChoiceImg.setImage(new Image(scissor));
                }
            }
            if(result1.getWinnerPlayer().getPlayerType()==PlayerType.AI) {
                if(result1.getWinnerMove()==Move.Rock) {
                    aiChoiceImg.setImage(new Image(rock));
                }
                if(result1.getWinnerMove()==Move.Paper) {
                    aiChoiceImg.setImage(new Image(paper));
                }
                if(result1.getWinnerMove()==Move.Scissor) {
                    aiChoiceImg.setImage(new Image(scissor));
                }
            }
            /*if (result1.getWinnerPlayer().getPlayerType() == PlayerType.Human && result1.getType() != ResultType.Tie){
                humanWins++;
                whoWinsLabel.setText("Human won !");
            }
            //playerScoreLabel.setText(""+humanWins+"");

            if (result1.getWinnerPlayer().getPlayerType() == PlayerType.AI && result1.getType() != ResultType.Tie){
                aiWins++;
                whoWinsLabel.setText("AI won !");
            }
            //aiScoreLabel.setText(""+aiWins+"");
            //result = result1;
            */
        }

        Result result1 = (Result) ge.getGameState().getHistoricResults().toArray()[ge.getGameState().getHistoricResults().size()-1];
        if (result1.getWinnerPlayer().getPlayerType() == PlayerType.Human && result1.getType() != ResultType.Tie){
            popupShow("Won");
            globalhumanWins++;
        }
        if (result1.getWinnerPlayer().getPlayerType() == PlayerType.AI && result1.getType() != ResultType.Tie){
            popupShow("Lost");
            globalaiWins++;
        }
        if ( result1.getType() == ResultType.Tie){
            popupShow("Tie");
        }
        playerScoreLabel.setText(""+globalhumanWins+"");

        aiScoreLabel.setText(""+globalaiWins+"");
    }

    private void popupShow(String condition){
        FXMLLoader fxmlLoader;
        switch(condition) {
            case "Won" :
                 fxmlLoader = new FXMLLoader(getClass().getResource("../view/WinnerWindow.fxml"));
                break; // optional

            case "Lost" :
                 fxmlLoader = new FXMLLoader(getClass().getResource("../view/LoserWindow.fxml"));
                break; // optional

            // Tie
            default : // Optional
                 fxmlLoader = new FXMLLoader(getClass().getResource("../view/TieWindow.fxml"));
        }
        try {
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
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
