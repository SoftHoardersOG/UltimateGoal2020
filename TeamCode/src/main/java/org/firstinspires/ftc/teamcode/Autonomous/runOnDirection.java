package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;

public class runOnDirection {
    private static final int ticksPerRotation = 1440;
    private static final double wheelRadius = 1.9;
    private static final double wheelLength = 2 * Math.PI * wheelRadius;
    private static final double P = 0.0008;
    private static final double D = 0.01;
    private static double I = 0.0000014;
    private static double Sum;
    private static final double Limit = 0.92;
    private static int target;
    private static DcMotor dcMotor;
    private static double maxPower;
    private static int lastError;


    public static void startRunning(double distanceInCm, Directions direction, double maxPower) {
        int ticksForDistance = (int) (distanceInCm / wheelLength * ticksPerRotation);
        if (direction == Directions.FORWARD) {
            dcMotor = Hardware.front_encoder;
        }
        target = (dcMotor.getCurrentPosition()) + ticksForDistance;
        runOnDirection.maxPower = maxPower;
        lastError = target - (dcMotor.getCurrentPosition());
    }

    private static void goForward(double power) {
        Hardware.back_left.setPower(power);
        Hardware.back_right.setPower(power);
        Hardware.front_left.setPower(power);
        Hardware.front_right.setPower(power);
    }

    public static void Pid(Telemetry telemetry) {
        int current = (dcMotor.getCurrentPosition());
        int error = target - current;
        Sum += error;
        double IEffect = I * Sum;
        if (IEffect >= Limit) {
            IEffect = Limit;
        } else if (IEffect < 0) {
            IEffect = 0;
        }
        if(error<=40){
            I=0;
        }
        telemetry.addLine(error + "");
        telemetry.update();
        int errorDiff = error - lastError;
        double output = P * error + D * errorDiff - IEffect;
        if (output > maxPower) {
            output = maxPower;
        } else if (output < -maxPower) {
            output = -maxPower;
        }
        goForward(output);
        lastError = error;
    }

}