package com.qualcomm.ftcrobotcontroller.opmodes.customOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/*
 * Created by woakley5 on 11/17/2015.
 */
public class hoonerTeleOpSteering extends OpMode {

    /**
     * Indicate whether a message is a available to the class user.
     */
    private boolean v_warning_generated = false;

    /**
     * Store a message to the user if one has been generated.
     */
    private String v_warning_message;

    private DcMotor leftFront;
    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor tapeMeasureLength;
    private DcMotor tapeMeasureAngle;
    private DcMotor winchLength;
    private Servo blueToggle;
    private Servo redToggle;

    public hoonerTeleOpSteering() {
        //
        // Initialize base classes.
        //
        // All via self-construction.

        //
        // Initialize class members.
        //
        // All via self-construction.

    }


    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     * The system calls this member once when the OpMode is enabled.
     */

    @Override
    public void init() {
        //
        // Use the hardwareMap to associate class members to hardware ports.
        //
        // Note that the names of the devices (i.e. arguments to the get method)
        // must match the names specified in the configuration file created by
        // the FTC Robot Controller (Settings-->Configure Robot).
        //
        // The variable below is used to provide telemetry data to a class user.
        //
        v_warning_generated = false;
        v_warning_message = "Can't map; ";

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
        redToggle.setDirection(Servo.Direction.FORWARD);

        blueToggle = hardwareMap.servo.get("blueToggle");
        blueToggle.setDirection(Servo.Direction.REVERSE);

    }
    /**
     * Access whether a warning has been generated.
     */
    boolean a_warning_generated()

    {
        return v_warning_generated;

    }

    /**
     * Access the warning message.
     */
    String a_warning_message()

    {
        return v_warning_message;

    }

    /**
     * Mutate the warning message by ADDING the specified message to the current
     * message; set the warning indicator to true.
     * <p/>
     * A comma will be added before the specified message if the message isn't
     * empty.
     */
    void m_warning_message(String p_exception_message)

    {
        if (v_warning_generated) {
            v_warning_message += ", ";
        }
        v_warning_generated = true;
        v_warning_message += p_exception_message;

    }

    /**
     * Perform any actions that are necessary when the OpMode is enabled.
     * <p/>
     * The system calls this member once when the OpMode is enabled.
     */
    @Override
    public void start()

    {

    }

    /**
     * Perform any actions that are necessary while the OpMode is running.
     * <p/>
     * The system calls this member repeatedly while the OpMode is running.
     */
    @Override
    public void loop() {
        set_drive_power(gamepad1);
        adjust_tape_measure(gamepad2.left_stick_y,gamepad2.right_stick_y, gamepad2.dpad_up, gamepad2.dpad_down);
        set_servos(gamepad2.a, gamepad2.b);
    }

        /**
     * Perform any actions that are necessary while the OpMode is stopped.
     * <p/>
     * The system calls this member repeatedly when the OpMode ends.
     */

    @Override
    public void stop() {
        //
        // Nothing needs to be done for this method.
        //

    } // stop

    //CUSTOM METHODS BELOW THIS POINT

    void set_drive_power(Gamepad sticks)
    {
        double turn = sticks.right_stick_x;
        double speed = sticks.left_stick_y;
        boolean leftisbig = (turn <= 0);
        double bigTurn = speed;
        double smallTurn = speed * (1-Math.abs(turn));
        double leftPower, rightPower;
        if(leftisbig)  {
            leftPower = bigTurn;
            rightPower = smallTurn;
        }else{
            leftPower = smallTurn;
            rightPower = bigTurn;
        }
        if (leftFront != null) {
            leftFront.setPower(leftPower);
        }
        if (rightFront != null) {
            rightFront.setPower(rightPower);
        }
        if (leftBack != null) {
            leftBack.setPower(leftPower);
        }
        if (rightBack != null) {
            rightBack.setPower(rightPower);
        }

    }

    void adjust_tape_measure(double angle, double winchPower, boolean measureUp, boolean measureDown)
    {
        tapeMeasureAngle.setPower(angle * 0.3);
        winchLength.setPower(winchPower);

        if (measureUp) {
            tapeMeasureLength.setPower(1.0);
        }
        else if (measureDown){
            tapeMeasureLength.setPower(-1.0);
        }
        else{
            tapeMeasureLength.setPower(0);
        }

    }

    void set_servos(boolean red, boolean blue){
        if (red){
            if (redToggle.getPosition() == 0) {
                redToggle.setPosition(0.5);
                telemetry.addData("Text","Red Servo Deployed");
            }
            else {
                redToggle.setPosition(0);
                telemetry.addData("Text","REd Servo Retracted");

            }
        }
        if (blue){
            if (blueToggle.getPosition() == 0) {
                blueToggle.setPosition(0.5);
                telemetry.addData("Text", "Blue Servo Deployed");
            }
            else {
                blueToggle.setPosition(0);
                telemetry.addData("Text","Blue Servo Retracted");
            }
        }
    }
}



