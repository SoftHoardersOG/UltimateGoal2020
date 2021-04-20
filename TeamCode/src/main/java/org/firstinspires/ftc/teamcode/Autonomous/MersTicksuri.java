package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class MersTicksuri {
    private static final int ticksPerRotation = (int) (1440 / 1.5);
    private static final double wheelRadius = 1.9;
    private static final double wheelLength = 2 * Math.PI * wheelRadius;
    private static DcMotor dcMotor;
    private static int target;
    private static int error = 0;
    private static double maxPower = 0.7;
    private static int ticksPerCm = (int)(ticksPerRotation/wheelLength);
    private static int accelerationDist;
    private static int decelerationDist;
    private static double stopPower = -0.3; // -> 0.2 mue


    public static void startRunning(double distanceInCm, Directions direction, double maxPower) {
        accelerationDist = (int)(distanceInCm/3.0*ticksPerCm);
        decelerationDist = (int)(distanceInCm/3.0*ticksPerCm);
        int ticksForDistance = (int) (distanceInCm / wheelLength * ticksPerRotation);
        if (direction == Directions.FORWARD) {
            dcMotor = Hardware.front_encoder;
        }
        target = (dcMotor.getCurrentPosition()) + ticksForDistance;
        MersTicksuri.maxPower = maxPower;
        int current = (dcMotor.getCurrentPosition());
        error = target - current;
    }

    public static void goForward(double power) {
        Hardware.back_left.setPower(power);
        Hardware.back_right.setPower(power);
        Hardware.front_left.setPower(power);
        Hardware.front_right.setPower(power);
    }

    public static boolean stopControl() {
        return error < 0;
    }

    public static void toPosition(double distanceInCm, Directions direction, double maxPower, Telemetry telemetry, LinearOpMode opMode) {
        startRunning(distanceInCm, direction, maxPower);
        while (opMode.opModeIsActive() && !(opMode.isStopRequested()) && !stopControl()) {
            control(telemetry);
        }
        goForward(-0.05);
        opMode.sleep(200);
    }

    public static void control(Telemetry telemetry) {
        int current = (dcMotor.getCurrentPosition());
        error = target - current;
//        telemetry.addLine("The error is " + error);
//        telemetry.update();
        if (target-error <= accelerationDist) {
            goForward(maxPower * error / accelerationDist);
        } else if (target-error >= target - decelerationDist) {
            //telemetry.addLine("pula");
            //telemetry.update();
            int descError = target - decelerationDist;
            double power = (maxPower - stopPower) / descError * error + stopPower;
            goForward(power);
        } else {
//            telemetry.addData("Last velocity", ((DcMotorEx) Hardware.front_encoder).getVelocity());
//            telemetry.update();
            goForward(maxPower);
        }
    }
}
