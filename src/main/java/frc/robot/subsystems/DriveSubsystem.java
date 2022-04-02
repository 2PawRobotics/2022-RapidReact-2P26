// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;
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

  private final Encoder LEncoder = new Encoder(0,1, true, Encoder.EncodingType.k2X);
  private final Encoder REncoder = new Encoder(2,3, true, Encoder.EncodingType.k2X);

  private final Timer driveTimer = new Timer();
  private final SlewRateLimiter slewRateLimiter = new SlewRateLimiter(Constants.RampRateLimit);

  private ShuffleboardTab tab = Shuffleboard.getTab("AutonPath");
  private NetworkTableEntry AutonPathChoice = tab.add("AutonPath", 1).getEntry();

  private int AutonSection = 1;
  private double TimeCheck = 0;


  //private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ArcadeDrive(XboxController XCont, double speedX, double speedY, double RspeedY/*, double XContY*/){

    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, slewRateLimiter.calculate(XCont.getLeftY())*speedY);
  }

  public void FullDrive(XboxController XCont, double speedX, double RspeedY){
    
    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, XCont.getLeftY());
  }

//Autonomous Drive Timer & Encoder
  public void DriveinitTimer() {
    driveTimer.reset();
    driveTimer.start();
    LEncoder.reset();
    REncoder.reset();
  }

//Autonomous Drive Paths
  public void AutonDrive(){
    double AutonPath = AutonPathChoice.getDouble(1);

    LEncoder.setDistancePerPulse(1./76.);
    REncoder.setDistancePerPulse(1./76.);
    Constants.LEncoderCorrection = LEncoder.getDistance() + 20;
 
//Autonomous Path 1
  if(AutonPath == 1){
    if(driveTimer.get() <= 3){
      if(REncoder.getDistance() < 10.7){ 
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 10.7){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    if(driveTimer.get() >= 4){
      if(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.6, -.6);
      }
      if(REncoder.getDistance() <= 8){
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

//Autonomous Path 2
  if(AutonPath == 2){
    if(driveTimer.get() <= 3.5){
      if(REncoder.getDistance() < 14){
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() > 14){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    if(driveTimer.get() >= 4){
      if(LEncoder.getDistance() > 9){
        arcadeDrive.tankDrive(.64, -.6);
      }
      if(LEncoder.getDistance() <= 9){
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

//Autonomous Path 3
  if(AutonPath == 3){
    if(driveTimer.get() <= 4){
      if(REncoder.getDistance() < 8){
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    if(driveTimer.get() >= 8){
      if(LEncoder.getDistance() <= 18){
        arcadeDrive.tankDrive(-.6, 0.7);
      }
      if(LEncoder.getDistance() >= 18){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    
  }

//Autonomous Path 4
  if(AutonPath == 4){
    //Forward to first Cargo
    if(AutonSection == 1){
      while(REncoder.getDistance() < 3){
        arcadeDrive.tankDrive(-.6, .6);
      }
      while(REncoder.getDistance() < 10){
        arcadeDrive.tankDrive(-.8, .8);
      }
      while(REncoder.getDistance() < 12){
        arcadeDrive.tankDrive(-.6, .6);
      }
      arcadeDrive.tankDrive(0, 0);
      AutonSection = 2;
      TimeCheck = driveTimer.get();
    }
    //Backwards w/ Slight Angle to Align With Scoring Hub to Shoot Cargo
    if(AutonSection == 2 && driveTimer.get() >= TimeCheck + 1){//3-4.5
      while(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.65, -.6);
      }
      arcadeDrive.tankDrive(0, 0);
      AutonSection = 3;
      TimeCheck = driveTimer.get();
    }
    //Forward S-Curve Towards Human Player Station, grab 3rd & 4th Cargo
    if(AutonSection == 3 && driveTimer.get() > TimeCheck + 1.5){//6-9
      while(REncoder.getDistance() < 10){
        arcadeDrive.tankDrive(-.67, .65);
      }
      while(REncoder.getDistance() < 25){
        arcadeDrive.tankDrive(-.87, .85);
      }
      while(REncoder.getDistance() < 28){
        arcadeDrive.tankDrive(-.53, 6);
      }
      arcadeDrive.tankDrive(0, 0);
      AutonSection = 4;
      TimeCheck = driveTimer.get();
    }
    //Backwards S-Curve Towards Human Player Station, Shoot 3rd & 4th Cargo
    if(AutonSection == 4 && driveTimer.get() > TimeCheck + 1.5){//10.5-13.5
      while(REncoder.getDistance() > 25){
        arcadeDrive.tankDrive(6, -.53);
      }
      while(REncoder.getDistance() > 10){
        arcadeDrive.tankDrive(.85, -.87);
      }
      while(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.65, -.67);
      }
        arcadeDrive.tankDrive(0, 0);
    }
  }

}

   public void resetGyro(){
     //navXGyro.reset();
   }

}
