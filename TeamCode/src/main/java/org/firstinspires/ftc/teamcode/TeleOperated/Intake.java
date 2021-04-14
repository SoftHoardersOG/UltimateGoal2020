package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class Intake {

    public static double shooter_booster_speed = 1;
    public static double threshold = 0.02;


    public static void variableSpeedIntake(double variableButton) {
        if (variableButton > threshold) {
            Hardware.intake.setPower(variableButton);
            Hardware.shooter_booster.setPower(variableButton);
        } else {
            Hardware.intake.setPower(0);
            Hardware.shooter_booster.setPower(0);
        }
    }

    public  static void Intake(Gamepad gamepad){
        variableSpeedIntake(gamepad.right_trigger);
    }

}
