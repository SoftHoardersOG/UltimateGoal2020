package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Utils.Autonomous.AutonomousMovement;
import org.firstinspires.ftc.teamcode.Utils.Autonomous.NormalizeAngle;

public class GyroPID {


/*    private static final double P = 0.025;
    private static final double I = 0.00;
    private static final double D = 0.08;*/

    private static final double P = 0.025;
    private static final double I = 0.00;
    private static final double D = 0.08;
    private static final double AdmittedError = 0.3f;
    private double target;
    private static final int ControlNumber = 100;
    private static int nr = 0;
    private double error = 0;
    private double lastError = 0;
    private double Sum = 0;
    private static final double ILimit = 0.5;

    public void StartPID(double command) {
        target = command + NormalizeAngle.GetAngle();
        if (target > 360) {
            target -= 360;
        } else if (target < 0) {
            target += 360;
        }
    }

    public void PID(Telemetry telemetry) {
        telemetry.addData("error: ", error);
        telemetry.update();
        error = target - NormalizeAngle.GetAngle();
        Sum += error;
        double IEffect = Sum * I;
        if (IEffect > ILimit) {
            Sum = 0;
            IEffect = ILimit;
        } else if (IEffect < -ILimit) {
            Sum = 0;
            IEffect = -ILimit;
        }
        double errorDifference = error - lastError;
        if (error > 180) {
            error -= 360;
        } else if (error < -180) {
            error += 360;
        }
        double output = P * error + D * errorDifference + IEffect;
        if (nr == ControlNumber) {
            output = 0;
        } else if (error > -AdmittedError && error < AdmittedError) {
            nr++;
        }
        AutonomousMovement.Rotate(output);
        lastError = error;
    }
}
