package sample.controller;

import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;
import logicPackage.model.Results;
import logicPackage.processing.Coloured;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static logicPackage.processing.Calculate.calculateDistance;

public class LogicController {


    public void getImgAndDoCalculatins(AlgoName algoNamesGiven, File populateWIithPicsAddr1, List<File> populateWIithPicsAddr2) throws IOException {

        for(int i =0; i<populateWIithPicsAddr2.size();i++) {
            Results results = new Results();
            results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
            results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
            results.setTypesOfSet(TypesOfSet.Coloured);
            results.setAlgoName(algoNamesGiven);

            Coloured coloured = new Coloured();
            float[][] pixelArray3Channels1 = coloured.calculateHistogram(results.getPicA());
            float[][] pixelArray3Channels2 = coloured.calculateHistogram(results.getPicB());

            for (int j = 0; j < pixelArray3Channels1.length;j++) {
                calculateDistance(populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1[j], pixelArray3Channels2[j],j);
            }
        }

    }
}
