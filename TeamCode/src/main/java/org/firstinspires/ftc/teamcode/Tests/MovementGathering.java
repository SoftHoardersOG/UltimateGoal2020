package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Movement;

@TeleOp(name = "MovementGathering")
public class MovementGathering extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
         Movement.driving(gamepad1);
        Hardware.init(hardwareMap, telemetry);
        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            if (gamepad1.left_trigger == 0) {
                Hardware.gathering.setPower(gamepad1.right_trigger);
            }
            if (gamepad1.right_trigger == 0) {
                Hardware.gathering.setPower(-gamepad1.left_trigger);
            }
        }
    }
}
