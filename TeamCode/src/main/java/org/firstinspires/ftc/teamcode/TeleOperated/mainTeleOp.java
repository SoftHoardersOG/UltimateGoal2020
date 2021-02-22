package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class mainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode(){
        Hardware.init(hardwareMap, telemetry);
        waitForStart();
        while (!isStopRequested() && opModeIsActive()) {
            Movement.driving(gamepad1);
            Debugs.encoderDebug(telemetry,true);
        }
    }
}
