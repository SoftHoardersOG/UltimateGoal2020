package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.HardwarePack.HardwareUtil.*;

public class Hardware {
    private Hardware() {
    }

    public static DcMotor front_right, front_left, back_right, back_left;
    public static DcMotor right_encoder, left_encoder, center_encoder;
    public static WebcamName webcam;
    public static BNO055IMU imu;


    public static void init(HardwareMap hm, Telemetry telemetry) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();

        ResetEncoders(right_encoder,left_encoder,center_encoder);

        directionChanging(back_left, front_left);
        telemetry.addLine("Direction changing for DCMotors Done!");
        telemetry.update();

        powerBehaviorChanging(front_left, front_right, back_right, back_left);
        telemetry.addLine("Power behavior Done!");
        telemetry.update();

        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

    public static void init(HardwareMap hm, Telemetry telemetry, OpenCvPipeline process) {
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();

        OpenCVSetup(hm, process, telemetry, webcam);
        telemetry.addLine("Open CV setup Done!");
        telemetry.update();

        ResetEncoders(right_encoder,left_encoder,center_encoder);

        directionChanging(back_left);
        telemetry.addLine("Direction changing for DCMotors Done!");
        telemetry.update();

        powerBehaviorChanging(front_left, front_right, back_right, back_left);
        telemetry.addLine("Power behavior Done!");
        telemetry.update();

        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

    private static void hardwareMapping(HardwareMap hardwareMap) {

        front_right = getDC("front_right", hardwareMap);
        front_left = getDC("front_left", hardwareMap);
        back_left = getDC("back_left", hardwareMap);
        back_right = getDC("back_right", hardwareMap);
        webcam = getWebcam("webcam", hardwareMap);
        imu=getIMU("imu",hardwareMap);
        HardwareUtil.InitializeIMU(imu);

        right_encoder = back_right;
        left_encoder = back_left;
        center_encoder = front_right;
    }
}

