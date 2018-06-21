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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

    public void showSimilar(ActionEvent actionEvent) throws FileNotFoundException {
        Image image = new Image(new FileInputStream(initialImages1.get(0)));
        List<BestFitModel> statisticsBestFit = statisticsBestFit();
        imageLayout1.setImage(image);
        Set<File> imagesForImageViewer22 = calculate.computeBestFit();
        List imagesForImageViewer2 = new ArrayList(imagesForImageViewer22);
        if(imagesForImageViewer2.size() == 0 ){
            labelSorry.setText("Sorry but none of the images given fit the test image");
        }else {
            for(File f: imagesForImageViewer22) {
            vBox3rd.getChildren().add(new ImageView(new Image(new FileInputStream(String.valueOf(f)), 220, 220,false,false)));
            scrollPane3rd.setContent(vBox3rd);
            }
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
