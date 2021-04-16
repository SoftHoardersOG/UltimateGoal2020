package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ChangeState;
import org.firstinspires.ftc.teamcode.Utils.Logics.DelayedAction;
import org.firstinspires.ftc.teamcode.Utils.Logics.TwoStateMotorWithDelay;


public class Intake {

    private static final double shooter_booster_speed = 1;
    private static final double threshold = 0.02;
    private static final OneTap intakeButton = new OneTap();
    private static final OneTap outtakeButton = new OneTap();
    private static final OneTap diskNormalizerButton = new OneTap();

    private static final TwoStateMotorWithDelay DiskNormalizerBeforeIntake = new TwoStateMotorWithDelay(-1800, 1700);

    private static final ChangeState intakeState = new ChangeState();
    private static final ChangeState outtakeState = new ChangeState();
    public static final ChangeState diskNormalizer = new ChangeState();

    public static void variableSpeedIntake(double variableButton) {
        if (variableButton > threshold) {
            Hardware.intake.setPower(variableButton);
            Hardware.shooter_booster.setPower(variableButton);
        } else {
            Hardware.intake.setPower(0);
            Hardware.shooter_booster.setPower(0);
        }
    }


    public static void oneSpeedIntake(boolean button) {
        boolean activate = intakeButton.onPress(button);
        intakeState.changeMotorState(activate, 1, Hardware.intake);
        intakeState.changeServoState(activate, 1, Hardware.shooter_booster);
        DiskNormalizer(activate);
    }

    public static void oneSpeedOutTake(boolean button) {
        boolean activate = outtakeButton.onPress(button);
        outtakeState.changeMotorState(activate, -1, Hardware.intake);
        outtakeState.changeServoState(activate, -1, Hardware.shooter_booster);
    }

    public static void IntakeVar(Gamepad gamepad) {
        variableSpeedIntake(gamepad.right_trigger);
    }

    public static void DiskNormalizer(boolean button) {
        diskNormalizer.changeMotorStateSpeed(button, -500, Hardware.shooter_left, Hardware.shooter_right);
    }

    public static void IntakeOneSpeed(Gamepad gamepad) {
        if (!Shooter.shooter.getIsMotorRunning()) {
            oneSpeedIntake(gamepad.right_bumper);
        }
        DiskNormalizerBeforeIntake.DiskNormalizer(gamepad.y);
    }
    public static void OutTakeOneSpeed(Gamepad gamepad){
        if (!Shooter.shooter.getIsMotorRunning()) {
            oneSpeedOutTake(gamepad.right_bumper);
        }
    }

}
