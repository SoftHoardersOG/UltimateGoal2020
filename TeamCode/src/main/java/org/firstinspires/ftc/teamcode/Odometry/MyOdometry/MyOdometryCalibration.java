//package org.firstinspires.ftc.teamcode.Odometry.MyOdometry;
//
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//
//import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
//@TeleOp(name = "MyOdometryCalibration")
//public class MyOdometryCalibration extends LinearOpMode {
//
//    private static final double ODOMETER_WHEEL_DIAMETER_CM = 3.8;
//    private static final double ODOMETER_WHEEL_LENGHT_CM = Math.PI * ODOMETER_WHEEL_DIAMETER_CM;
//    private static final double TICKS_PER_ROTATION = 360*4;
//    private static final double TICKS_PER_CM = TICKS_PER_ROTATION/ODOMETER_WHEEL_LENGHT_CM;
//    private double rotateSpeed = 0.3;
//
//    double getAngle(){
//        return Hardware.imu.getAngularOrientation().firstAngle;
//    }
//    private void rotateRobot(double rotateSpeed){
//        Hardware.front_right.setPower(rotateSpeed);
//        Hardware.back_right.setPower(rotateSpeed);
//        Hardware.front_left.setPower(-rotateSpeed);
//        Hardware.back_left.setPower(-rotateSpeed);
//    }
//    private void stopRobot(){
//        Hardware.front_right.setPower(0);
//        Hardware.back_right.setPower(0);
//        Hardware.front_left.setPower(0);
//        Hardware.back_left.setPower(0);
//    }
//    public static String getEncoderTicks(DcMotor encoder, String name){
//        return name+" "+ encoder.getCurrentPosition();
//    }
//    @Override
//    public void runOpMode(){
//
//        Hardware.init(hardwareMap, telemetry);
//        waitForStart();
//        double currentAngle = Hardware.imu.getAngularOrientation().firstAngle;
//        while(getAngle()<=90)
//        {
//            rotateRobot(rotateSpeed);
//            if(getAngle()>=45&&getAngle()<=50){
//               rotateSpeed=.2;
//            }
//            if(getAngle()>=55&&getAngle()<=65){
//                rotateSpeed=.1;
//            }
//            //Waiting for the robot to turn
//        }
//        stopRobot();
//        while (opModeIsActive() && !isStopRequested()) {
//            telemetry.addLine(getEncoderTicks(Hardware.right_encoder,"right:_encoder"));
//            telemetry.addLine(getEncoderTicks(Hardware.left_encoder,"left_encoder"));
//            telemetry.addLine(getEncoderTicks(Hardware.center_encoder,"center_encoder"));
//            telemetry.addLine("IMU Angle: " + getAngle());
//        }
//
//    }
//}
