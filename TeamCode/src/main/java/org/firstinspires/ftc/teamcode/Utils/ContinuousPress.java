package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.util.ElapsedTime;

public class ContinuousPress {

    private final ElapsedTime timer;
    private final double  incrementSpeedPerSecond;
    private double startTime;
    private double endTime;

    public ContinuousPress(ElapsedTime timer, double incrementSpeedPerSecond) {
        this.timer = timer;
        this.incrementSpeedPerSecond = incrementSpeedPerSecond;
        startTime = 0;
        endTime = 0;
    }

    public double incrementByTime(){
        if(startTime==0){
            startTime = timer.milliseconds();
        }
        endTime = timer.milliseconds();
        double elapsed = incrementSpeedPerSecond*(endTime - startTime)/1000;
        startTime = timer.milliseconds();
        return elapsed;
    }

}
