package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvPipeline;

import static org.firstinspires.ftc.teamcode.Utils.HardwareUtil.OpenCVSetup;
import static org.firstinspires.ftc.teamcode.Utils.HardwareUtil.ResetEncoders;
import static org.firstinspires.ftc.teamcode.Utils.HardwareUtil.directionChanging;
import static org.firstinspires.ftc.teamcode.Utils.HardwareUtil.powerBehaviorChanging;

public class Hardware extends HardwareMapping{

    public static void init(HardwareMap hm, Telemetry telemetry) {

        telemetry.addLine("Initializing...");
        telemetry.update();

        hardwareMapping(hm);
        telemetry.addLine("Hardware Mapping Done!");
        telemetry.update();

        ResetEncoders(right_encoder, left_encoder, center_encoder);

        directionChanging(back_left, front_left,shooter_right);
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

        ResetEncoders(right_encoder, left_encoder, center_encoder);

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
