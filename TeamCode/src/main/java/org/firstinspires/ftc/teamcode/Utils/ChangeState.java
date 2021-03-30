package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ChangeState {

    private boolean isMotorRunning = false;
    private boolean isServoRunning = false;

    public void changeMotorState(boolean activate, double power, DcMotor... dcMotors) {
        if(activate) {
            if (isMotorRunning) {
                for (DcMotor dcMotor : dcMotors) {
                    dcMotor.setPower(0);
                }
                isMotorRunning = false;
            } else {
                for (DcMotor dcMotor : dcMotors) {
                    dcMotor.setPower(power);
                }
                isMotorRunning = true;
            }
        }
    }

    public void changeServoState(boolean activate, double power, CRServo... crServos) {
        if (activate) {
            if (isServoRunning) {
                for (CRServo crServo : crServos) {
                    crServo.setPower(0);
                }
                isServoRunning = false;
            } else {
                for (CRServo crServo : crServos) {
                    crServo.setPower(power);
                }
                isServoRunning = true;
            }
        }
    }

}
