package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.image.ImageView;
import logicPackage.enums.AlgoName;
import logicPackage.processing.Coloured;
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

public class Main extends Application {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private Button startCalculating;

    LogicController logicController = new LogicController();
    public static File populateWIithPicsAddr1 = null;
    public static List<File> populateWIithPicsAddr2 = new ArrayList<File>();
    public static List<AlgoName> algoNames = new ArrayList<>();

    public static List<File> initialImages1 = new ArrayList<>();
    public static List<File> initialImages2 = new ArrayList<>();

    @FXML
    private Button seeAllHistograms;

    @FXML
    private AnchorPane anchorFirstPage;

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
        root.getStylesheets().add(getClass().getResource("view/DarkTheme.css").toString());
        primaryStage.setTitle("Images");
        primaryStage.setScene(new Scene(root, 1000, 550));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void handleDrop1(DragEvent dragEvent) throws FileNotFoundException {
        List<File> images = dragEvent.getDragboard().getFiles();
        Image image = new Image(new FileInputStream(images.get(0)));
        imageView1.setImage(image);
        initialImages1.add(images.get(0));
        populateWIithPicsAddr1 = images.get(0);
    }

    public void handleDrop2(DragEvent dragEvent) throws FileNotFoundException, IOException {
        List<File> images = dragEvent.getDragboard().getFiles();
        for (int i = 0; i < images.size(); i++) {
            initialImages2.add(images.get(i));
            Image image = new Image(new FileInputStream(images.get(i)));
            imageView2.setImage(image);
            populateWIithPicsAddr2.add(images.get(i));
        }
    }

    public void fileChooser2(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);

