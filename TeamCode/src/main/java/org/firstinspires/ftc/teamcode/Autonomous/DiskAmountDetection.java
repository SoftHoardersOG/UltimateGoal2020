package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;


public class DiskAmountDetection {
    private static OpenCvCamera camera;
    public static UltimateGoalPipeline pipeline;

    public static void startDetection(Telemetry telemetry, boolean update) {
        camera = OpenCvCameraFactory.getInstance().createWebcam(Hardware.webcam, Hardware.cameraMonitorViewId);
        pipeline = new UltimateGoalPipeline(telemetry, update);

        camera.setPipeline(pipeline);
        //camera.startStreaming(176, 144, OpenCvCameraRotation.SIDEWAYS_LEFT);
        //camera.setViewportRenderingPolicy(OpenCvCamera.ViewportRenderingPolicy.OPTIMIZE_VIEW);

    }

    public static class UltimateGoalPipeline extends OpenCvPipeline {

        Telemetry telemetry;
        boolean update;

        public UltimateGoalPipeline(Telemetry telemetry, boolean update) {
            this.telemetry = telemetry;
            this.update = update;
        }

        final Scalar BLUE = new Scalar(0, 0, 255);

        @Override
        public Mat processFrame(Mat input) {
            Mat RedMat = new Mat();
            Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2YCrCb);
            //Imgproc.cvtColor(input,input,Imgproc.COLOR_RGB2GRAY );
            //Imgproc.Canny(input, input, 60, 60*3);
            Core.extractChannel(input, RedMat, 1);
            int n = RedMat.width(), m = RedMat.height();
            telemetry.addData("mat size is: ",n+" "+m);
            Point firstPoint = new Point(n / 2 - 20, m / 2 -10), secondPoint = new Point(n / 2 + 20, m / 2 + 30);
            Imgproc.rectangle(
                    RedMat,
                    firstPoint,
                    secondPoint,
                    BLUE,
                    1
            );
            double average = 0f;
            for (int i = (int) firstPoint.x; i < (int) secondPoint.x; i++) {
                for (int j = (int) firstPoint.y; j < (int) secondPoint.y; j++) {
                    average += RedMat.get(j, i)[0];
                }
            }
            average /= (n * m);

            double redAmount = 0f;

            for (int i = (int) firstPoint.x; i < (int) secondPoint.x; i++) {
                for (int j = (int) firstPoint.y; j < (int) secondPoint.y; j++) {
//                    RedMat.put(j,i,(RedMat.get(j,i)[0]-average)*15);
                    redAmount += 15 * (RedMat.get(j, i)[0] - average);
                }
            }

            telemetry.addData("The red amount is:", redAmount);

            if (update) {
                telemetry.update();
            }

            return RedMat;
        }
    }
}