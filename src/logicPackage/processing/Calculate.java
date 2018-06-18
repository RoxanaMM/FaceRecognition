package logicPackage.processing;

import logicPackage.algorithms.Algorithms;
import logicPackage.model.Model;
import logicPackage.model.Results;
import logicPackage.enums.AlgoName;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calculate extends Algorithms {
    public static HashMap<AlgoName, Command> keepAlgoFunc = new HashMap<AlgoName, Command>();
    public static List<String> algoCalculationResult = new ArrayList<String>();
    public static Integer contor = 0;

    public static void calculateDistance(File populateWIithPicsAddr1, File populateWIithPicsAddr2, Results results, float P[], float Q[], int j) {


        if (results.getAlgoName().name().equals("all")) {
            for (AlgoName a : AlgoName.values()) {
                keepAlgoFunc.put(a, new Help(results, P, Q));
                receiveCommand(a, P, Q);
            }
        } else {
            keepAlgoFunc.put(results.getAlgoName(), new Help(results, P, Q));
            receiveCommand(results.getAlgoName(), P, Q);
        }
        writeInFile(results.getAlgoName(), algoCalculationResult, populateWIithPicsAddr1, populateWIithPicsAddr2, j);
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command, P, Q);
        algoCalculationResult.clear();
        algoCalculationResult.add(damerge.toString());
    }

    public static void writeInFile(AlgoName algoName, List<String> algoCalculationResult, File populateWIithPicsAddr1, File populateWIithPicsAddr2, int j) {


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
        switch (j) {
            case 0:
                writer.print("Red Channel");
                break;
            case 1:
                writer.println("Green Channel");
                break;
            case 2:
                writer.println("Blue Channel");
                break;
        }
        writer.println(algoCalculationResult.get(0).toString());

        writer.close();

    }
}
