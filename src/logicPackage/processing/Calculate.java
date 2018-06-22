package logicPackage.processing;

import logicPackage.algorithms.Algorithms;
import logicPackage.model.BestFitModel;
import logicPackage.model.Model;
import logicPackage.model.Results;
import logicPackage.enums.AlgoName;

import javax.swing.text.html.ListView;
import java.io.*;
import java.util.*;

public class Calculate extends Algorithms {
    public static HashMap<AlgoName, Command> keepAlgoFunc = new HashMap<AlgoName, Command>();
    public static String algoCalculationResult = new String();
    public static Integer contor = 0;

    static int indicator = 0;

    static Map<Integer, AlgoName> algoNameMap = new HashMap<Integer, AlgoName>();

    public static void buildNameMaps() {
        int z = 0;
        for (AlgoName algoName : AlgoName.values()) {
            algoNameMap.put(z, algoName);
            z++;
        }
    }

    public static String calculateDistance(File populateWIithPicsAddr1, File populateWIithPicsAddr2, Results results, float P[], float Q[], int channelIndicator) {
        indicator = 0;
        buildNameMaps();
        List<Double> bestFitModel = new ArrayList<>();
        keepAlgoFunc.put(results.getAlgoName(), new Help(results, P, Q));
        receiveCommand(results.getAlgoName(), P, Q);
        writeInFile(results.getAlgoName(), algoCalculationResult, populateWIithPicsAddr1, populateWIithPicsAddr2, channelIndicator);
        return algoCalculationResult;
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command, P, Q);
        algoCalculationResult = damerge.toString();
    }

    public static void writeInFile(AlgoName algoName, String algoCalculationResult, File populateWIithPicsAddr1, File populateWIithPicsAddr2, int channelIndicator) {
        PrintWriter writer = null;
        try {
            if (contor == 0) {
                writer = new PrintWriter(new FileWriter("results.txt", false));
            } else {
                writer = new PrintWriter(new FileWriter("results.txt", true));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contor = 1;
        writer.println("\n");
        writer.println(populateWIithPicsAddr1.toString().concat("__").concat(populateWIithPicsAddr2.toString()));
        writer.println(algoName);
        switch (channelIndicator) {
            case 0:
                writer.print("Red Channel ");
                break;
            case 1:
                writer.println("Green Channel ");
                break;
            case 2:
                writer.println("Blue Channel ");
                break;
        }

        writer.println(algoCalculationResult);

        writer.close();

    }
}
