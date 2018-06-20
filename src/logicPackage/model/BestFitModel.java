package logicPackage.model;

import logicPackage.enums.AlgoName;

import java.io.File;
import java.util.List;

public class BestFitModel {
    AlgoName algo;
    List<String> algoCalculationResult;
    String algoCalculationSingleResult;
    File populateWIithPicsAddr1;
    File populateWIithPicsAddr2;
    int channelIndicator;

    public BestFitModel(){
    }

    public BestFitModel(AlgoName algo, List<String> algoCalculationResult, File populateWIithPicsAddr1, File populateWIithPicsAddr2, int channelIndicator) {
        this.algo = algo;
        this.algoCalculationResult = algoCalculationResult;
        this.populateWIithPicsAddr1 = populateWIithPicsAddr1;
        this.populateWIithPicsAddr2 = populateWIithPicsAddr2;
        this.channelIndicator = channelIndicator;
    }

    public BestFitModel(AlgoName algo, String algoCalculationSingleResult, File populateWIithPicsAddr1, File populateWIithPicsAddr2, int channelIndicator) {
        this.algo = algo;
        this.algoCalculationSingleResult = algoCalculationSingleResult;
        this.populateWIithPicsAddr1 = populateWIithPicsAddr1;
        this.populateWIithPicsAddr2 = populateWIithPicsAddr2;
        this.channelIndicator = channelIndicator;
    }

    public AlgoName getAlgo() {
        return algo;
    }

    public void setAlgo(AlgoName algo) {
        this.algo = algo;
    }

    public List<String> getAlgoCalculationResult() {
        return algoCalculationResult;
    }

    public void setAlgoCalculationResult(List<String> algoCalculationResult) {
        this.algoCalculationResult = algoCalculationResult;
    }

    public File getPopulateWIithPicsAddr1() {
        return populateWIithPicsAddr1;
    }

    public void setPopulateWIithPicsAddr1(File populateWIithPicsAddr1) {
        this.populateWIithPicsAddr1 = populateWIithPicsAddr1;
    }

    public File getPopulateWIithPicsAddr2() {
        return populateWIithPicsAddr2;
    }

    public void setPopulateWIithPicsAddr2(File populateWIithPicsAddr2) {
        this.populateWIithPicsAddr2 = populateWIithPicsAddr2;
    }

    public String getAlgoCalculationSingleResult() {
        return algoCalculationSingleResult;
    }

    public void setAlgoCalculationSingleResult(String algoCalculationSingleResult) {
        this.algoCalculationSingleResult = algoCalculationSingleResult;
    }

    public int getChannelIndicator() {
        return channelIndicator;
    }

    public void setChannelIndicator(int channelIndicator) {
        this.channelIndicator = channelIndicator;
    }
}
