package logicPackage.processing;

import com.googlecode.javacv.cpp.opencv_objdetect;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public  class PersonRecognizer {



//
//    public void run()
//    {
//        // TODO Auto-generated method stub
//        System.out.println("\nRunning DetectFaceDemo");
//
//        // Create a face detector from the cascade file in the resources
//        // directory.
//        long startTime = System.nanoTime();
//        //    CascadeClassifier faceDetector = new CascadeClassifier("D:\\opencv\\sources\\data\\lbpcascades\\lbpcascade_frontalface.xml");
//        opencv_objdetect.CascadeClassifier faceDetector = new opencv_objdetect.CascadeClassifier("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
////       CascadeClassifier faceDetector = new CascadeClassifier("D:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_default.xml");
//
//
////      Mat image = Highgui.imread("d:\\face image\\aaa05.jpg");
//        Mat image = Imgcodecs.imread("D:\\photo test gallery_HD_2\\5.jpg");
//        float size = Math.round(image.height() * 0.1f);
//        //System.out.println("####= "+ size);
//        Rect rectCrop = null;
//        // Detect faces in the image.
//        // MatOfRect is a special container class for Rect.
//        MatOfRect faceDetections = new MatOfRect();
//       // double[] re = new double[];
//      //  faceDetector.detectMultiScale(image;
//       // faceDetector.detectMultiScale(image, faceDetections);
////      faceDetector.detectMultiScale(image, faceDetections, 1.1, 3, 0, new Size(90, 90),new Size(400, 400));
//
//
//        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
//
//        // Draw a bounding box around each face.
//        for (Rect rect : faceDetections.toArray())
//        {
//            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
//
//            rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
//
//            Imgproc.putText(image, "Edited by me", new Point(rect.x,rect.y),
//                    Core.FONT_HERSHEY_PLAIN, 1.0 ,new  Scalar(0,255,255));
//        }
//
//
//        // crop individual image
//        int counter=0;
//        for (Rect rect : faceDetections.toArray())
//        {
//            Mat faceImage = image.submat(rect);
//            Mat resizeimage = new Mat();
//            Size sz = new Size(64, 128);
//            Imgproc.resize(faceImage, resizeimage, sz);
//            System.out.println(counter);
//            Imgcodecs.imwrite("D:\\image\\appface\\"+ counter+ ".jpg", resizeimage);
//            counter++;
//            System.out.println(";)");
//
//        }
//
//        //long stopTime = System.nanoTime();
//        //float time_diff = stopTime - startTime;
//        //System.out.println("timefacedetect-s:"+ time_diff/1000000000);
//
//        // Save the visualized detection.
//        String filename = "d:\\faceDetection.jpg";
//        System.out.println(String.format("Writing %s", filename));
//        Imgcodecs.imwrite(filename, image);
//
//    }

}