package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;

public class runOnDirection {
    private static final int ticksPerRotation = 0;
    private static final double wheelRadius = 0;
    private static final double wheelLength = 2 * Math.PI * wheelRadius;

    public static void startRunning(double distanceInCm, Directions direction, double maxPower){
        int ticksForDistance = (int) (distanceInCm * wheelLength);
        if(direction==Directions.FORWARD){
            HardwareUtil.RunToPosition(Hardware.front_left);
            HardwareUtil.RunWithoutEncoders(Hardware.front_right,Hardware.back_left,Hardware.back_right);
            Hardware.front_left.setPower(maxPower);
            Hardware.front_left.setTargetPosition(ticksForDistance);
        }
    }

}
