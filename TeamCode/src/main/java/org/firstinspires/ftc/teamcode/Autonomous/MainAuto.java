package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.Utils.Autonomous.NormalizeAngle;

@TeleOp(name = "Autonomous")
public class MainAuto extends LinearOpMode {

    double normalizeCommand(double Command){

        if (Command > 180) {
            Command -= 360;
        } else if (Command < -180) {
            Command += 360;
        }
        return Command;
    }

    private static final int shootSpeed = 1350; //1600
    private static final double shootPosition = 0.68; //0.6
    @Override
    public void runOpMode() throws InterruptedException {

        Hardware.init(hardwareMap, telemetry);
        Hardware.shooter_idler.setPosition(Shooter.free);
        waitForStart();
        double starPos = NormalizeAngle.GetAngle();
        Hardware.angle_control_left_s.setPosition(shootPosition);
        Hardware.angle_control_right_s.setPosition(1 - shootPosition);

        ((DcMotorEx)Hardware.shooter_right).setVelocity(shootSpeed);
        ((DcMotorEx)Hardware.shooter_left).setVelocity(shootSpeed);

        MersTicksuri.toPosition(170, Directions.FORWARD,0.7,telemetry, MainAuto.this);
        double command = starPos - NormalizeAngle.GetAngle();
        command = normalizeCommand(command);

       // command+=4;
        command = normalizeCommand(command-1); //command
        int debugpos = Hardware.center_encoder.getCurrentPosition();
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
        Hardware.shooter_idler.setPosition(Shooter.push);
        sleep(100);
        Hardware.shooter_idler.setPosition(Shooter.free);
        sleep(100);
        command=7; // 6
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
        Hardware.shooter_idler.setPosition(Shooter.push);
        sleep(100);
        Hardware.shooter_idler.setPosition(Shooter.free);
        sleep(100);
        command=-10; //-10
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
        Hardware.shooter_idler.setPosition(Shooter.push);
        sleep(100);
        //Hardware.shooter_idler.setPosition(Shooter.free);

        //pid with new command
        telemetry.update();
        while(opModeIsActive() && !isStopRequested()){
            telemetry.addLine("CENTER" + debugpos);
            telemetry.update();

        }

    }
}
