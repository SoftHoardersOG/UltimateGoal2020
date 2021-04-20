package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;

@TeleOp(name = "Test PID Tuning")
public class Ramy extends LinearOpMode {

    @Override
    public void runOpMode(){
        Hardware.init(hardwareMap, telemetry);
        waitForStart();
        GyroPID.rotate(7,  telemetry, Ramy.this);
        sleep(1000);
        GyroPID.rotate(3,telemetry,Ramy.this);
        sleep(1000);
        GyroPID.rotate(-5,telemetry,Ramy.this);
        sleep(1000);
        GyroPID.rotate(90,telemetry,Ramy.this);
        sleep(1000000);
    }

}
