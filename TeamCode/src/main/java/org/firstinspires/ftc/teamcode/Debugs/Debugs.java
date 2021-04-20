 package org.firstinspires.ftc.teamcode.Debugs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Intake;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.Utils.Autonomous.NormalizeAngle;
import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;

 public class Debugs {

    public static void movementSpeedDebug(Telemetry telemetry) {
        telemetry.addData("front_left: ", Hardware.front_left.getPower());
        telemetry.addData("front_right: ", Hardware.front_right.getPower());
        telemetry.addData("back_left: ", Hardware.back_left.getPower());
        telemetry.addData("back right: ", Hardware.back_right.getPower());
    }

    public static void movementSpeedDebug(Telemetry telemetry, boolean update) {
        telemetry.addData("front_left: ", Hardware.front_left.getPower());
        telemetry.addData("front_right: ", Hardware.front_right.getPower());
        telemetry.addData("back_left: ", Hardware.back_left.getPower());
        telemetry.addData("back right: ", Hardware.back_right.getPower());
        if(update){
            telemetry.update();
        }
    }

    public static void angleDebug(Telemetry telemetry,boolean update){
        telemetry.addData("Current Angle: ",NormalizeAngle.GetAngle());

        if(update){
            telemetry.update();
        }

    }

    public static void timerDebug(Telemetry telemetry){
        telemetry.addData("Timer value: ", Shooter.getTimer());
        telemetry.update();
    }

    public static void encoderDebug(Telemetry telemetry, boolean update){
        telemetry.addData("right value: ", Hardware.front_encoder.getCurrentPosition());
        telemetry.addData("center value: ", Hardware.center_encoder.getCurrentPosition());
        if(update){
            telemetry.update();
        }
    }

    public static void grabberPosition(Telemetry telemetry, boolean update){
        telemetry.addLine("grabber position: "+Hardware.grabber.getCurrentPosition());
        if(update){
            telemetry.update();
        }
    }

    public static void motorVelocity(Telemetry telemetry, boolean update){
        telemetry.addData("PID PARAMETERS: ",((DcMotorEx) Hardware.shooter_right).getPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER));
        telemetry.addData("shooter_right",((DcMotorEx) Hardware.shooter_right).getVelocity());
        telemetry.addData("shooter_left",((DcMotorEx) Hardware.shooter_left).getVelocity());
        if (update){
            telemetry.update();
        }
    }
    public static void changeShooterAngleDebug(Telemetry telemetry, boolean update){
        telemetry.addLine("changeShooterAngle current position is: "+ ChangeShootingAngle.getAbsPosition());
        if(update){
            telemetry.update();
        }
    }


}
