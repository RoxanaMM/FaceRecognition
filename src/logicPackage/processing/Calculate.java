package logicPackage.processing;

import logicPackage.algorithms.Algorithms;
import logicPackage.model.Model;
import logicPackage.model.Results;
import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Calculate extends Algorithms {
    public static HashMap<AlgoName,Command> keepAlgoFunc = new HashMap<AlgoName,Command>();
    public static List<String> algoCalculationResult = new ArrayList<String>();

    public static void calculateDistance(Results results,float P[], float Q[]) {

        if (results.getAlgoName().name().equals("all")) {
            for (AlgoName a : AlgoName.values()) {
                keepAlgoFunc.put(a, new Help(results, P, Q));
                receiveCommand(a, P, Q);
            }
        }else {
            keepAlgoFunc.put(results.getAlgoName(), new Help(results, P, Q));
            receiveCommand(results.getAlgoName(), P, Q);
        }
        writeInFile(algoCalculationResult);
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command,P,Q);
        algoCalculationResult.add(damerge.toString());
    }
    public static void writeInFile(List<String> algoCalculationResult){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("results.txt", "UTF-8");
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for(int i =0; i< algoCalculationResult.size();i++){
            writer.println(algoCalculationResult.get(i).toString());
        }
        writer.close();

    }
}
