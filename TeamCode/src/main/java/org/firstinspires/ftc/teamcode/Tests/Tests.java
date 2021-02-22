package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Movement;

import java.util.Objects;

@TeleOp(name = "tests")
public class Tests extends LinearOpMode {

    private String getEncoderTicks(DcMotor encoder, String name) {
        try {
            Objects.requireNonNull(encoder, "Motor " + name + "is null!");
            return name + ": " + encoder.getCurrentPosition() / 4;

        } catch (Exception e) {
            System.err.println("Exception occurred while reading encoder value: " + e.getMessage());
            return name + ": N/A";
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Hardware.init(hardwareMap, telemetry);
        RobotLog.i("Started custom logging via RobotLog..");
        waitForStart();

//        EncoderReader reader = new EncoderReader(Hardware.left_encoder, Hardware.right_encoder, Hardware.center_encoder);
//
//        Thread thread = new Thread(reader);
//        thread.start();

        while (opModeIsActive() && !isStopRequested()) {
            try {

                Movement.driving(gamepad1);


                String leftEncoderValue = EncoderReader.getEncoderTicks(Hardware.left_encoder, "left_encoder");
                String centerEncoderValue = getEncoderTicks(Hardware.center_encoder, "center_encoder");
                String rightEncoderValue = getEncoderTicks(Hardware.right_encoder, "right_encoder");
                System.err.println(leftEncoderValue + " " + centerEncoderValue + " " + rightEncoderValue);

                telemetry.addLine(leftEncoderValue);
                telemetry.addLine(centerEncoderValue);
                telemetry.addLine(rightEncoderValue);
                telemetry.update();
             //   sleep(200);
            } catch (Exception e) {
                System.err.println("Exception occurred in main loop: " + e.getMessage());
            }

        }
    }


}
