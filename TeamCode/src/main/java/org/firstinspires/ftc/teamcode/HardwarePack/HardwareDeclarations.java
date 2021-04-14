package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class HardwareDeclarations {
    public static DcMotor front_right, front_left, back_right, back_left;
    public static DcMotor right_encoder, left_encoder, center_encoder;
    public static DcMotor shooter_left,shooter_right;
    public static DcMotor intake;
    public static Servo angle_control_left_s, angle_control_right_s;
    public static Servo shooter_idler;
    public static CRServo shooter_booster;
    public static WebcamName webcam;
    public static BNO055IMU imu;
}

