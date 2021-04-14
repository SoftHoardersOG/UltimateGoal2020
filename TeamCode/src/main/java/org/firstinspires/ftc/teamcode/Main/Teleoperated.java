package org.firstinspires.ftc.teamcode.Main;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Control.Instruction;
import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Intake;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "TeleOp's")
public class Teleoperated extends LinearOpMode {

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
            //Instruction.Commands(telemetry,false);
            Debugs.encoderDebug(telemetry,true);

        }
    }
}
