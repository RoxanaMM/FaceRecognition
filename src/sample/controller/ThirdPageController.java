package sample.controller;


import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.scene.text.TextFlow;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;
import logicPackage.model.BestFitModel;
import logicPackage.processing.Calculate;
import logicPackage.processing.Coloured;
import sample.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static logicPackage.processing.Calculate.statisticsBestFit;

public class ThirdPageController extends Main {


    @FXML
    private AnchorPane anchorThird;

    @FXML
    private ImageView imageLayout1;
    @FXML
    private ImageView imageLayout2_11;
    @FXML
    private ImageView imageLayout2_22;
    @FXML
    private ImageView imageLayout2_33;
    @FXML
    private ImageView imageLayout2_44;
    @FXML
    private ImageView imageLayout2_55;
    @FXML
    private ImageView imageLayout2_66;
    @FXML
    private ImageView imageLayout2_77;
    @FXML
    private ImageView imageLayout2_88;
    @FXML
    private ImageView imageLayout2_99;
    @FXML
    private ImageView imageLayout2_1010;
    @FXML
    private ImageView imageLayout2_1111;
    @FXML
    private ImageView imageLayout2_1212;
    @FXML
    private Label labelSorry;
    Calculate calculate = new Calculate();
    @FXML
    private Label algoResults;
    static String concatEverythingHereRed = new String();
    static String concatEverythingHereGreen = new String();
    static String concatEverythingHereBlue = new String();


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

    public void showSimilar(ActionEvent actionEvent) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(initialImages1.get(0)));
        List<BestFitModel> statisticsBestFit = statisticsBestFit();
        imageLayout1.setImage(image);
        Set<File> imagesForImageViewer22 = calculate.computeBestFit();
        List imagesForImageViewer2 = new ArrayList(imagesForImageViewer22);
        if(imagesForImageViewer2.size() == 0 ){
            labelSorry.setText("Sorry but none of the images given fit the test image");
        }else {
            if (imagesForImageViewer2.size() >= 1)
                imageLayout2_11.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(0)))));
            if (imagesForImageViewer2.size() >= 2)
                imageLayout2_22.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(1)))));
            if (imagesForImageViewer2.size() >= 3)
                imageLayout2_33.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(2)))));
            if (imagesForImageViewer2.size() >= 4)
                imageLayout2_44.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(3)))));
            if (imagesForImageViewer2.size() >= 5)
                imageLayout2_55.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(4)))));
            if (imagesForImageViewer2.size() >= 6)
                imageLayout2_66.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(5)))));
            if (imagesForImageViewer2.size() >= 7)
                imageLayout2_77.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(6)))));
            if (imagesForImageViewer2.size() >= 8)
                imageLayout2_88.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(7)))));
            if (imagesForImageViewer2.size() >= 9)
                imageLayout2_99.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(8)))));
            if (imagesForImageViewer2.size() >= 10)
                imageLayout2_1010.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(9)))));
            if (imagesForImageViewer2.size() >= 11)
                imageLayout2_1111.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(10)))));
            if (imagesForImageViewer2.size() >= 12)
                imageLayout2_1212.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(11)))));
            algoResults.setText("The algorithms with best restults are:  ");

            for (BestFitModel model : statisticsBestFit) {
                String name = model.getAlgo().name();
                String result = model.getAlgoCalculationSingleResult();
                if(model.getChannelIndicator() == 0)
                    concatEverythingHereRed = concatEverythingHereRed.concat(name).concat("\n").concat(result).concat("\n");
                if(model.getChannelIndicator() == 1)
                    concatEverythingHereGreen = concatEverythingHereGreen.concat(name).concat("\n").concat(result).concat("\n");
                if(model.getChannelIndicator() == 2)
                    concatEverythingHereBlue = concatEverythingHereBlue.concat(name).concat("\n").concat(result).concat("\n");

            }
            bigRedText.setText(concatEverythingHereRed);
            bigGreenText.setText(concatEverythingHereGreen);
            bigBlueText.setText(concatEverythingHereBlue);

        }
    }
}
