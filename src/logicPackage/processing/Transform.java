package logicPackage.processing;

import org.opencv.contrib.FaceRecognizer;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Transform {
    private FaceRecognizer faceRecognizer;
    public static Size TRAIN_FACE_IMAGE_SIZE = new Size(160, 160);
    GrayScale greyscale = new GrayScale();

//ideea e asa
    //ii dau pozele de train
    //le fac greyscale, apoi le aduc la standard size iar dupaia le tai in jurul fetei si obtin noi imagini
    //pe aceste noi imagini eu voi face comparatia si voi sti cine e de fapt

    public static Mat toMedialMat( Mat mat) {
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_BGR2GRAY);
        Imgproc.resize(mat, mat, TRAIN_FACE_IMAGE_SIZE);
        Mat mat2 = new Mat(mat.size(), mat.type());

        int rows = mat.rows();
        int cols = mat.cols();

        double sumOfPixelByColInRow = 0;

        for (int x = 0; x < rows; x++) {
            double sumOfPixelByRow = 0;

            for (int y = 0; y < cols; y++) {
                sumOfPixelByRow = sumOfPixelByRow + mat.get(x, y)[0];
            }

            sumOfPixelByRow = sumOfPixelByRow / cols;
            sumOfPixelByColInRow = sumOfPixelByColInRow + sumOfPixelByRow;
        }

        double mediumPixel = sumOfPixelByColInRow / rows;

        int perfectMediumPixel = 255 / 2;

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                double pixelValue = (int) mat.get(x, y)[0];
                double mediumValue = (int) (pixelValue * perfectMediumPixel / mediumPixel);

                if (mediumValue > 255) {
                    mediumValue = 255;
                }

                mat2.put(x, y, mediumValue);
            }
        }
        return mat2;
    }

    public void iaPrinteazoOleaca(File sourcePic1){
        Mat mat = Imgcodecs.imread(sourcePic1.toString());
        Mat mat2= toMedialMat(mat);
        Imgcodecs.imwrite( sourcePic1.toString().concat("LBP_Modified.jpg"), mat2);
        float []arrayOfGreyscalePixels = greyscale.convertToGreyHelper(mat2);
       // Mat< float a, float [] frequencyOfArrayOfGreyscalePixels>
        for(int i =0;i<arrayOfGreyscalePixels.length;i++){
        //    frequencyOfArrayOfGreyscalePixels[arrayOfGreyscalePixels[i]++];
        }

    }
    public RecognizedUser matchFace(Mat face) {
        Imgproc.cvtColor(face, face, Imgproc.COLOR_BGR2GRAY);
        Imgproc.resize(face, face, TRAIN_FACE_IMAGE_SIZE);

        int[] label = {0};
        double[] confidence = {0};

        faceRecognizer.predict(face, label, confidence);


        if (confidence[0] < 100) {
            return new RecognizedUser(label[0], confidence[0]);
        }

        return new RecognizedUser(-1, 0);
    }
}
