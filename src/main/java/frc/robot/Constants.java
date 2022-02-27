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

    //Drive Motors
    public static final int leftCimPort1 = 1;
    public static final int leftCimPort2 = 3;
    public static final int rightCimPort1 = 2;
    public static final int rightCimPort2 = 4;

    //Encoder Ports THIS IS NEW STUFF
    public static final int[] kLeftEncoderPorts = new int[] {0, 1};
    public static final int[] krightEncoderPorts = new int[] {2, 3};
    public static final boolean kLeftEncoderReversed = true;
    public static final boolean kRightEncoderReversed = true;

    //Other Encoder Stuff
    public static final int kEncoderCPR = 720;
    public static final double kWheelDiameterMeters = 0.15;
    public static final double kEncoderDistancePerPulse = (kWheelDiameterMeters * Math.PI) / (kEncoderCPR);

    //Other Motors
    public static final int actuatorport = 5;
    public static final int intakelowport = 6;
    public static final int intakehighport = 7;
    public static final int leftshooterport = 8;
    public static final int rightshooterport = 9;

    //Controllers 
    public static final int XContPort = 1;
    public static final int ButtonPanelPort = 0;

    //Drive Variables
    public static final double speedY = 0.83;
    public static final double speedX = -0.83;
    public static final double RspeedY = -0.83;
    public static final double RspeedX = 0.83;

    //Voltage Constants
    public static final int shooterVolts = 10;
    public static final int autonShooterVolts = 10;
    public static final int rShooterVolts = 3;
    public static final int bothIntakeVolts = 3;
    public static final int lIntakeVolts = 8;
    public static final int tIntakeVolts = 3;
    public static final int fastAngleVolts = 4;
    public static final int slowAngleVolts = 2;
    public static final int zeroAngleVolts = 0;

    //Button Panel Ports
    public static final int ButtonPort1 = 1;
    public static final int ButtonPort2 = 2;
    public static final int ButtonPort3 = 3;
    public static final int ButtonPort4 = 4;
    public static final int ButtonPort5 = 5;
    public static final int ButtonPort6 = 6;
    //public static final int ButtonPort7 = 7; These two are not wired on the button panel.
    //public static final int ButtonPort8 = 8;
    public static final int ButtonPort9 = 9;
    public static final int ButtonPort10 = 10;
    public static final int ButtonPort11 = 11;
    public static final int ButtonPort12 = 12;
    public static final int ButtonPort13 = 13;
    public static final int ButtonPort14 = 14;

    //Auton Path Value
    public static boolean AutonPath1 = false;
    public static boolean AutonPath2 = false;
    public static boolean AutonPath3 = true;
    public static boolean AutonPath4 = false;
}