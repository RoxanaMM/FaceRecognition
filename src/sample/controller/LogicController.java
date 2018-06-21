package sample.controller;

import com.sun.corba.se.pept.transport.ReaderThread;
import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;
import logicPackage.model.BestFitModel;
import logicPackage.model.Results;
import logicPackage.processing.Coloured;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logicPackage.processing.Calculate.calculateDistance;


public class LogicController {


    public Map<Integer, List<BestFitModel>> getImgAndDoCalculatins(AlgoName algoNamesGiven, File populateWIithPicsAddr1, List<File> populateWIithPicsAddr2) throws IOException {
        Map<Integer, List<BestFitModel>> res = new HashMap<>();
        List<BestFitModel> helper = new ArrayList<>();
        for (int i = 0; i < populateWIithPicsAddr2.size(); i++) {
            Results results = new Results();
            results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
            results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
            results.setTypesOfSet(TypesOfSet.Coloured);
            results.setAlgoName(algoNamesGiven);

            Coloured coloured = new Coloured();
            float[][] pixelArray3Channels1 = coloured.calculateHistogram(results.getPicA());
            float[][] pixelArray3Channels2 = coloured.calculateHistogram(results.getPicB());

            for (int j = 0; j < pixelArray3Channels1.length; j++) {
                res = calculateDistance(helper, res, populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1[j], pixelArray3Channels2[j], j);
            }
        }
        return res;
    }
}
