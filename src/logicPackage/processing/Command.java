package logicPackage.processing;

import logicPackage.enums.AlgoName;

public interface Command {
    Object execute(AlgoName algoName, float[] P, float[] Q);
}
