// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ShooterSubsystem extends SubsystemBase {

  //Declare Hardware Components
  public final static CANSparkMax leftShooterMotor = new CANSparkMax(Constants.leftshooterport, MotorType.kBrushed);
  public final static CANSparkMax rightShooterMotor = new CANSparkMax(Constants.rightshooterport, MotorType.kBrushed);
  public final static MotorControllerGroup shooter = new MotorControllerGroup(rightShooterMotor, leftShooterMotor);

  private final Timer shooterTimer = new Timer();

  private ShuffleboardTab tab = Shuffleboard.getTab("AutonPath");
  private NetworkTableEntry AutonPathChoice = tab.add("AutonPath", 2).getEntry();

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
    shooter.setVoltage(Constants.lshooterVolts);
    RobotContainer.XCont.setRumble(RumbleType.kLeftRumble, .5);
    RobotContainer.XCont.setRumble(RumbleType.kRightRumble, .5);
  }

  public void ReverseShooter(){

    leftShooterMotor.setInverted(true);
    rightShooterMotor.setInverted(false);
    shooter.setVoltage(Constants.RshooterVolts);
  }

  
//Autonomous Shooter Timer
public void ShooterinitTimer() {
  shooterTimer.reset();
  shooterTimer.start();
}

//Autonomous Shooter Paths
public void AutonShooter(double shooterVolts){
  double AutonPath = AutonPathChoice.getDouble(2.0);
  leftShooterMotor.setInverted(false);
  rightShooterMotor.setInverted(true);

//Autonomous Path 1
  if(AutonPath == 1){
    if(shooterTimer.get() >= 5.5 && shooterTimer.get() <= 8){
      shooter.setVoltage(shooterVolts);
    }
    if(shooterTimer.get() >= 8){
      shooter.setVoltage(0);
    }
  }

//Autonomous Path 2
  if(AutonPath == 2){
    if(shooterTimer.get() >= 6.5 && shooterTimer.get() <= 8.5){
      shooter.setVoltage(shooterVolts);
    }
    if(shooterTimer.get() >= 8.5){
      shooter.setVoltage(0);
    }
  }

//Autonomous Path 3
  if(AutonPath == 3){
    if(shooterTimer.get() >= 5.5 && shooterTimer.get() <= 7.5){
      shooter.setVoltage(shooterVolts);
    }
    if(shooterTimer.get() >= 7.5){
      shooter.setVoltage(0);
    }
  }

//Autonomous Path 4
  if(AutonPath == 4){
    //Shoot 1st & 2nd Cargo
    if(shooterTimer.get() > 4 && shooterTimer.get() < 6.5){
      shooter.setVoltage(shooterVolts);
    }
    else {
      shooter.setVoltage(0);
    }
    //Shoot 3rd & 4th Cargo
    if(shooterTimer.get() > 13){
      shooter.setVoltage(shooterVolts);
    }
  }
}

}
