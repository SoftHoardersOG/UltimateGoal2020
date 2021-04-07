package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.ChangeState;
import org.firstinspires.ftc.teamcode.Utils.DelayedAction;
import org.firstinspires.ftc.teamcode.Utils.OneTap;
import org.firstinspires.ftc.teamcode.Utils.SingleShooting;

public class Shooter {
    public static final double push = 0.74;
    public static final double free = 0.80;
    private static final double shootSpeed = 0.9;
    private static final int shootTime = 50;
    private static final int returnTime = 125;
    private static final OneTap idlerSequential = new OneTap();
    private static final OneTap motor = new OneTap();
    private static final OneTap idlerContinuous = new OneTap();
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
        if (idlerContinuous.onPressN(buttonForContinuousShooting,3)) {
            shootServoContinuous.callAction();
        }
        if (shootServoContinuous.runAction()) {
            Hardware.shooter_idler.setPosition(push);
            returnServoContinuous.callAction();
        }
        if (returnServoContinuous.runAction()){
            Hardware.shooter_idler.setPosition(free);
            OneTap.incrementCount();
        }
    }

    private static void SequentialShooting(boolean buttonForSequentialShooting) {

        if (idlerSequential.onPress(buttonForSequentialShooting)) {
            Hardware.shooter_idler.setPosition(push);
            returnServo.callAction();
        }
        if (returnServo.runAction()) {
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
        SingleShooting.singleShooting(gamepad.x);
    }

}
