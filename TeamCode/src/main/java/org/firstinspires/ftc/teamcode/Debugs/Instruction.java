package org.firstinspires.ftc.teamcode.Debugs;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.TeleOperated.ChangeShootingAngle;

public class Instruction {
    public static void Commands(Telemetry telemetry, boolean update){
        telemetry.addLine("                GAMEPAD 1");
        telemetry.addLine("  -->   Driving  : Joysticks ");
        telemetry.addLine("  -->   Angle control" );
        telemetry.addLine("***The current abs position is: " + ChangeShootingAngle.getAbsPosition());
        telemetry.addLine("  -->   Shooting");
        telemetry.addLine("***Press y to normalize disk before shooting");
        telemetry.addLine("**Press right_bumper for ");

        telemetry.addLine("                GAMEPAD 2");
        telemetry.addLine("       Shooting");
        telemetry.addLine("***Press a to shoot once");
        telemetry.addLine("***Hold b to shoot 3 times");
        telemetry.addLine("***Press x to switch between shooting positions");
        telemetry.addLine("***Press left bumper to activate/deactivate the shooting motors");
        telemetry.addLine("***Press a to shoot one ring");
        telemetry.addLine("***Hold b for continuous mode shooting");
        telemetry.addLine("***Press x to go to \"shoot\" position");
        telemetry.addLine("***Press y to go to \"free\" position");
        if(update){
            telemetry.update();
        }

    }
}
