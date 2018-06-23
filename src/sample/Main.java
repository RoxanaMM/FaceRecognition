package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import org.opencv.core.Core;
import sample.controller.LogicController;

import javax.xml.crypto.Data;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    @FXML
    private ImageView imageView1;
    @FXML
    private Button startCalculating;

    @FXML
    private Label labelInitilize;
    @FXML
    private Label labelTrainned;

    LogicController logicController = new LogicController();
    public static File populateWIithPicsAddr1 = null;
    public static List<AlgoName> algoNames = new ArrayList<>();
    public static List<DatabaseName> databaseName = new ArrayList<>();
    public static List<Integer> nrOfIterations = new ArrayList<>();
    public static final String documentSufix = "C:\\forMaster\\temaDisertatie\\backupPic\\Corel1000\\";
    public static List<File> backGroundPicsAddressesList = new ArrayList<>();
    public static List<File> initialImages1 = new ArrayList<>();
    public static List<String> similarPhotosResults = new ArrayList<>();
    public static List<String> nonSimilarPhotosResults = new ArrayList<>();
    public static List<Double> trainingResultsRGB = new ArrayList<>();
    public static List<Double> trainingNonSimResultsRGB = new ArrayList<>();
    public static List<Double> testResultsRGB = new ArrayList<>();
    public static String nameOfSet = new String();
    public static Integer nrOfIterationsChoosen;
    public static File[] listOfFiles;
    private static String backgroundFile = "Mountains";
    @FXML
    private AnchorPane anchorFirstPage;
    @FXML
    private Label labelWithName;

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

    //<editor-fold desc="Description">
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
        databaseName.add(DatabaseName.Busses);
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
        nrOfIterations.add(50);
    }

    public void choose100000(ActionEvent actionEvent) {
        nrOfIterations.add(90);
    }
    //</editor-fold>

    public static void buildBackGroundPicsAddresses() {
        File folder = new File(documentSufix.concat(backgroundFile));
        File[] listOfFiles = folder.listFiles();
        for (File firstFileEntry : listOfFiles) {
            if (firstFileEntry.getAbsolutePath().contains("jpg") || firstFileEntry.getAbsolutePath().contains("JPG")) {
                backGroundPicsAddressesList.add(new File(firstFileEntry.getAbsolutePath()));
            }
        }
    }

    public List<Double> transformToDouble(List<String> stringList) {
        List<Double> doubleList = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            doubleList.add(Double.parseDouble(stringList.get(i)));
        }
        return doubleList;
    }

    public void compareWithAllSimilar() throws IOException {
        List<Double> currentValsSim = new ArrayList<>();
        List<Double> currentValsNotSim = new ArrayList<>();

        List<Double> finalSim = new ArrayList<>();
        List<Double> finalNonSim = new ArrayList<>();


        List<List<Double>> allInOneSim = new ArrayList<>();
        List<List<Double>> allInOneNonSim = new ArrayList<>();

        int similar = 0;
        int nonSimilar = 0;

        File folder = new File(documentSufix.concat(nameOfSet));
        for (AlgoName algo : algoNames) {
            similarPhotosResults = logicController.getImgAndDoCalculatins(algo, populateWIithPicsAddr1, Arrays.asList(listOfFiles));
            nonSimilarPhotosResults = logicController.getImgAndDoCalculatins(algo, populateWIithPicsAddr1, backGroundPicsAddressesList);

            finalSim = transformToDouble(similarPhotosResults);
            allInOneSim = takeRgbInOne(finalSim);

            finalNonSim = transformToDouble(nonSimilarPhotosResults);
            allInOneNonSim = takeRgbInOne(finalNonSim);


            Collections.sort(allInOneSim.get(0));
            Collections.sort(allInOneSim.get(1));
            Collections.sort(allInOneSim.get(2));
            Collections.sort(allInOneNonSim.get(0));
            Collections.sort(allInOneNonSim.get(1));
            Collections.sort(allInOneNonSim.get(2));

            finalSim.clear();
            finalNonSim.clear();
            List<Integer>nrOfIterationsChoosen1 = new ArrayList<>();
            nrOfIterationsChoosen1.add(5);
            nrOfIterationsChoosen1.add(10);
            nrOfIterationsChoosen1.add(50);
            nrOfIterationsChoosen1.add(90);
            for(int j=0;j<4;j++) {
                for (int i = 0; i < nrOfIterationsChoosen1.get(j); i++) {
                    if (allInOneSim.get(0).get(i) < allInOneNonSim.get(0).get(i)) {
                        similar++;
                    } else {
                        nonSimilar++;
                    }

                    if (allInOneSim.get(1).get(i) < allInOneNonSim.get(1).get(i)) {
                        similar++;
                    } else {
                        nonSimilar++;
                    }

                    if (allInOneSim.get(2).get(i) < allInOneNonSim.get(2).get(i)) {

                    } else {
                        nonSimilar++;
                    }

                }
                if (similar > nonSimilar) {
                    System.out.println("Photo is found as being " + nameOfSet);
                }

                writeInFileTrainValues("test", populateWIithPicsAddr1.getAbsolutePath(), algo, nrOfIterationsChoosen1.get(j), similar, nonSimilar);
                similar = 0;
                nonSimilar = 0;
            }
        }

    }

    public void writeInFileTrainValues(String testOrTrain, String populateWithPicAddr, AlgoName a, Integer nrOfIterationsChoosen1, int siimilar, int nonSimilar) throws IOException {
        PrintWriter writer = null;

        writer = new PrintWriter(new FileWriter("results1.txt", true));
        writer.println(nameOfSet);
        writer.println("Number of iterations: ");
        writer.println(nrOfIterationsChoosen1);
        writer.println(a);
        writer.println(testOrTrain);
        writer.println(populateWithPicAddr);
        writer.println("Similare pentru : " + nameOfSet);
        writer.println(siimilar);
        writer.println("Similare pentru : " + backgroundFile);
        writer.println(nonSimilar);
        writer.println("\n");
        writer.close();
    }

    public List<Double> normalizaVals(List<Double> sim) {
        List<Double> newList = new ArrayList<>();
        Collections.sort(sim);
        Double minim = sim.get(0);
        Collections.reverse(sim);
        Double maxim = sim.get(0);
        Double helper;
        for (int i = 0; i < sim.size(); i++) {
            Double numarator = (maxim - minim) == 0 ? 1 : (maxim - minim);

            helper = (sim.get(i) - minim) / numarator;
            newList.add(helper);

        }
        return newList;
    }

    public void calculations() throws IOException {
        String train = "train";
        for (int i = 0; i <= 1; i++) {
            for (File firstFileEntry : listOfFiles) {
                if (firstFileEntry.getAbsolutePath().contains("jpg") || firstFileEntry.getAbsolutePath().contains("JPG")) {
                    for (AlgoName algo : algoNames) {
                        similarPhotosResults = logicController.getImgAndDoCalculatins(algo, new File(firstFileEntry.getAbsolutePath()), Arrays.asList(listOfFiles));
                        // trainingResultsRGB = takeFinalResults(normalizaVals(transformToDouble(similarPhotosResults)), trainingResultsRGB);
                    }
                    for (AlgoName algo : algoNames) {
                        nonSimilarPhotosResults = logicController.getImgAndDoCalculatins(algo, new File(firstFileEntry.getAbsolutePath()), backGroundPicsAddressesList);
                        //   trainingNonSimResultsRGB = takeFinalResults(normalizaVals(transformToDouble(nonSimilarPhotosResults)), trainingNonSimResultsRGB);
                    }
                }
            }
            System.out.println("Done");
            labelTrainned.setText("Set trainned!");
        }
        //writeInFileTrainValues(train, trainingResultsRGB);
    }

    public void initializeChooseSet(ActionEvent actionEvent) throws IOException {
        buildBackGroundPicsAddresses();

        nameOfSet = databaseName.get(0).name();
        labelWithName.setText(nameOfSet);
        nrOfIterationsChoosen = nrOfIterations.get(0);
        File folder = new File(documentSufix.concat(nameOfSet));
        listOfFiles = folder.listFiles();
        labelInitilize.setText("Set initilized");
    }


    public static Double helpMeCalculate(List<Double> resultsForChannel) {
        Double sum = 0.00;
        for (int i = 0; i < resultsForChannel.size(); i++)
            sum = sum + resultsForChannel.get(i);
        return sum / resultsForChannel.size();
    }

    public List<List<Double>> takeRgbInOne(List<Double> sim) {
        List<Double> helperRed = new ArrayList<>();
        List<Double> helperBlue = new ArrayList<>();
        List<Double> helperGreen = new ArrayList<>();
        List<List<Double>> allChannelsInOne = new ArrayList<>();
        helperRed.add(sim.get(0));
        helperGreen.add(sim.get(1));
        helperBlue.add(sim.get(2));
        for (int i = 3; i < sim.size(); i = i + 3) {
            helperRed.add(sim.get(i));
            helperGreen.add(sim.get(i + 1));
            helperBlue.add(sim.get(i + 2));
        }

        allChannelsInOne.add(helperRed);
        allChannelsInOne.add(helperGreen);
        allChannelsInOne.add(helperBlue);
//        if (trainingResultsRGB.size() > 0) {
//            trainingResultsRGB.set(0, (trainingResultsRGB.get(0) + helpMeCalculate(helperRed)) / 2);
//            trainingResultsRGB.set(1, (trainingResultsRGB.get(1) + helpMeCalculate(helperGreen)) / 2);
//            trainingResultsRGB.set(2, (trainingResultsRGB.get(2) + helpMeCalculate(helperBlue)) / 2);
//        } else {
//            trainingResultsRGB.add(helpMeCalculate(helperRed));
//            trainingResultsRGB.add(helpMeCalculate(helperGreen));
//            trainingResultsRGB.add(helpMeCalculate(helperBlue));
//        }
        return allChannelsInOne;
    }

    public void onMouseClick(MouseEvent mouseEvent) {
    }

    public void startCalculating(ActionEvent actionEvent) throws IOException {
        compareWithAllSimilar();
    }

    public void trainThisSet(ActionEvent actionEvent) throws IOException {
        calculations();
    }

    public void all(ActionEvent actionEvent) {
        algoNames.add(AlgoName.euclidianL2);
        algoNames.add(AlgoName.cityBlockL1);
        algoNames.add(AlgoName.minkowskiLp);
        algoNames.add(AlgoName.cebyshevLinf);
        algoNames.add(AlgoName.sorensen);
        algoNames.add(AlgoName.gower);
        algoNames.add(AlgoName.soergel);
        algoNames.add(AlgoName.kulczynskid);
        algoNames.add(AlgoName.canberra);
        algoNames.add(AlgoName.lorentzian);
        algoNames.add(AlgoName.intersectionDistance);
        algoNames.add(AlgoName.waveHedgesDistance);
        algoNames.add(AlgoName.similarityCzekanowski);
        algoNames.add(AlgoName.distanceCzekanowski);
        algoNames.add(AlgoName.similarityMotyka);
        algoNames.add(AlgoName.distanceMotyka);
        algoNames.add(AlgoName.similarityKulczynkyS);
        algoNames.add(AlgoName.distanceKulczynkyS);
        algoNames.add(AlgoName.ruzicka);
        algoNames.add(AlgoName.tanimoto);
        algoNames.add(AlgoName.innerProductSimilarity);
        algoNames.add(AlgoName.harmonicMeanSimilarity);
        algoNames.add(AlgoName.cosineSimilarity);
        algoNames.add(AlgoName.kumarHassebrookDistance);
        algoNames.add(AlgoName.similarityJaccard);
        algoNames.add(AlgoName.distanceJaccard);
        algoNames.add(AlgoName.similarityDice);
        algoNames.add(AlgoName.distanceDice);
        algoNames.add(AlgoName.similarityFidelity);
        algoNames.add(AlgoName.distanceBhattacharyya);
        algoNames.add(AlgoName.distanceHellinger);
        algoNames.add(AlgoName.distanceMatusita);
        algoNames.add(AlgoName.distanceSquaredChord);
        algoNames.add(AlgoName.similaritySquaredChord);
        algoNames.add(AlgoName.distanceSquaredEuclidian);
        algoNames.add(AlgoName.distancePearson);
        algoNames.add(AlgoName.distanceNeyman);
        algoNames.add(AlgoName.distanceSquared);
        algoNames.add(AlgoName.distanceProbabilisticSymmetric);
        algoNames.add(AlgoName.distanceDivergence);
        algoNames.add(AlgoName.distanceClark);
        algoNames.add(AlgoName.distanceAdditiceSymmetric);
        algoNames.add(AlgoName.distanceKullbackLeibler);
        algoNames.add(AlgoName.distanceJeffreys);
        algoNames.add(AlgoName.distanceKDivergence);
        algoNames.add(AlgoName.distanceTopsoe);
        algoNames.add(AlgoName.distanceJensenShannon);
        algoNames.add(AlgoName.distanceJensenDifference);
        algoNames.add(AlgoName.distanceKumarJohnson);
        algoNames.add(AlgoName.distanceAvg);

    }
}
