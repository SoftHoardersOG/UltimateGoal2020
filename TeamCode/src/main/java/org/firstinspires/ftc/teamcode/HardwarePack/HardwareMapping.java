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

        intake = getDC("intake",hardwareMap);

        webcam = getWebcam("webcam", hardwareMap);

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

        right_encoder = front_right;
        left_encoder = front_left;
        center_encoder = back_left;
    }
}
