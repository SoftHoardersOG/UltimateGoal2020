package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="TrowTest" ,group="Tests")
public class AruncareTest extends LinearOpMode{
    private static final double push = 0.05;
    private static final double free = 0.16;
    DcMotor leftTrow, rightTrow;
    Servo trowLoader;


    @Override
    public void runOpMode() {
        leftTrow = hardwareMap.get(DcMotor.class,"left");
        rightTrow = hardwareMap.get(DcMotor.class,"right");
        trowLoader = hardwareMap.get(Servo.class,"loader");
        telemetry.addLine("Initialisation completed!");
        telemetry.update();
        waitForStart();
        trowLoader.setPosition(free);
        boolean fristPress=true;
        boolean firstPressMotor=true;
        boolean motorState=false;
        while (opModeIsActive() && !isStopRequested()){

            if(gamepad1.left_bumper && firstPressMotor){
                if(!motorState){
                    leftTrow.setPower(-0.9);
                    rightTrow.setPower(0.9);
                    motorState=true;
                }
                else{
                    leftTrow.setPower(0);
                    rightTrow.setPower(0);
                    motorState=false;
                }
                firstPressMotor=false;
            }
            if(!gamepad1.left_bumper){
                firstPressMotor=true;
            }

            if(gamepad1.a && fristPress){
                trowLoader.setPosition(push);
                sleep(40);
                trowLoader.setPosition(free);
                fristPress = false;
            }
            if(!gamepad1.a){
                fristPress=true;
            }

            if(gamepad1.b){
                trowLoader.setPosition(push);
                sleep(50);
                trowLoader.setPosition(free);
                sleep(70);
            }
            if(gamepad1.x){
                trowLoader.setPosition(push);
            }
            if(gamepad1.y){
                trowLoader.setPosition(free);
            }
        }




    }
}
