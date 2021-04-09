package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Control.Instruction;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class mainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap, telemetry);
        waitForStart();
        Shooter.ShooterAfterStart();
        while (!isStopRequested() && opModeIsActive()) {
            Movement.driving(gamepad1);
            Shooter.ShooterControl(gamepad1);
            Intake.Intake(gamepad2);
            ChangeShootingAngle.AngleControl(gamepad2);
            Instruction.Commands(telemetry,true);
        }
    }
}
