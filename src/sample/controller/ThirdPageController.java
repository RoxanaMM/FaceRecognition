package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logicPackage.processing.Calculate;
import sample.Main;

import java.io.IOException;
import java.util.List;

public class ThirdPageController extends Main {


    @FXML
    private AnchorPane anchorThird;
    @FXML
    private ImageView imageLayout1;

    private List<ImageView> imageLayout2;
    @FXML
    private Label labelSorry;
    Calculate calculate = new Calculate();
    @FXML
    private Label algoResults;
    static String concatEverythingHereRed = new String();
    static String concatEverythingHereGreen = new String();
    static String concatEverythingHereBlue = new String();


    @FXML
    private VBox vBox3rd;


    @FXML
    private ScrollPane scrollPane3rd;

    @FXML
    private Label bigRedText;

    @FXML
    private Label bigGreenText;

    @FXML
    private Label bigBlueText;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/ThirdPage.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public void launchWIn3p(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(blah);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }
}
