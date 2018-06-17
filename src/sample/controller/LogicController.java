package sample.controller;

import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;
import logicPackage.model.Results;
import logicPackage.processing.Coloured;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static logicPackage.processing.Calculate.calculateDistance;

public class LogicController {

    public void getImgAndDoCalculatins(File populateWIithPicsAddr1, List<File> populateWIithPicsAddr2) throws IOException {
        String algoNamesGiven = "all";

        for(int i =0; i<populateWIithPicsAddr2.size();i++) {
            Results results = new Results();
            results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
            results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
            results.setTypesOfSet(TypesOfSet.Coloured);
            results.setAlgoName(AlgoName.valueOf(algoNamesGiven));

            Coloured coloured = new Coloured();

            float[][] pixelArray3Channels1 = coloured.calculateAndDrawHistogram(results.getPicA());
            float[][] pixelArray3Channels2 = coloured.calculateAndDrawHistogram(results.getPicB());

            for (int j = 0; j < pixelArray3Channels1.length;j++) {
                calculateDistance(results, pixelArray3Channels1[i], pixelArray3Channels2[i]);
            }
        }

    }
}
