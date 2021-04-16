package org.firstinspires.ftc.teamcode.TeleOperated;

import android.nfc.cardemulation.HostApduService;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class ChangeShootingAngle {

    private static final double incrementValue = 0.03;
    private static final double upperLimit = 0.7;
    private static final double lowerLimit = 0;

    public static double getIncrementValue() {
        return incrementValue;
    }

    public static double getAbsPosition() {
        return absPosition;
    }

    private static double absPosition = 0;
    private static OneTap up = new OneTap();
    private static OneTap down = new OneTap();

    private static void AngleControl(double position) {
        Devices.setServoPosition(Hardware.angle_control_right_s, "angle_control_right_s", 1 - position);
        Devices.setServoPosition(Hardware.angle_control_left_s, "angle_control_left_s", position);
    }

    private static void LimitAngle() {
        if (absPosition < lowerLimit) {
            absPosition = lowerLimit;
        }
        if (absPosition > upperLimit) {
            absPosition = upperLimit;
        }
    }

    private static void SequentialIncrement(boolean upB, boolean downB) {
        if (up.onPress(upB)) {
            absPosition += incrementValue;
        }

        if (down.onPress(downB)) {
            absPosition -= incrementValue;
        }

    }

    public static void AngleControl(Gamepad gamepad) {
        LimitAngle();
        AngleControl(absPosition);
        ShootingIntakePositions(gamepad.a,gamepad.b);
        SequentialIncrement(gamepad.x, gamepad.y);
    }

    public static void ShootingIntakePositions(boolean firstButton, boolean secondButton) {
        if (firstButton) {
           absPosition=0.57; //shooting
        }else if(secondButton){
          absPosition=0.48; //intake
        }
    }

}
