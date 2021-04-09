package org.firstinspires.ftc.teamcode.TeleOperated;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class Gathering {

    public static double shooter_booster_speed=1f;
    public static double threshold=0.02f;

    public static void StartGathering(Gamepad gamepad){
        if(gamepad.right_trigger>threshold){
            Hardware.intake.setPower(gamepad.right_trigger);
            Hardware.shooter_booster.setPower(shooter_booster_speed);
        }else{
            Hardware.intake.setPower(0);
            Hardware.shooter_booster.setPower(0);
        }
    }

}
