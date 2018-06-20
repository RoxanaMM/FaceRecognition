package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logicPackage.model.BestFitModel;
import logicPackage.processing.Coloured;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static logicPackage.processing.Calculate.algoCalculationResult;
import static logicPackage.processing.Calculate.statisticsBestFit;

public class SecondPageController extends Main {
    @FXML
    private ImageView loadHisto1;
//    @FXML
//    private ImageView histoViewer2_1;
//    @FXML
//    private ImageView histoViewer2_2;
//    @FXML
//    private ImageView histoViewer2_3;
//    @FXML
//    private ImageView histoViewer2_4;
//    @FXML
//    private ImageView histoViewer2_5;
//    @FXML
//    private ImageView histoViewer2_6;
//    @FXML
//    private ImageView histoViewer2_7;
//    @FXML
//    private ImageView histoViewer2_8;
//    @FXML
//    private ImageView histoViewer2_9;
//    @FXML
//    private ImageView histoViewer2_10;
//    @FXML
//    private ImageView histoViewer2_11;
//    @FXML
//    private ImageView histoViewer2_12;
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

    public void getHistograms2(ActionEvent actionEvent) throws FileNotFoundException {
        List<String> histogramAddresses = new ArrayList<>();
        List<Image> images = new ArrayList<Image>();
        int index =0;
        for (File f : populateWIithPicsAddr2) {
            String result = f.toString();
            result = result.substring(result.length()- 15, result.length());

            histogramAddresses.add(coloured.getHistogram(f.toString()));
            images.add(new Image(new FileInputStream(coloured.getHistogram(f.toString()))));
            vertical1.getChildren().add(index, new ImageView(new Image(new FileInputStream(coloured.getHistogram(f.toString())),220,220,false,false)));
            vertical2.getChildren().add(index, new Label(result));
            vertical2.getChildren().add(index, new Label(" " ));
          //  vertical1.setAccessibleText().add(label,2,index);
         //   paneForHisto.add(new ImageView(new Image(new FileInputStream(coloured.getHistogram(f.toString())))),2,index);
          //  paneForHisto.getChildren().add(new ImageView(new Image(new FileInputStream(coloured.getHistogram(f.toString())))));
            index++;
            //hboxNou.getChildren().add(new ImageView(new Image(new FileInputStream(coloured.getHistogram(f.toString())))));

        }

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
