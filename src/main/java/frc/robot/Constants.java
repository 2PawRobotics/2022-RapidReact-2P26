// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Motors
        //Drive Motors
        public static final int leftCimPort1 = 1;
        public static final int leftCimPort2 = 3;
        public static final int rightCimPort1 = 2;
        public static final int rightCimPort2 = 4;
        //Other Motors
        public static final int actuatorport = 5;
        public static final int intakelowport = 6;
        public static final int intakehighport = 7;
        public static final int leftshooterport = 8;
        public static final int rightshooterport = 9;

    //Encoder Stuff
        //Encoder Ports THIS IS NEW STUFF
        public static final int[] kLeftEncoderPorts = new int[] {0, 1};
        public static final int[] krightEncoderPorts = new int[] {2, 3};
        public static final boolean kLeftEncoderReversed = true;
        public static final boolean kRightEncoderReversed = true;
        //Other Encoder Stuff
        public static final int kEncoderCPR = 720;
        public static final double kWheelDiameterMeters = 0.15;
        public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (kEncoderCPR);

    //Controllers and Various Ports
        //Controllers 
        public static final int XContPort = 1;
        public static final int ButtonPanelPort = 0;
        //Button Panel Ports
        public static final int ButtonPort1 = 1;
        public static final int ButtonPort2 = 2;
        public static final int ButtonPort3 = 3;
        public static final int ButtonPort4 = 4;
        public static final int ButtonPort5 = 5;
        public static final int ButtonPort6 = 6;
        public static final int ButtonPort9 = 9;
        public static final int ButtonPort10 = 10;
        public static final int ButtonPort11 = 11;
        public static final int ButtonPort12 = 12;
        public static final int ButtonPort13 = 13;
        public static final int ButtonPort14 = 14;
        public static final int RightBumper = 6;
        //DIO Ports
        public static final int topLimitSwitchPort = 4;
        public static final int lowLimitSwitchPort = 5;

    //Speeds and Voltages
        //Drive Speeds
        public static final double speedY = 0.83;
        public static final double speedX = -0.78;
        public static final double RspeedY = -0.83;
        public static final double rateLimit = 2.0;
       // public static final double speedX = RobotContainer.XCont.getRightX();
        //public static final double speedY = Math.pow(RobotContainer.XCont.getRightX(), 2)*0.83;
        //public static final double speedX = Math.pow(RobotContainer.XCont.getLeftY(), 2)*-0.83;
        //public static final double RspeedY = Math.pow(RobotContainer.XCont.getRightX(), 2)*-0.83;
        //Speed Constants
        public static final double actuatorSpeed = 0.6;
        public static final double RactuatorSpeed = -0.6;
        public static final int zeroSpeed = 0;
        //Voltage Constants
        public static final double shooterVolts = 10.5;
        public static final double HiAutoshooterVolts = 10;
        public static final int lowshooterVolts = 6;
        public static final int rShooterVolts = 4;
        public static final int autonShooterVolts = 10;
        public static final double bothIntakeVolts = 5.0;
        public static final double AutobothIntakeVolts = 4.0;
        public static final int lIntakeVolts = 6;
        public static final int tIntakeVolts = 3;
        public static final int AutoshooterVolts = 6;
    
    //Auton Paths
        public static int AutonPath = 2;

    //Command Booleans
        public static boolean intakeActive = false;
        public static boolean reverseIntakeActive = false;
        public static boolean reverseDrive = false;

    //Encoder Motor Correction
        public static double LEncoderCorrection = 0;
        //public static double LEncoderCorrection = .5;

}