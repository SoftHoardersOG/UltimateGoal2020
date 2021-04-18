package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;

public class Wobble {
    private static final double back_grabPosition = 0.14f;
    private static final double back_freePosition = 0.47f;
    private static final double front_grabPosition = 0.89f;
    private static final double front_freePosition = 0.55f;
    private static final ServoToPosition servoToPosition_front = new ServoToPosition(Hardware.grabber_front, "grabber_front", front_freePosition, front_grabPosition);
    private static final ServoToPosition servoToPosition_back = new ServoToPosition(Hardware.grabber_back, "grabber_back", back_freePosition, back_grabPosition);

    public static void servoPositions(boolean button) {
        servoToPosition_back.modifyPosition(button);
        servoToPosition_front.modifyPosition(button);
    }

    public static void motorArm(double up, double down) {
        if (down <= 0.05) Hardware.grabber.setPower(up / 2);
        if (up <= 0.05) Hardware.grabber.setPower(-down / 2);
    }

    public static void initialization() {

        Hardware.grabber.setPower(0.5);
        Hardware.grabber_front.setPosition(front_grabPosition);
        Hardware.grabber_back.setPosition(back_grabPosition);
    }

    public static void motorArmToPosition(boolean button, int position) {
        if (button) {
            Hardware.grabber.setTargetPosition(position);
        }

    }

    public static void wobbleControl(Gamepad gamepad) {
        servoPositions(gamepad.x);
        motorArmToPosition(gamepad.dpad_down, 700);
        motorArmToPosition(gamepad.dpad_up, 0);

    }
}
