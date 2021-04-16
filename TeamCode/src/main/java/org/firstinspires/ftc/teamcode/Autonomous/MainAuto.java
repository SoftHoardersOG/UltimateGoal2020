package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

@TeleOp(name = "Autonomous")
public class MainAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Hardware.init(hardwareMap, telemetry);
        GyroPID gyroPID = new GyroPID();
        waitForStart();
        gyroPID.StartPID(90);
        while (opModeIsActive() && !isStopRequested()) {
            gyroPID.PID(telemetry);
        }
    }
}
