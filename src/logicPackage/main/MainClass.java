package logicPackage.main;

import logicPackage.model.Model;
import logicPackage.model.Results;
import logicPackage.enums.AlgoName;
import logicPackage.enums.TypesOfSet;
import logicPackage.processing.Calculate;
import logicPackage.processing.Coloured;
import org.opencv.core.Core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends Calculate {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) throws Exception {

        String filePath = "C:\\forMaster\\temaDisertatie\\test\\toBeGreyScalled-nesimilare";
        //        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-nesimilare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\gri-similare";
        //  String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-similare";
//        String filePath = "C:\\forMaster\\temaDisertatie\\test\\faces-nesimilare";

        File folder = new File(filePath);
        File[] listOfFiles = folder.listFiles();
        List<String>listOfBPics = new ArrayList<String>();
        listOfBPics.add("C:\\forMaster\\temaDisertatie\\test\\color-similare\\2.jpg");
        listOfBPics.add("C:\\forMaster\\temaDisertatie\\test\\color-similare\\3.jpg");
        listOfBPics.add("C:\\forMaster\\temaDisertatie\\test\\color-similare\\4.jpg");
        //teoretic trebuie de la tastatura, practic n-am interfata inca


        String algoNamesGiven = "all";
        
      // for(int i =0; i<listOfBPics.size();i++) {
           Results results = new Results();
           results.setPicA("C:\\forMaster\\temaDisertatie\\test\\color-similare\\1.jpg");
         //  results.setPicB(listOfBPics.get(i));
           results.setTypesOfSet(TypesOfSet.Coloured);
           results.setAlgoName(AlgoName.valueOf(algoNamesGiven));

           //  results.setCalculatedDistances();
           Coloured coloured = new Coloured();
           //aici e trratat doar cazul in care se face o comparatie 1 la 1

           float[][] pixelArray3Channels1 = coloured.calculateHistogram(results.getPicA());
           float[][] pixelArray3Channels2 = coloured.calculateHistogram(results.getPicB());

        //   for (int j = 0; j < pixelArray3Channels1.length;j++) {
              //    calculateDistance(results, pixelArray3Channels1, pixelArray3Channels2);
          // }
      // }



    }
}