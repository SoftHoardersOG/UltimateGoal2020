package org.firstinspires.ftc.teamcode.Main;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Odometry.OdometryGlobalCoordinatePosition;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Intake;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.TeleOperated.Wall;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;

@TeleOp(name = "TeleOp", group = "TeleOp's")
public class Teleoperated extends LinearOpMode {

    OdometryGlobalCoordinatePosition globalPositionUpdate;
    private static final double COUNTS_PER_INCH = 1742.97111909;

    @Override
    public void runOpMode() {
        Hardware.init(hardwareMap, telemetry);
        Hardware.grabber.setTargetPosition(0);
        //Wobble.initialization();
        //Wall.initialization();
        waitForStart();
        globalPositionUpdate = new OdometryGlobalCoordinatePosition(Hardware.left_encoder, Hardware.right_encoder, Hardware.center_encoder, COUNTS_PER_INCH, 75);
        Thread positionThread = new Thread(globalPositionUpdate);
        positionThread.start();
        globalPositionUpdate.reverseRightEncoder();
        globalPositionUpdate.reverseLeftEncoder();
        globalPositionUpdate.reverseNormalEncoder();
        Shooter.ShooterAfterStart();

        while (!isStopRequested() && opModeIsActive()) {
            Movement.driving(gamepad1);
            Shooter.ShooterControl(gamepad1);
            Intake.IntakeOneSpeed(gamepad1);
            Intake.OutTakeOneSpeed(gamepad2);
            ChangeShootingAngle.AngleControl(gamepad2);
//
            // Debugs.encoderDebug(telemetry, true);
            telemetry.addData("X Position", globalPositionUpdate.returnXCoordinate() / COUNTS_PER_INCH);
            telemetry.addData("Y Position", globalPositionUpdate.returnYCoordinate() / COUNTS_PER_INCH);
            telemetry.addData("Orientation (Degrees)", globalPositionUpdate.returnOrientation());

            telemetry.addData("Vertical left encoder position", Hardware.left_encoder.getCurrentPosition());
            telemetry.addData("Vertical right encoder position", Hardware.right_encoder.getCurrentPosition());
            telemetry.addData("horizontal encoder position",  Hardware.center_encoder.getCurrentPosition());

            telemetry.addData("Thread Active", positionThread.isAlive());

            telemetry.update();

        }
       globalPositionUpdate.stop();
    }
}
