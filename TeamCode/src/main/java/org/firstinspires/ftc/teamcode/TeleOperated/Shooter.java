package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ChangeState;
import org.firstinspires.ftc.teamcode.Utils.Logics.DelayedAction;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;

public class Shooter {
    public static final double push = 0.68;
    public static final double free = 0.80;
    public static final double shootSpeed = 1700;
    private static final int shootTime = 100;
    private static final int returnTime = 125;
    private static final OneTap idlerSequential = new OneTap();
    private static final OneTap motor = new OneTap();
    private static final OneTap idlerContinuous = new OneTap();
    private static final ServoToPosition singleShooting = new ServoToPosition(Hardware.shooter_idler, "shooter_idler", free, push);
    public static final ChangeState shooter = new ChangeState();
    private static final ElapsedTime timer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
    private static final DelayedAction returnServo = new DelayedAction(shootTime, timer);
    private static final DelayedAction shootServoContinuous = new DelayedAction(returnTime, timer);
    private static final DelayedAction returnServoContinuous = new DelayedAction(shootTime, timer);

    public static ElapsedTime getTimer() {
        return timer;
    }

    public static void ShooterAfterStart() {
        Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", free);
    }

    private static void ContinuousShooting(boolean buttonForContinuousShooting) {
        if (idlerContinuous.onPressN(buttonForContinuousShooting, 3)) {
            shootServoContinuous.callAction();
        }
        if (shootServoContinuous.runAction()) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", push);
            returnServoContinuous.callAction();
        }
        if (returnServoContinuous.runAction()) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", free);
            idlerContinuous.incrementCount();
        }
    }

    public static void SequentialShooting(boolean buttonForSequentialShooting) {

        if (idlerSequential.onPress(buttonForSequentialShooting)) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", push);
            returnServo.callAction();
        }
        if (returnServo.runAction()) {
            Devices.setServoPosition(Hardware.shooter_idler, "shooter_idler", free);
        }
    }

    public static void AutonomousSequentialShooting(LinearOpMode opMode){
        Hardware.shooter_idler.setPosition(push);
        opMode.sleep(100);
        Hardware.shooter_idler.setPosition(free);

    }

    public static void ShootingMotors(boolean buttonForStartingTheShooter) {
        shooter.changeMotorStateSpeed(motor.onPress(buttonForStartingTheShooter), shootSpeed, Hardware.shooter_left, Hardware.shooter_right);
    }



    public static void ShooterControl(Gamepad gamepad) {
        if (!Intake.diskNormalizer.getIsMotorRunning()) {
            ShootingMotors(gamepad.left_bumper);
        }
        SequentialShooting(gamepad.a);
        //ContinuousShooting(gamepad.b);
        //singleShooting.modifyPosition(gamepad.x);
    }


}
