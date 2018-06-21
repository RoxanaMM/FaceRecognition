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
    public static List<String> algoCalculationResult = new ArrayList<String>();
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

    public static Map<Integer, List<BestFitModel>> calculateDistance(List<BestFitModel> helper, Map<Integer, List<BestFitModel>>bestFitModels,File populateWIithPicsAddr1, File populateWIithPicsAddr2, Results results, float P[], float Q[], int channelIndicator) {
        indicator = 0;
        buildNameMaps();
      //  List<BestFitModel>helper = new ArrayList<>();

        if (results.getAlgoName().name().equals("all")) {
            for (AlgoName a : AlgoName.values()) {
                keepAlgoFunc.put(a, new Help(results, P, Q));
                receiveCommand(a, P, Q);
                indicator = 1;
            }
            for (int i = 0; i < algoCalculationResult.size(); i++) {
                BestFitModel model = new BestFitModel(algoNameMap.get(i), algoCalculationResult.get(i).toString(), populateWIithPicsAddr1, populateWIithPicsAddr2, channelIndicator);
                helper.add(model);

            }
            bestFitModels.put(channelIndicator,helper);
        } else {
            keepAlgoFunc.put(results.getAlgoName(), new Help(results, P, Q));
            receiveCommand(results.getAlgoName(), P, Q);

            BestFitModel model = new BestFitModel(results.getAlgoName(), algoCalculationResult, populateWIithPicsAddr1, populateWIithPicsAddr2, channelIndicator);
            helper.add(model);
            bestFitModels.put(channelIndicator,helper);
        }


        writeInFile(results.getAlgoName(), algoCalculationResult, populateWIithPicsAddr1, populateWIithPicsAddr2, channelIndicator);
        return  bestFitModels;
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command, P, Q);
        if (indicator == 0)
            algoCalculationResult.clear();
        algoCalculationResult.add(damerge.toString());
    }

    public static void writeInFile(AlgoName algoName, List<String> algoCalculationResult, File populateWIithPicsAddr1, File populateWIithPicsAddr2, int channelIndicator) {
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
        if (algoCalculationResult.size() == 1) {
            writer.println(algoCalculationResult.get(0).toString());
        } else {
            for (int k = 0; k < algoCalculationResult.size(); k++) {
                writer.println(algoNameMap.get(k));
                writer.println(algoCalculationResult.get(k).toString());
            }
        }
        writer.close();

    }
}
