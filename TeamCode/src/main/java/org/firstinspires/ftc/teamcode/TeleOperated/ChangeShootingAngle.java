package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.OneTap;

public class ChangeShootingAngle {

    private static final double incrementValue = 0.1;
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
        Hardware.angleControlSecvR.setPosition(position);
        Hardware.angleControlSecvL.setPosition(1 - position);
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
        SequentialIncrement(gamepad.x, gamepad.y);
    }


}
