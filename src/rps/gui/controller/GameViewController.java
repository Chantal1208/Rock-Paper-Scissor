package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import rps.bll.game.Move;

import java.net.URL;
import java.util.Locale;
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
    }
}
