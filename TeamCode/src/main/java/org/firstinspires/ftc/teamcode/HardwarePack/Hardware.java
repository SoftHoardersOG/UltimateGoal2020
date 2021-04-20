package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.OpenCVSetup;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.ResetEncoders;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.RunToPosition;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.directionChanging;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.powerBehaviorChanging;

public class Hardware extends HardwareMapping{

    public static void init(HardwareMap hm, Telemetry telemetry) {
        HardwareUtil.setTelemetry(telemetry);
        telemetry.addLine("Initializing...");


        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");


        ResetEncoders(grabber,front_encoder, center_encoder, front_left, front_right, back_left, back_right, shooter_right, shooter_left, intake);
        RunToPosition(grabber);

        directionChanging(back_left, front_left, shooter_right,intake);
        telemetry.addLine("Direction changing for DCMotors Done!");


        powerBehaviorChanging(front_left, front_right, back_right, back_left);
        telemetry.addLine("Power behavior Done!");


        telemetry.addLine("Initialization completed.");
        telemetry.update();

    }

    public static void init(HardwareMap hm, Telemetry telemetry, OpenCvPipeline process) throws InterruptedException {
        HardwareUtil.setTelemetry(telemetry);
        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();


        OpenCVSetup(hm, process, telemetry, webcam);
        telemetry.addLine("Open CV setup Done!");
        telemetry.update();

        ResetEncoders(front_encoder, center_encoder);
        RunToPosition(grabber);

        directionChanging(back_left, front_left);
        telemetry.addLine("Direction changing for DCMotors Done!");
        telemetry.update();

        powerBehaviorChanging(front_left, front_right, back_right, back_left);
        telemetry.addLine("Power behavior Done!");
        telemetry.update();

        telemetry.addLine("Initialization completed.");
        telemetry.update();
    }

}
