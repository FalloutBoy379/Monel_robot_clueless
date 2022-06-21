package org.firstinspires.ftc.teamcode.TestCodes.Mechanisms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Extension Test with 2 motors", group="Linear OpMode")
public class Test_Extension_doubleMotor extends LinearOpMode{

    DcMotorEx motor1, motor2;
    Servo servo_dropper, servo_arm;
    int MOTOR1MAX = 600;
    int MOTOR2MAX = -600;

    int flag = 0;



    @Override
    public void runOpMode() throws InterruptedException {
    motor1 = hardwareMap.get(DcMotorEx.class, "motor1");
    motor2 = hardwareMap.get(DcMotorEx.class, "motor2");
    servo_arm = hardwareMap.get(Servo.class, "Arm");
    servo_dropper = hardwareMap.get(Servo.class, "Dropper");

    motor1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
    motor2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

    waitForStart();

    motor1.setTargetPosition(0);
    motor2.setTargetPosition(0);

    motor1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    motor2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

    motor1.setVelocity(500);
    motor2.setVelocity(500);

    while(opModeIsActive()){

        if(gamepad1.right_bumper){
            flag = 1;
        }
        if(gamepad1.left_bumper){
            flag = 2;
        }

        if(gamepad1.b){
            flag = 0;
        }

        if(flag == 0){
            motor1.setTargetPosition(0);
            motor2.setTargetPosition(0);
            motor1.setVelocity(0);
            motor2.setVelocity(0);
        }
        else if(flag == 1){
            motor1.setTargetPosition(MOTOR1MAX);
            motor2.setTargetPosition(MOTOR2MAX);
            motor1.setVelocity(900);
            motor2.setVelocity(900);
            telemetry.addData("Status", "Opening");
        }
        else if(flag == 2){
            motor1.setTargetPosition(0);
            motor2.setTargetPosition(0);
            motor1.setVelocity(900);
            motor2.setVelocity(900);
            telemetry.addData("Status", "Closing");
        }
        telemetry.addData("Motor 1: ", motor1.getCurrentPosition());
        telemetry.addData("Motor 2: ", motor2.getCurrentPosition());
        telemetry.update();
    }
    }
}
