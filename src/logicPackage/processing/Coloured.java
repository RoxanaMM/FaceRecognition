package logicPackage.processing;

import logicPackage.histogram.DrawColouredHistogram;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Coloured extends DrawColouredHistogram {

    public static Integer contor = 0;
    public static int histSize = 256;

    public static float[][] colouredHelper(Mat A) {
        float[][] floatArray = new float[3][256];
        int hist_w = 512;

        MatOfFloat ranges = new MatOfFloat(0, 256);
        List<Mat> bgrPlanes = new ArrayList<>();
        Core.split(A, bgrPlanes);

        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();

        Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < bHist.rows(); i++) {
            for (int j = 0; j < bHist.cols(); j++) {
                for (int k = 0; k < bHist.get(i, j).length; k++) {
                    floatArray[0][i] = (float) Math.round((bHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }

        Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < gHist.rows(); i++) {
            for (int j = 0; j < gHist.cols(); j++) {
                for (int k = 0; k < gHist.get(i, j).length; k++) {
                    floatArray[1][i] = (float) Math.round((gHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }
        Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), ranges, false);
        for (int i = 0; i < rHist.rows(); i++) {
            for (int j = 0; j < rHist.cols(); j++) {
                for (int k = 0; k < rHist.get(i, j).length; k++) {
                    floatArray[2][i] = (float) Math.round((rHist.get(i, j)[k] * 100.0) / 100.0);
                }
            }
        }

        return floatArray;
    }


    public static float[][] calculateHistogram(String sourcePic1) throws IOException {
        float[][] floatArray = new float[3][256];
        if (sourcePic1.contains("jpg")) {
            Mat A = Imgcodecs.imread(sourcePic1);
            floatArray = colouredHelper(A);
        }
        return floatArray;
    }

    public String getHistogram(String sourcePic1) {
        Mat histImage;
        String histogramName= sourcePic1.concat("HISTOGRAM.jpg");
        Mat bHist = new Mat(), gHist = new Mat(), rHist = new Mat();
        if (sourcePic1.contains(".jpg")) {
            Mat A = Imgcodecs.imread(sourcePic1);
            Mat hist = new Mat(256, 1, CvType.CV_8UC1);
            MatOfFloat ranges = new MatOfFloat(0, 256);
            MatOfInt channels = new MatOfInt(3);

            List<Mat> bgrPlanes = new ArrayList<>();
            Core.split(A, bgrPlanes);
            Imgproc.calcHist(bgrPlanes, new MatOfInt(0), new Mat(), bHist, new MatOfInt(histSize), ranges, false);
            Imgproc.calcHist(bgrPlanes, new MatOfInt(1), new Mat(), gHist, new MatOfInt(histSize), ranges, false);
            Imgproc.calcHist(bgrPlanes, new MatOfInt(2), new Mat(), rHist, new MatOfInt(histSize), ranges, false);

            histImage = drawColouredHistogram(bHist, gHist, rHist);
            Imgcodecs.imwrite(histogramName, histImage);

        }
        return histogramName;
    }
}

