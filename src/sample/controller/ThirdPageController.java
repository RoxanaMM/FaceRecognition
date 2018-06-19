package sample.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
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

    Calculate calculate = new Calculate();


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
        imageLayout1.setImage(image);
        Set<File> imagesForImageViewer22 = calculate.computeBestFit();
        List imagesForImageViewer2 = new ArrayList(imagesForImageViewer22);
        if(imagesForImageViewer2.size() >= 1)
        imageLayout2_11.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(0)))));
        if(imagesForImageViewer2.size() >=  2)
        imageLayout2_22.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(1)))));
        if(imagesForImageViewer2.size() >=  3)
        imageLayout2_33.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(2)))));
        if(imagesForImageViewer2.size() >= 4)
        imageLayout2_44.setImage(new Image(new FileInputStream(String.valueOf(imagesForImageViewer2.get(3)))));

    }
}
//        extends Main {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("view/ThirdPage.fxml"));
//        root.getStylesheets().add(getClass().getResource("view/DarkTheme.css").toString());
//        primaryStage.setTitle("Images");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//
//    }
//}
