package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.image.ImageView ;
import org.opencv.core.Core;
import sample.controller.LogicController;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static logicPackage.processing.Calculate.calculateDistance;

public class Main extends Application {

    LogicController logicController = new LogicController();
    static File populateWIithPicsAddr1 = null;
    static List<File> populateWIithPicsAddr2 = new ArrayList<File>();

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;

    @FXML
    private Button startCalculating;

    public void handleDragPic1(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
    }


    public void handleDragPics2(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.ANY);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/FirstPage.fxml"));
//        GridPane grid = new GridPane();
//        Scene scene = new Scene(grid, 1550,1550);
//        scene.getStylesheets().add("view/DarkTheme.css");
        root.getStylesheets().add(getClass().getResource("view/DarkTheme.css").toString());
        primaryStage.setTitle("Images");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void handleDrop1(DragEvent dragEvent) throws FileNotFoundException{
        List<File> images = dragEvent.getDragboard().getFiles();
        Image image = new Image(new FileInputStream(images.get(0)));
        imageView1.setImage(image);
        populateWIithPicsAddr1=images.get(0);
    }

    public void handleDrop2(DragEvent dragEvent) throws FileNotFoundException, IOException {
        List<File> images = dragEvent.getDragboard().getFiles();
        for(int i=0;i<images.size();i++) {
            Image image = new Image(new FileInputStream(images.get(i)));
            imageView2.setImage(image);
            populateWIithPicsAddr2.add(images.get(i));
        }
       // logicController.getImgAndDoCalculatins(populateWIithPicsAddr1,populateWIithPicsAddr2);


    }

    public void fileChooser2(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        for (int i = 0; i < selectedFiles.size(); i++){
            if (selectedFiles.get(i) != null) {

                Image image = new Image(new FileInputStream(selectedFiles.get(i)));
                imageView2.setImage(image);
            }
        }
        populateWIithPicsAddr2.addAll(selectedFiles);
      //  logicController.getImgAndDoCalculatins(populateWIithPicsAddr1,populateWIithPicsAddr2);
    }

    public void fileChooser1(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile != null){

            Image image = new Image(new FileInputStream(selectedFile));
            imageView1.setImage(image);
            populateWIithPicsAddr1 = selectedFile;
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        logicController.getImgAndDoCalculatins(populateWIithPicsAddr1,populateWIithPicsAddr2);
        startCalculating.setDisable(true);
    }

    public void chooseAlgorithm(MouseEvent mouseEvent) {

    }

    public  void downloadResults(MouseEvent mouseEvent) throws IOException {
        String filePath="results.txt";
        String line = null;
        List<String>lines= new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(filePath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            filePath + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        try {

            String filePath1 ="C:\\Users\\Roxana\\Desktop\\mama.txt";
            Path path = Paths.get(filePath1);
            boolean exists = Files.exists(path);

            System.out.println( "File " + path.getFileName() + " exists: " + exists);

            if( exists) {
                boolean empty = Files.size(path) == 0;
                System.out.println("Empty: " + empty);
            }

            Files.write(path, lines, StandardCharsets.UTF_8);

            System.out.println("File written: " + path.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
