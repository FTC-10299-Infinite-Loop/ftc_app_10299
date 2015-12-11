package com.qualcomm.ftcrobotcontroller.opmodes.customOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Created by WillOakley on 11/22/15.
 */
public class hoonerAutonomus extends LinearOpMode {

    DcMotor leftFront;
    DcMotor leftBack;
    DcMotor rightFront;
    DcMotor rightBack;
    DcMotor tapeMeasureLength;
    DcMotor tapeMeasureAngle;
    DcMotor winchLength;
    Servo blueToggle;
    Servo redToggle;

    @Override
    public void runOpMode() throws InterruptedException{

        //Use config profile "hoonerFinal"
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftBack.setDirection(DcMotor.Direction.FORWARD);

        rightBack = hardwareMap.dcMotor.get("rightBack");
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftFront.setDirection(DcMotor.Direction.FORWARD);

        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        tapeMeasureAngle = hardwareMap.dcMotor.get("measureAngle");

        tapeMeasureLength = hardwareMap.dcMotor.get("measureLength");
        tapeMeasureLength.setDirection(DcMotor.Direction.FORWARD);

        winchLength = hardwareMap.dcMotor.get("winchLength");
        winchLength.setDirection(DcMotor.Direction.FORWARD);

        redToggle = hardwareMap.servo.get("redToggle");

        blueToggle = hardwareMap.servo.get("blueToggle");


        //Begin program here

        leftBack.setPower(0.75);
        rightBack.setPower(0.75);
        leftFront.setPower(0.75);
        rightFront.setPower(0.75);
        sleep(1000);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
        tapeMeasureLength.setPower(0.5);
        sleep(200);
        tapeMeasureLength.setPower(0);
        tapeMeasureAngle.setPower(0.3);
        sleep(50);
        tapeMeasureAngle.setPower(0);
        sleep(100);
        leftBack.setPower(-0.75);
        rightBack.setPower(-0.75);
        leftFront.setPower(-0.75);
        rightFront.setPower(-0.75);
        sleep(500);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setPower(0);
        rightFront.setPower(0);
    }

}

