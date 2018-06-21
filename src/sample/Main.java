package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logicPackage.enums.AlgoName;
import logicPackage.enums.DatabaseName;
import logicPackage.model.BestFitModel;
import org.opencv.core.Core;
import sample.controller.LogicController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main extends Application {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @FXML
    private ImageView imageView1;
    @FXML
    private Button startCalculating;

    LogicController logicController = new LogicController();
    public static File populateWIithPicsAddr1 = null;
    public static List<AlgoName> algoNames = new ArrayList<>();
    public static List<DatabaseName> databaseName = new ArrayList<>();
    public static List<Integer> nrOfIterations = new ArrayList<>();
    public static final String documentSufix = "C:\\forMaster\\temaDisertatie\\backupPic\\Corel1000\\";
    public static List<File> backGroundPicsAddressesList = new ArrayList<>();
    public static List<File> initialImages1 = new ArrayList<>();
    public static List<Map< Integer, List<BestFitModel>>> similarPhotosResults = new ArrayList<>();
    public static List<Map< Integer, List<BestFitModel>>> nonSimilarPhotosResults = new ArrayList<>();
    public static List<Map<Integer, Double>>finalResultSimilar = new ArrayList<>();
    public static List<Map<Integer,Double>>finalResultNonSimilar = new ArrayList<>();

    @FXML
    private AnchorPane anchorFirstPage;

    public void handleDragPic1(DragEvent dragEvent) {
        if (dragEvent.getDragboard().hasFiles())
            dragEvent.acceptTransferModes(TransferMode.ANY);
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

    public void chooseDataBase(MouseEvent mouseEvent) {
    }

    public void Africa(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Africa);
    }

    public void Beach(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Beach);
    }

    public void Buildings(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Buildings);
    }

    public void Buses(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Buses);
    }

    public void Dinosaurs(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Dinosaurs);
    }

    public void Elephants(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Elephants);
    }

    public void Flowers(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Flowers);
    }

    public void Food(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Food);
    }

    public void Horses(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Horses);
    }

    public void Mountains(ActionEvent actionEvent) {
        databaseName.add(DatabaseName.Mountains);
    }

    public void choose500(ActionEvent actionEvent) {
        nrOfIterations.add(5);
    }

    public void choose1000(ActionEvent actionEvent) {
        nrOfIterations.add(10);
    }

    public void choose10000(ActionEvent actionEvent) {
        nrOfIterations.add(15);
    }

    public void choose100000(ActionEvent actionEvent) {
        nrOfIterations.add(20);
    }

    public static void buildBackGroundPicsAddresses() {
        File folder = new File(documentSufix.concat("backgroundSet"));
        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            if (firstFileEntry.getAbsolutePath().contains("jpg") || firstFileEntry.getAbsolutePath().contains("JPG")) {
                backGroundPicsAddressesList.add(new File(firstFileEntry.getAbsolutePath()));
            }
        }
    }


    public void trainChooseSet(ActionEvent actionEvent) throws IOException {
        buildBackGroundPicsAddresses();
        Map< Integer, List<BestFitModel>>sim = new HashMap<>();
        Map< Integer, List<BestFitModel>> nonSim = new HashMap<>();
        String nameOfSet = databaseName.get(0).name();
        Integer nrOfIterationsChoosen = nrOfIterations.get(0);
        File folder = new File(documentSufix.concat(nameOfSet));
        File[] listOfFiles = folder.listFiles();
                for (File firstFileEntry : listOfFiles) {
                    if (firstFileEntry.getAbsolutePath().contains("jpg") || firstFileEntry.getAbsolutePath().contains("JPG")) {
                     //   if (k == 0) {
                            if (algoNames.isEmpty()) {
                                sim.putAll(logicController.getImgAndDoCalculatins(AlgoName.all, new File(firstFileEntry.getAbsolutePath()), Arrays.asList(listOfFiles)));
                            similarPhotosResults.add(sim);
                            } else {
                                for (AlgoName algo : algoNames) {
                                    sim.putAll(logicController.getImgAndDoCalculatins(algo, new File(firstFileEntry.getAbsolutePath()), Arrays.asList(listOfFiles)));
                                    similarPhotosResults.add(sim);
                                }
                            }
                      //  } else {

                            }
                       // }
                    }






            for (File firstFileEntry : listOfFiles) {
                if (firstFileEntry.getAbsolutePath().contains("jpg") || firstFileEntry.getAbsolutePath().contains("JPG")) {
                    if (algoNames.isEmpty()) {
                        nonSim.putAll(logicController.getImgAndDoCalculatins(AlgoName.all, new File(firstFileEntry.getAbsolutePath()), backGroundPicsAddressesList));
                        nonSimilarPhotosResults.add(nonSim);
                    } else {
                        for (AlgoName algo : algoNames) {
                            nonSim.putAll(logicController.getImgAndDoCalculatins(algo, new File(firstFileEntry.getAbsolutePath()), backGroundPicsAddressesList));
                        nonSimilarPhotosResults.add(nonSim);
                        }

                    }
                }
            }

     //   takeFinalResults();
    }


    public static Double helpMeCalculate(List<Double> resultsForChannel){
        Double sum=0.00;
        for(int i=0; i<resultsForChannel.size();i++)
            sum= sum + resultsForChannel.get(i);
        return sum;
    }

//    public void takeFinalResults (){
//
//        Map <Integer, List<Double>> sim = displayOnScreen(similarPhotosResults);
//        Map <Integer, List<Double>> NonSim = displayOnScreen(nonSimilarPhotosResults);
//        List <Double> listOfDoubleValues = new ArrayList<Double>();
//        Map<Integer, Double>helper = new HashMap<>();
//        for(int j=0;j<=2;j++){
//            for(int i =0;i<=sim.get(j).size();i++){
//                listOfDoubleValues=sim.get(j);
//            }
//            helper.put(j, helpMeCalculate(listOfDoubleValues));
//            finalResultSimilar.add(helper);
//            helper = new HashMap<>();
//        }
//        helper = new HashMap<>();
//
//        for(int j=0;j<=2;j++){
//            for(int i =0;i<=sim.get(j).size();i++){
//                listOfDoubleValues=sim.get(j);
//            }
//            helper.put(j, helpMeCalculate(listOfDoubleValues));
//            finalResultNonSimilar.add(helper);
//            helper = new HashMap<>();
//        }
//        System.out.println("similar");
//        for(int i =0;i<finalResultSimilar.size();i++){
//            System.out.println(finalResultSimilar.get(i).get(0));;
//        }
//        System.out.println("nesimilar");
//        for(int i =0;i<finalResultSimilar.size();i++){
//            System.out.println(finalResultNonSimilar.get(i).get(0));;
//        }
//
//    }


    public Map <Integer, List<Double>> displayOnScreen(Map< Integer, List<BestFitModel>>similarPhotosResults1){
        Integer oldIndex = 0;
        List<Double>doubleValues = new ArrayList<>();
        Map <Integer, List<Double>> similarStuff = new HashMap<>();
        for(int j=0;j<similarPhotosResults1.size();j++) {
            for (int i = 0; i < similarPhotosResults1.get(j).size(); i++) {
                String result = similarPhotosResults1.get(j).get(i).getAlgoCalculationResult().get(0);
                Double resultDouble = Double.parseDouble(result);
                doubleValues.add(resultDouble);
            }
            similarStuff.put(j, doubleValues);
            doubleValues = new ArrayList<>();
        }
        return similarStuff;
    }
    public void onMouseClick(MouseEvent mouseEvent) {
    }
}
