package org.firstinspires.ftc.teamcode.TeleOperated;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Logics.ServoToPosition;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

public class Wall {
    private static final double left_extendedPosition = 0.9f;
    private static final double left_retractedPosition = 0.0f;
    private static final double right_retractedPosition = 1.0f;
    private static final double right_extendedPosition = 0.0f;
    private static final ServoToPosition servoToPosition_left = new ServoToPosition(Hardware.wall_left, "wall_left", left_retractedPosition, left_extendedPosition);
    private static final ServoToPosition servoToPosition_right = new ServoToPosition(Hardware.wall_right, "wall_right", right_retractedPosition, right_extendedPosition);

    public static void wallControl(Gamepad gamepad){
        servoToPosition_left.modifyPosition(gamepad.x);
        servoToPosition_right.modifyPosition(gamepad.y);
    }

    public static void initialization(){
        Hardware.wall_left.setPosition(left_retractedPosition);
        Hardware.wall_right.setPosition(right_retractedPosition);
    }
}
