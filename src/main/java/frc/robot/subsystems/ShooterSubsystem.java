// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {

  //Declare Hardware Components
  public final static CANSparkMax leftShooterMotor = new CANSparkMax(Constants.leftshooterport, MotorType.kBrushed);
  public final static CANSparkMax rightShooterMotor = new CANSparkMax(Constants.rightshooterport, MotorType.kBrushed);
  public final static MotorControllerGroup shooter = new MotorControllerGroup(rightShooterMotor, leftShooterMotor);

  private final Timer shooterTimer = new Timer();

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ShooterOff(){

    shooter.setVoltage(0);
    leftShooterMotor.setVoltage(0);
    rightShooterMotor.setVoltage(0);
    RobotContainer.XCont.setRumble(RumbleType.kLeftRumble, 0);
    RobotContainer.XCont.setRumble(RumbleType.kRightRumble, 0);
  }

  public void RunShooter(){

    leftShooterMotor.setInverted(false);
    rightShooterMotor.setInverted(true);
    shooter.setVoltage(Constants.shooterVolts);
    RobotContainer.XCont.setRumble(RumbleType.kLeftRumble, 1);
    RobotContainer.XCont.setRumble(RumbleType.kRightRumble, 1);
    System.out.println(shooter.get());
  }

  public void LowRunShooter(){

    leftShooterMotor.setInverted(false);
    rightShooterMotor.setInverted(true);
    shooter.setVoltage(Constants.lowshooterVolts);
    RobotContainer.XCont.setRumble(RumbleType.kLeftRumble, .5);
    RobotContainer.XCont.setRumble(RumbleType.kRightRumble, .5);
  }

  public void ReverseShooter(){

    leftShooterMotor.setInverted(true);
    rightShooterMotor.setInverted(false);
    shooter.setVoltage(Constants.rShooterVolts);
  }

  
//Auton Shooter
  public void AutonShooter(int AutonPath){
    System.out.println(shooterTimer.get());


//Auton Path 1, 2, 3, and 4

//Low Port Auton 5
    if(AutonPath == 1){
      if(shooterTimer.get() >= 0 && shooterTimer.get() < 1.5){
        leftShooterMotor.setInverted(false);
        rightShooterMotor.setInverted(true);
        shooter.setVoltage(Constants.AutoshooterVolts);}
      if(shooterTimer.get() >= 1.5){
        shooter.setVoltage(0);}
    }
    if(AutonPath == 2){
      if(shooterTimer.get() >= 0 && shooterTimer.get() <= 1.8){
        leftShooterMotor.setInverted(false);
        rightShooterMotor.setInverted(true);
        shooter.setVoltage(Constants.AutoshooterVolts);
      }
      if(shooterTimer.get() >= 1.8 && shooterTimer.get() <4.7){
        shooter.setVoltage(0);
      }
      if(shooterTimer.get() >= 4.7 && shooterTimer.get() <= 7){
        shooter.setVoltage(Constants.shooterVolts);
      }
      if(shooterTimer.get() >= 7){
        shooter.setVoltage(0);
      }
    }
  }

  public void ShooterinitTimer() {
    shooterTimer.reset();
    shooterTimer.start();
  }
}
