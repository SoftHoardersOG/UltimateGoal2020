package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

public class MersTicksuri {
    private static final int ticksPerRotation = (int) (2048*4);
    private static final double wheelRadius = 1.9;
    private static final double wheelLength = 2 * Math.PI * wheelRadius;
    private static DcMotor dcMotor;
    private static int target;
    private static int error = 0;
    private static double maxPower = 0.7;
    private static int ticksPerCm = (int)(ticksPerRotation/wheelLength);
    private static int accelerationDist;
    private static int decelerationDist;
    private static double stopPower = 0; // -> 0
    public static double initalDist = 0;


    public static void startRunning(double distanceInCm, Directions direction, double maxPower) {
        int ticksForDistance = (int) (distanceInCm / wheelLength * ticksPerRotation);
        if (direction == Directions.FORWARD) {
            dcMotor = Hardware.left_encoder;
        }
        initalDist = dcMotor.getCurrentPosition();
        accelerationDist = (int)(distanceInCm/2.0*ticksPerCm+initalDist);
        decelerationDist = (int)(distanceInCm/2.0*ticksPerCm+initalDist);
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
        telemetry.addLine("The error is " + error);
        telemetry.update();
        if (current <= accelerationDist) {
            goForward(maxPower * error / accelerationDist);
        } else if (error>= decelerationDist) {
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
