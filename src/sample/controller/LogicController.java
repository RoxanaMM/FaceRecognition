package sample.controller;

import logicPackage.enums.AlgoName;
import logicPackage.model.Results;
import logicPackage.processing.Coloured;
import logicPackage.processing.GrayScale;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static logicPackage.processing.Calculate.calculateDistance;
import static logicPackage.processing.Transform.toMedialMat;
import static sample.Main.choosedLBP;

public class LogicController {
    public static GrayScale greyscale = new GrayScale();
    public static Results results = new Results();

    public static void transform(File sourcePic1) {
        boolean b =false;
        Mat mat = Imgcodecs.imread(sourcePic1.toString());
        Mat mat2 = toMedialMat(mat);
        File file = new File(sourcePic1.getParent());
        File[] a = file.listFiles();
        for(File f : a){
            if(f.toString().contains(sourcePic1.getAbsoluteFile().toString().concat("LBP_Modified.jpg"))){
                b = true;
                break;
            }
        }
        if(b == false){
            if(!sourcePic1.getAbsoluteFile().toString().contains("LBP_Modified.jpg")){
                Imgcodecs.imwrite(sourcePic1.toString().concat("LBP_Modified.jpg"), mat2);
            }
        }


    }

    public static List<String> getImgAndDoCalculatins(AlgoName algoNamesGiven, File populateWIithPicsAddr1, List<File> populateWIithPicsAddr2) throws IOException {
        List<String> res = new ArrayList<>();
        if (choosedLBP == true) {
            transform(populateWIithPicsAddr1);
            for (int i = 0; i < populateWIithPicsAddr2.size(); i++) {
                transform(populateWIithPicsAddr2.get(i));
            }
            for (int i = 0; i < populateWIithPicsAddr2.size(); i++) {
                if(populateWIithPicsAddr1.toString().contains("LBP_Modified.jpg"))
                    results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
                else
                    results.setPicA(populateWIithPicsAddr1.getAbsolutePath().concat("LBP_Modified.jpg"));
                if(populateWIithPicsAddr2.get(i).getAbsolutePath().contains("LBP_Modified.jpg"))
                    results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
                else
                    results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath().concat("LBP_Modified.jpg"));
                results.setAlgoName(algoNamesGiven);
                GrayScale grayScale = new GrayScale();
                float[] pixelArray3Channels1 = grayScale.convertImageToGrey(results.getPicA());
                float[] pixelArray3Channels2 = grayScale.convertImageToGrey(results.getPicB());
                res.add(calculateDistance(populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1, pixelArray3Channels2, 0));

            }
        } else {
            for (int i = 0; i < populateWIithPicsAddr2.size(); i++) {

                results.setPicA(populateWIithPicsAddr1.getAbsolutePath());
                results.setPicB(populateWIithPicsAddr2.get(i).getAbsolutePath());
                results.setAlgoName(algoNamesGiven);
                Coloured coloured = new Coloured();
                float[][] pixelArray3Channels1 = coloured.calculateHistogram(results.getPicA());
                float[][] pixelArray3Channels2 = coloured.calculateHistogram(results.getPicB());

                for (int j = 0; j < pixelArray3Channels1.length; j++) {
                    res.add(calculateDistance(populateWIithPicsAddr1, populateWIithPicsAddr2.get(i), results, pixelArray3Channels1[j], pixelArray3Channels2[j], j));

                }

            }
        }
        return res;
    }
}
