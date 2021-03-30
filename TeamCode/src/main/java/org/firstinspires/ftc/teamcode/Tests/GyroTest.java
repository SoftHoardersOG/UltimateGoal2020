package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.GyroSensor;

@TeleOp(name = "GyroTest")
public class GyroTest extends LinearOpMode {
    GyroSensor sensor;
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("x: ", sensor.rawX());
        telemetry.addData("y: ",sensor.rawY());
        telemetry.addData("z: ",sensor.rawZ());
        telemetry.update();
        while(!isStopRequested()&&isStarted()){
               
        }
    }
}
