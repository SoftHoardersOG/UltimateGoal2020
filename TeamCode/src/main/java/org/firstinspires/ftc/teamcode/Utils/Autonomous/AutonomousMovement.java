package org.firstinspires.ftc.teamcode.Utils.Autonomous;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class AutonomousMovement {
    public static void Rotate(double power){
        Hardware.front_left.setPower(-power);
        Hardware.front_right.setPower(power);
        Hardware.back_left.setPower(-power);
        Hardware.back_right.setPower(power);
    }

}
