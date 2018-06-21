package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logicPackage.processing.Coloured;
import sample.Main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SecondPageController extends Main {
    @FXML
    private ImageView loadHisto1;
    @FXML
    private ScrollPane scPane1;

    @FXML
    private VBox vBox1;
    @FXML
    private Button mostSimilar1;
    private List<ImageView>histoImages = new ArrayList<ImageView>();
    @FXML
    private HBox vertical2;
    @FXML
    private HBox vertical1;
    Coloured coloured = new Coloured();

    @FXML
    private HBox hboxNou;

    @FXML
    private AnchorPane anchorSecondPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader root = FXMLLoader.load(getClass().getResource("view/SecondPage.fxml"));
      //  root.getResources().getStylesheets().add(getClass().getResource("view/DarkTheme.css").toString());
        primaryStage.setTitle("Images");
        VBox vBox = new VBox(20);
        primaryStage.setScene(new Scene(vBox, 300, 275));
        primaryStage.show();

    }

    public void getHistogram1(ActionEvent actionEvent) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(coloured.getHistogram(populateWIithPicsAddr1.toString())));
        loadHisto1.setImage(image);
    }

    public void showColouredHisto1(MouseEvent mouseEvent) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(coloured.getHistogram(populateWIithPicsAddr1.toString())));
        loadHisto1.setImage(image);
    }

    public void seeAllHistograms(ActionEvent actionEvent) {
    }

    public void showShapeHisto1(MouseEvent mouseEvent) {

    }

    public void showTextureHisto1(MouseEvent mouseEvent) throws FileNotFoundException {

    }

    public void goBack(ActionEvent actionEvent) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("view/FirstPage.fxml"));
//        anchorSecondPane.getChildren().setAll(pane);

    }
}
