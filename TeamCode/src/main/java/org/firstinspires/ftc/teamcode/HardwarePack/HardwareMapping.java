package org.firstinspires.ftc.teamcode.HardwarePack;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil;

import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.getCRServo;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.getDC;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.getIMU;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.getServo;
import static org.firstinspires.ftc.teamcode.Utils.Hardware.HardwareUtil.getWebcam;

public class HardwareMapping extends HardwareDeclarations{
    protected static void hardwareMapping(HardwareMap hardwareMap) {

        front_right = getDC("front_right", hardwareMap);
        front_left = getDC("front_left", hardwareMap);
        back_left = getDC("back_left", hardwareMap);
        back_right = getDC("back_right", hardwareMap);

        grabber = getDC("grabber", hardwareMap);
        grabber_back = getServo("grabber_back", hardwareMap);
        grabber_front = getServo("grabber_front", hardwareMap);

        intake = getDC("intake",hardwareMap);

        webcam = getWebcam("Webcam 1", hardwareMap);
        cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        imu = getIMU("imu", hardwareMap);
        imu1 = getIMU("imu1", hardwareMap);

        angle_control_left_s = getServo("angle_control_left_s",hardwareMap);
        angle_control_right_s = getServo("angle_control_right_s",hardwareMap);

        shooter_left = getDC("shooter_left", hardwareMap);
        shooter_right = getDC("shooter_right",hardwareMap);

        shooter_idler = getServo("shooter_idler", hardwareMap);
        shooter_booster = getCRServo("shooter_booster", hardwareMap);

        HardwareUtil.InitializeIMU(imu);
        HardwareUtil.InitializeIMU(imu1);

        left_encoder = back_left;
        center_encoder = back_right;
        right_encoder = front_left;

        //wall_left=getServo("wall_left", hardwareMap);
        //wall_right=getServo("wall_right", hardwareMap);
    }
}
