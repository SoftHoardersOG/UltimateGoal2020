package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.Debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Debugs.Debugs;
import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;
import org.firstinspires.ftc.teamcode.TeleOperated.Wobble;
import org.firstinspires.ftc.teamcode.Utils.Autonomous.NormalizeAngle;

@TeleOp(name = "Autonomous")
public class MainAuto extends LinearOpMode {

    private static double normalizeCommand(double Command){

        if (Command > 180) {
            Command -= 360;
        } else if (Command < -180) {
            Command += 360;
        }
        return Command;
    }

    private static void shoot(LinearOpMode teleOp){
        Hardware.shooter_idler.setPosition(Shooter.push);
        teleOp.sleep(100);
        Hardware.shooter_idler.setPosition(Shooter.free);
        teleOp.sleep(100);

    }

    private static final int shootSpeed = 1350; //1600
    private static final double shootPosition = 0.69; //0.66 ; 0.6
    @Override
    public void runOpMode() throws InterruptedException {

        Hardware.init(hardwareMap, telemetry);
        //Wobble.initialization();
        Hardware.shooter_idler.setPosition(Shooter.free);
        waitForStart();
        double starPos = NormalizeAngle.GetAngle();
        Hardware.angle_control_left_s.setPosition(shootPosition);
        Hardware.angle_control_right_s.setPosition(1 - shootPosition);

        ((DcMotorEx)Hardware.shooter_right).setVelocity(shootSpeed);
        ((DcMotorEx)Hardware.shooter_left).setVelocity(shootSpeed);

        //170 pt shooting

        MersTicksuri.toPosition(150, Directions.FORWARD,0.5,telemetry, MainAuto.this);
        sleep(300);

        double command = starPos - NormalizeAngle.GetAngle();

        //command+=4;
        command = normalizeCommand(command); //command
        int debugpos = Hardware.center_encoder.getCurrentPosition();
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
       shoot(this);

        command=6; // 6
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
       shoot(this);

        command=-11; //-10
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
       shoot(this);



        ((DcMotorEx) Hardware.shooter_left).setVelocity(0);
        ((DcMotorEx) Hardware.shooter_right).setVelocity(0);
        command = starPos - NormalizeAngle.GetAngle();
        command = normalizeCommand(command);
        GyroPID.rotate(command,telemetry,MainAuto.this);
        sleep(300);
//        MersTicksuri.toPosition(15, Directions.FORWARD,0.7,telemetry, MainAuto.this);

        while(opModeIsActive() && !isStopRequested()){
            //telemetry.addLine("CENTER" + debugpos);
            //telemetry.update();

        }

    }
}