        for (int i = 0; i < selectedFiles.size(); i++) {
            if (selectedFiles.get(i) != null) {

                Image image = new Image(new FileInputStream(selectedFiles.get(i)));
                imageView2.setImage(image);
                initialImages2.add(selectedFiles.get(i));
            }
        }
        populateWIithPicsAddr2.addAll(selectedFiles);
    }

    public void fileChooser1(ActionEvent actionEvent) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            Image image = new Image(new FileInputStream(selectedFile));
            imageView1.setImage(image);
            initialImages1.add(selectedFile);
            populateWIithPicsAddr1 = selectedFile;
        }
    }

    public void onMouseClick(MouseEvent mouseEvent) throws IOException {
        if (algoNames.isEmpty())
            logicController.getImgAndDoCalculatins(AlgoName.all, populateWIithPicsAddr1, populateWIithPicsAddr2);
        else {
            for (AlgoName algo : algoNames) {
                logicController.getImgAndDoCalculatins(algo, populateWIithPicsAddr1, populateWIithPicsAddr2);
            }
        }
        startCalculating.setDisable(true);
    }

    public void chooseAlgorithm(MouseEvent mouseEvent) {

    }

    public void downloadResults(MouseEvent mouseEvent) throws IOException {
        String filePath = "results.txt";
        String line = null;
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + filePath + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + filePath + "'");
        }
        try {
            String filePath1 = "C:\\Users\\Roxana\\Desktop\\results.txt";
            Path path = Paths.get(filePath1);
            boolean exists = Files.exists(path);
            System.out.println("File " + path.getFileName() + " exists: " + exists);

            if (exists) {
                boolean empty = Files.size(path) == 0;
                System.out.println("Empty: " + empty);
            }

            Files.write(path, lines, StandardCharsets.UTF_8);

            System.out.println("File written: " + path.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void euclidian(ActionEvent actionEvent) {
        algoNames.add(AlgoName.euclidianL2);
    }

    public void cityBlock(ActionEvent actionEvent) {
        algoNames.add(AlgoName.cityBlockL1);
    }

    public void minkowski(ActionEvent actionEvent) {
        algoNames.add(AlgoName.minkowskiLp);
    }

    public void cebyshevLinf(ActionEvent actionEvent) {
        algoNames.add(AlgoName.cebyshevLinf);
    }

    public void soresen(ActionEvent actionEvent) {
        algoNames.add(AlgoName.sorensen);
    }

    public void gower(ActionEvent actionEvent) {
        algoNames.add(AlgoName.gower);
    }

    public void soergel(ActionEvent actionEvent) {
        algoNames.add(AlgoName.soergel);
    }

    public void kulczynskid(ActionEvent actionEvent) {
        algoNames.add(AlgoName.kulczynskid);
    }

    public void canberra(ActionEvent actionEvent) {
        algoNames.add(AlgoName.canberra);
    }

    public void lorentzian(ActionEvent actionEvent) {
        algoNames.add(AlgoName.lorentzian);
    }

    public void intersectionDistance(ActionEvent actionEvent) {
        algoNames.add(AlgoName.intersectionDistance);
    }

    public void waveHedgesDistance(ActionEvent actionEvent) {
        algoNames.add(AlgoName.waveHedgesDistance);
    }

    public void similarityCzekanowski(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityCzekanowski);
    }

    public void distanceCzekanowski(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceCzekanowski);
    }

    public void similarityMotyka(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityMotyka);
    }

    public void distanceMotyka(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceMotyka);
    }

    public void similarityKulczynkyS(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityKulczynkyS);
    }

    public void distanceKulczynkyS(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceKulczynkyS);
    }

    public void ruzicka(ActionEvent actionEvent) {
        algoNames.add(AlgoName.ruzicka);
    }

    public void tanimoto(ActionEvent actionEvent) {
        algoNames.add(AlgoName.tanimoto);
    }

    public void innerProductSimilarity(ActionEvent actionEvent) {
        algoNames.add(AlgoName.innerProductSimilarity);
    }

    public void harmonicMeanSimilarity(ActionEvent actionEvent) {
        algoNames.add(AlgoName.harmonicMeanSimilarity);
    }

    public void cosineSimilarity(ActionEvent actionEvent) {
        algoNames.add(AlgoName.cosineSimilarity);
    }

    public void kumarHassebrookDistance(ActionEvent actionEvent) {
        algoNames.add(AlgoName.kumarHassebrookDistance);
    }

    public void similarityJaccard(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityJaccard);
    }

    public void distanceJaccard(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceJaccard);
    }

    public void similarityDice(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityDice);
    }

    public void distanceDice(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceDice);
    }

    public void similarityFidelity(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similarityFidelity);
    }

    public void distanceBhattacharyya(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceBhattacharyya);
    }

    public void distanceHellinger(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceHellinger);
    }

    public void distanceMatusita(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceMatusita);
    }

    public void distanceSquaredChord(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceSquaredChord);
    }

    public void similaritySquaredChord(ActionEvent actionEvent) {
        algoNames.add(AlgoName.similaritySquaredChord);
    }

    public void distanceSquaredEuclidian(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceSquaredEuclidian);
    }

    public void distancePearson(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distancePearson);
    }

    public void distanceNeyman(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceNeyman);
    }

    public void distanceSquared(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceSquared);
    }

    public void distanceProbabilisticSymmetric(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceProbabilisticSymmetric);
    }

    public void distanceDivergence(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceDivergence);
    }

    public void distanceClark(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceClark);
    }

    public void distanceAdditiceSymmetric(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceAdditiceSymmetric);
    }

    public void distanceKullbackLeibler(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceKullbackLeibler);
    }

    public void distanceJeffreys(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceJeffreys);
    }

    public void distanceKDivergence(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceKDivergence);
    }

    public void distanceTopsoe(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceTopsoe);
    }

    public void distanceJensenShannon(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceJensenShannon);
    }

    public void distanceJensenDifference(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceJensenDifference);
    }

    public void distanceKumarJohnson(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceKumarJohnson);
    }

    public void distanceAvg(ActionEvent actionEvent) {
        algoNames.add(AlgoName.distanceAvg);
    }

    public void launchWIn2(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view/SecondPage.fxml"));
        anchorFirstPage.getChildren().setAll(pane);
    }

    public void closeWindow(MouseEvent mouseEvent) {
    }

    public void launchWIn3p(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view/ThirdPage.fxml"));
        anchorFirstPage.getChildren().setAll(pane);
    }

    public void launchWIn4(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("view/FourthPage.fxml"));
        anchorFirstPage.getChildren().setAll(pane);
    }
}
