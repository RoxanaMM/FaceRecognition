package sample.controller;

import com.sun.corba.se.pept.transport.ReaderThread;
import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;
import logicPackage.model.BestFitModel;
import logicPackage.model.Results;
import logicPackage.processing.Coloured;
import logicPackage.processing.GrayScale;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logicPackage.processing.Calculate.calculateDistance;
import static sample.Main.booleanGreyScale;


public class LogicController {


    public static List<String> getImgAndDoCalculatins(AlgoName algoNamesGiven, File populateWIithPicsAddr1, List<File> populateWIithPicsAddr2) throws IOException {
        List<String> res = new ArrayList<>();


        List<BestFitModel> helper = new ArrayList<>();
        for (int i = 0; i < populateWIithPicsAddr2.size(); i++) {
            Results results = new Results();
            results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
            results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
            results.setAlgoName(algoNamesGiven);
            if(booleanGreyScale ==false) {
                Coloured coloured = new Coloured();
                float[][] pixelArray3Channels1 = coloured.calculateHistogram(results.getPicA());
                float[][] pixelArray3Channels2 = coloured.calculateHistogram(results.getPicB());

                for (int j = 0; j < pixelArray3Channels1.length; j++) {
                    res.add(calculateDistance(populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1[j], pixelArray3Channels2[j], j));

                }
            }else{
                GrayScale grayScale = new GrayScale();
                float[] pixelArray3Channels1Gray = grayScale.convertImageToGrey(results.getPicA());
                float[] pixelArray3Channels2Gray = grayScale.convertImageToGrey(results.getPicB());
                for (int j = 0; j < pixelArray3Channels1Gray.length; j++) {
                    res.add(calculateDistance(populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1Gray, pixelArray3Channels2Gray, j));

                }
            }
        }
        return res;
    }
}
