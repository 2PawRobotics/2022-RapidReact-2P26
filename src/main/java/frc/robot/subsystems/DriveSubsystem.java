// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;
//import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SerialPort;


public class DriveSubsystem extends SubsystemBase {

  //Declare Hardware Components

  private final CANSparkMax leftCim1 = new CANSparkMax(Constants.leftCimPort1, MotorType.kBrushed);
  private final CANSparkMax leftCim2 = new CANSparkMax(Constants.leftCimPort2, MotorType.kBrushed);
  private final CANSparkMax rightCim1 = new CANSparkMax(Constants.rightCimPort1, MotorType.kBrushed);
  private final CANSparkMax rightCim2 = new CANSparkMax(Constants.rightCimPort2, MotorType.kBrushed);
  private final MotorControllerGroup leftCims = new MotorControllerGroup(leftCim1, leftCim2);
  private final MotorControllerGroup rightCims = new MotorControllerGroup(rightCim1, rightCim2);

  private final DifferentialDrive arcadeDrive = new DifferentialDrive(leftCims, rightCims);

  private final Timer driveTimer = new Timer();

  //private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ArcadeDrive(XboxController XCont, double speedX, double speedY, double RspeedX, double RspeedY){
    arcadeDrive.arcadeDrive(RobotContainer.XCont.getRightX()*speedX, RobotContainer.XCont.getLeftY()*speedY);
  }

  public void ReverseDrive(XboxController XCont, double speedX, double RspeedY){
    while(RobotContainer.XCont.getRightBumperPressed()){
      arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, XCont.getLeftY()*RspeedY);
      Constants.reverseDrive = true;
    }
  }

  public void AutonDrive(int AutonPath){
    driveTimer.reset();
    driveTimer.start();

//Auton Path 1 and 2
    if(AutonPath == 1 || AutonPath == 2){
      while(driveTimer.get() < .5){
        arcadeDrive.tankDrive(.3, .0);
      }
      while(driveTimer.get() >= .5 && driveTimer.get() < 1.5){
        arcadeDrive.tankDrive(.5, .5);
      }
      if(driveTimer.get() >= 1.5){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.stop();
      }
    }
//Auton Path 3
    if(AutonPath == 3){
      while(driveTimer.get() < 1){
        arcadeDrive.tankDrive(.6, .6);
      }
      if(driveTimer.get() >= 1){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.stop();
      }
    }
//Auton Path 4
    if(AutonPath == 4){
      while(driveTimer.get() < .5){
        arcadeDrive.tankDrive(.0, .3);
      }
      while(driveTimer.get() >= .5 && driveTimer.get() < 1.5){
        arcadeDrive.tankDrive(.6, .6);
      }
      if(driveTimer.get() >= 1.5){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.stop();
      } 
    }
}

   public void resetGyro(){
     //navXGyro.reset();
   }
}
