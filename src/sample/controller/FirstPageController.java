package sample.controller;

import javafx.fxml.Initializable;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;

import javax.naming.spi.InitialContextFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPageController implements Initializable{
    public void handleDragPic1(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasImage()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    public void handleDragPics2(DragEvent dragEvent) {
        if(dragEvent.getDragboard().hasImage()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
