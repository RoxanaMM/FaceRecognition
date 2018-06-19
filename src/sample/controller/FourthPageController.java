package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

public class FourthPageController extends Main {

    @FXML
    private Menu algorithmMenu;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Fourth.fxml"));

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public void populateAlgorithmChoosed(MouseEvent mouseEvent) {
        algorithmMenu.getItems().addAll((MenuItem) algoNames);
    }
}
