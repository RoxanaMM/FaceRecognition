package logicPackage.processing;

import logicPackage.algorithms.Algorithms;
import logicPackage.model.Model;
import logicPackage.model.Results;
import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;

import java.util.HashMap;

public class Calculate extends Algorithms {
    public static HashMap<AlgoName,Command> keepAlgoFunc = new HashMap<AlgoName,Command>();

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
    }

    public static void receiveCommand(AlgoName command, float P[], float Q[]) {
        Object damerge = keepAlgoFunc.get(command).execute(command,P,Q);
        System.out.println(damerge.toString());
    }
}
