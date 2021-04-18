package org.firstinspires.ftc.teamcode.Utils.Logics;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Utils.Devices;
import org.firstinspires.ftc.teamcode.Utils.Gamepads.OneTap;

public class ServoToPosition {
    private boolean wasPressed=false;
    private final OneTap tap = new OneTap();
    private final Servo servo;
    private final String name;
    private final double firstPosition;
    private final double secondPosition;

    public ServoToPosition(Servo servo, String name, double firstPosition, double secondPosition) {
        this.servo = servo;
        this.name = name;
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
    }

    public void modifyPosition(boolean button){
        if(tap.onPress(button)) {
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
