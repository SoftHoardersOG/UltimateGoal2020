package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

@TeleOp(name = "ImageDetectionTest")
public class ImageDetectionTest extends LinearOpMode {

    OpenCvInternalCamera phoneCamera;

    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCamera = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);

        UltimateGoalPipeline pipeline = new UltimateGoalPipeline();
        phoneCamera.setPipeline(pipeline);
        phoneCamera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);
        phoneCamera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                phoneCamera.startStreaming(176, 144, OpenCvCameraRotation.SIDEWAYS_LEFT);
            }
        });
        waitForStart();
        while (!isStopRequested() && isStarted()) {

        }
    }

    public class UltimateGoalPipeline extends OpenCvPipeline {

        final Scalar BLUE = new Scalar(0, 0, 255);

        @Override
        public Mat processFrame(Mat input) {
            Mat RedMat= new Mat();
            Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);
            //Imgproc.cvtColor(input,input,Imgproc.COLOR_RGB2GRAY );
            //Imgproc.Canny(input, input, 60, 60*3);
            Core.extractChannel(input,RedMat,1);
            int n=RedMat.width(),m=RedMat.height();
            double medie= 0f;
            for(int i=0; i<n; i++){
                for(int j=0 ; j<m; j++){
                    double g=RedMat.get(j,i)[0];g/=n*m;
                    medie+=g;
                }
            }
            double r=0;
            for(int i=0; i<n; i++){
                for(int j=0 ; j<m; j++){
                    double p=(RedMat.get(j,i)[0]-medie*1.05)*15;
                    RedMat.put(j,i,p);
                    if(p>=0) {
                        r+=p;
                    }
                }
            }
            int diskAmount=0;
            if(r>=190000){
                diskAmount=4;
            }else if(r>=85000){
                diskAmount=1;
            }
            telemetry.addData("The disk amount is:" , diskAmount);
            //  telemetry.addData("Mat amount: ",r);
            telemetry.update();
//            int midX = input.width() / 2, midY = input.height() / 2;
//            final float offset=30f;
//            Imgproc.rectangle(
//                    input,
//                    new Point(midX - offset, midY + offset),
//                    new Point(midX + offset, midY - offset),
//                    BLUE,
//                    1
//            );
            return RedMat;
        }
    }
}