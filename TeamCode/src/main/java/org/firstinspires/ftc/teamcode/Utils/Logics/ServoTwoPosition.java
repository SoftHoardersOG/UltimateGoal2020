package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class ServoTwoPosition {
    private boolean wasPressed=false;
    private final OneTap idlerManual = new OneTap();
    private final Servo servo;
    private final String name;
    private final double firstPosition;
    private final double secondPosition;

    public ServoTwoPosition(Servo servo, String name, double firstPosition, double secondPosition) {
        this.servo = servo;
        this.name = name;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public void modifyPosition(boolean buttonForSingleShooting){
        if(idlerManual.onPress(buttonForSingleShooting)) {
            if(wasPressed){
                Devices.setServoPosition(servo,name,firstPosition);
                wasPressed=false;
            }else{
                Devices.setServoPosition(servo,name,secondPosition);
                wasPressed=true;
            }
        }
    }
}
