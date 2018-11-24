package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Turkey Train", group = "Driver")
public class TurkeyTrain extends OpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private CruiseControl cruiseControl;

    @Override
    public void init() {
        leftMotor = hardwareMap.dcMotor.get("left");
        rightMotor = hardwareMap.dcMotor.get("right");
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        if (gamepad1.x) {
            cruiseControl = CruiseControl.ENABLED;
        }
        if (gamepad1.x && cruiseControl == CruiseControl.ENABLED) {
            cruiseControl = CruiseControl.DISABLED;
        }
        if (gamepad1.dpad_up) {
            cruiseControl = CruiseControl.ENABLED;
        }
        if (gamepad1.dpad_down) {
            cruiseControl = CruiseControl.HALF;
        }


        if (cruiseControl == CruiseControl.ENABLED) {
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
        }
        else if (cruiseControl == CruiseControl.HALF) {
            leftMotor.setPower(-0.5);
            rightMotor.setPower(0.5);
        }
        else if (cruiseControl == CruiseControl.ENABLED) {
            leftMotor.setPower(0);
            rightMotor.setPower(0);
        }
        leftMotor.setPower(-gamepad1.right_stick_y);
        rightMotor.setPower(gamepad1.left_stick_y);
    }
}
