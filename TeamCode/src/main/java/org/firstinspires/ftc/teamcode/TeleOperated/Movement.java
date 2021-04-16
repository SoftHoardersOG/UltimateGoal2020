package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class Movement {

    public static void driving(Gamepad gamepad) {

        double r = -Math.hypot(gamepad.right_stick_x, gamepad.right_stick_y);
        double robotAngle = Math.atan2(gamepad.right_stick_y, gamepad.right_stick_x) - Math.PI / 4;
        double leftX = -gamepad.left_stick_x;
        double v1 = Math.sin(robotAngle);
        double v2 = Math.cos(robotAngle);
        double v3 = Math.sin(robotAngle);
        double v4 = Math.cos(robotAngle);
        double maxi = Math.max(v1, v2);
        maxi = Math.max(maxi, v3);
        maxi = Math.max(maxi, v4);
        maxi = Math.abs(maxi);
        r = Math.pow(r, 3);
        double incremental;
        incremental = r / maxi;
        v1 *= incremental;
        v2 *= incremental;
        v3 *= incremental;
        v4 *= incremental;
        if (gamepad.left_stick_x > 0.01) {
            leftX = Math.pow(leftX, 3) - 0.1;
        } else  if (gamepad.left_stick_x < -0.01){
            leftX = Math.pow(leftX, 3) + 0.1;
        } else {
            leftX = 0;
        }
        Hardware.back_left.setPower(v1 - leftX);
        Hardware.front_left.setPower(v2 - leftX);
        Hardware.back_right.setPower(v3 + leftX);
        Hardware.front_right.setPower(v4 + leftX);
    }

}