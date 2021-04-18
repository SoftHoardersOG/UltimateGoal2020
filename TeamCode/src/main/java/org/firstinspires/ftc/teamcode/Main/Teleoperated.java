package org.firstinspires.ftc.teamcode.Main;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Intake;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class Teleoperated extends LinearOpMode {

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap, telemetry);
        Hardware.grabber.setTargetPosition(0);
        Wobble.initialization();
        waitForStart();
        Shooter.ShooterAfterStart();

        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addLine(Hardware.grabber.getMode()+"");
            Movement.driving(gamepad1);
            Shooter.ShooterControl(gamepad1);
            Intake.IntakeOneSpeed(gamepad1);
            Intake.OutTakeOneSpeed(gamepad2);
            ChangeShootingAngle.AngleControl(gamepad2);
            Wobble.wobbleControl(gamepad1);
            telemetry.addLine("grabber current power "+ Hardware.grabber.getPowerFloat());
            Debugs.grabberPosition(telemetry, true);
            //Debugs.motorVelocity(telemetry,true);
            //Instruction.Commands(telemetry,false);
//            Debugs.angleDebug(telemetry,true);
            //Debugs.encoderDebug(telemetry,false);
            //Debugs.changeShooterAngleDebug(telemetry,true);
            //Debugs.movementSpeedDebug(telemetry,true );
        }
    }
}
