package org.firstinspires.ftc.teamcode.Debugs;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;

public class Debugs {

    public static void speedDebug(Telemetry telemetry) {
        telemetry.addData("front_left: ", Hardware.front_left.getPower());
        telemetry.addData("front_right: ", Hardware.front_right.getPower());
        telemetry.addData("back_left: ", Hardware.back_left.getPower());
        telemetry.addData("back right: ", Hardware.back_right.getPower());
    }

    public static void speedDebug(Telemetry telemetry, boolean update) {
        telemetry.addData("front_left: ", Hardware.front_left.getPower());
        telemetry.addData("front_right: ", Hardware.front_right.getPower());
        telemetry.addData("back_left: ", Hardware.back_left.getPower());
        telemetry.addData("back right: ", Hardware.back_right.getPower());
        if(update){
            telemetry.update();
        }
    }

    public static void timerDebug(Telemetry telemetry){
        telemetry.addData("Timer value: ", Shooter.getTimer());
        telemetry.update();
    }

    public static void encoderDebug(Telemetry telemetry, boolean update){
        telemetry.addData("left value: ", Hardware.left_encoder.getCurrentPosition());
        telemetry.addData("right value: ", Hardware.right_encoder.getCurrentPosition());
        telemetry.addData("center value: ", Hardware.center_encoder.getCurrentPosition());
        if(update){
            telemetry.update();
        }
    }

}
