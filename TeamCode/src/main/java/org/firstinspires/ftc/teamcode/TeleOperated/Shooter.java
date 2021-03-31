package org.firstinspires.ftc.teamcode.TeleOperated;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.ChangeState;
import org.firstinspires.ftc.teamcode.Utils.DelayedAction;
import org.firstinspires.ftc.teamcode.Utils.OneTap;

public class Shooter {
    private static final double push = 0.74;
    private static final double free = 0.78;
    private static final double shootSpeed = 0.9;
    private static final int shootTime = 40;
    private static final int returnTime = 70;
    private static final OneTap idler = new OneTap();
    private static final OneTap motor = new OneTap();
    private static final ChangeState shooter = new ChangeState();
    private static final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static final DelayedAction returnServo = new DelayedAction(shootTime, timer);
    private static final DelayedAction shootServoContinuous = new DelayedAction(returnTime, timer);
    private static final DelayedAction returnServoContinuous = new DelayedAction(shootTime, timer);

    public static ElapsedTime getTimer() {
        return timer;
    }

    public static void ShooterAfterStart() {
        Hardware.shooter_idler.setPosition(free);

    }

    private static void ContinuousShooting(boolean buttonForContinuousShooting) {
        if (buttonForContinuousShooting) {
            shootServoContinuous.callAction();
        }
        if (shootServoContinuous.runAction()) {
            Hardware.shooter_idler.setPosition(push);
            returnServoContinuous.callAction();
        }
        if (returnServoContinuous.runAction())
            Hardware.shooter_idler.setPosition(free);
    }

    private static void SequentialShooting(boolean buttonForSequentialShooting) {

        if (idler.onPress(buttonForSequentialShooting)) {
            Hardware.shooter_idler.setPosition(push);
            returnServo.callAction();
        }
        if (returnServo.runAction()) {
            Hardware.shooter_idler.setPosition(free);
        }
    }

    private static void ReturnPosition(boolean buttonForReturnPosition) {
        if (buttonForReturnPosition) {
            Hardware.shooter_idler.setPosition(push);
        }
    }

    private static void ShootPosition(boolean buttonForShootPosition) {
        if (buttonForShootPosition) {
            Hardware.shooter_idler.setPosition(free);
        }
    }

    public static void ShootingMotors(boolean buttonForStartingTheShooter) {
        shooter.changeMotorState(motor.onPress(buttonForStartingTheShooter), shootSpeed, Hardware.shooter_left, Hardware.shooter_right);
    }

    public static void ShooterControl(Gamepad gamepad) {
        ShootingMotors(gamepad.left_bumper);
        SequentialShooting(gamepad.a);
        ContinuousShooting(gamepad.b);
        ReturnPosition(gamepad.x);
        ShootPosition(gamepad.y);
    }

}
