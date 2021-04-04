package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.HardwarePack.Hardware;
import org.firstinspires.ftc.teamcode.TeleOperated.Shooter;

public class SingleShooting {
    private static boolean wasPressed=false;
    public static OneTap idlerManual = new OneTap();
    public static void singleShooting(boolean buttonForSingleShooting){
        if(idlerManual.onPress(buttonForSingleShooting)) {
            if(wasPressed){
                Hardware.shooter_idler.setPosition(Shooter.free);
                wasPressed=false;
            }else{
                Hardware.shooter_idler.setPosition(Shooter.push);
                wasPressed=true;
            }
        }
    }
}
