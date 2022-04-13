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

  private int AutonSection = 1;
  private double TimeCheck = 0;



  //private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ArcadeDrive(XboxController XCont, double speedX, double speedY, double RspeedY/*, double XContY*/){

    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, slewRateLimiter.calculate(XCont.getLeftY())*speedY);
    System.out.println(leftCims.get() + " Left");
    System.out.println(rightCims.get() + " Right");
  }

  public void FullDrive(XboxController XCont, double speedX, double RspeedY){
    
    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, XCont.getLeftY());
  }

//Autonomous Drive Timer & Encoder
  public void DriveinitTimer() {
    //leftCims.setInverted(true);
    //rightCims.setInverted(false);
    driveTimer.reset();
    driveTimer.start();
    LEncoder.reset();
    REncoder.reset();
    AutonSection = 1;
    TimeCheck = 0;
  }

//Autonomous Drive Paths
  public void AutonDrive(int AutonPath){
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
      if(LEncoder.getDistance() <= 14){
        arcadeDrive.tankDrive(-.6, 0.7);
      }
      if(LEncoder.getDistance() >= 18){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    
  }

//Autonomous Path 4
  if(AutonPath == 4){
    //37, 50.5, 52
    //Forward to first Cargo
    if(driveTimer.get() < 2.5){
      if(REncoder.getDistance() < 11){
        //for(int i=1; i<2; i++){
        //  leftCims.setVoltage(5);
        //  rightCims.setVoltage(5);
       // }
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 11){
        //leftCims.setVoltage(0);
        //rightCims.setVoltage(0);
        //leftCims.setInverted(false);
        //rightCims.setInverted(true);
        arcadeDrive.tankDrive(0, 0);
        
      }
      //AutonSection = 2;
      //TimeCheck = driveTimer.get();
    }
    
    //Backwards w/ Slight Angle to Align With Scoring Hub to Shoot Cargo
    if(driveTimer.get() > 3 && driveTimer.get() < 4.5){//3-4.5
      if(REncoder.getDistance() > 8){
        //for(int i=1; i<2; i++){
         // leftCims.setVoltage(6.5);
         // rightCims.setVoltage(5.);
        //}
        arcadeDrive.tankDrive(.6, -.5);
      }
      if(REncoder.getDistance() <= 8){
        //leftCims.setVoltage(0);
        //rightCims.setVoltage(0);
        //leftCims.setInverted(true);
        //rightCims.setInverted(false);
        arcadeDrive.tankDrive(0, 0);
        
      }
      //AutonSection = 3;
      //TimeCheck = driveTimer.get();
    }
    //Forward S-Curve Towards Human Player Station, grab 3rd & 4th Cargo
    if(driveTimer.get() > 6 && driveTimer.get() < 9){//6-9
      if(REncoder.getDistance() < 31){
        //for(int i=1; i<2; i++){
          //leftCims.setVoltage(11.5);
          //rightCims.setVoltage(8.);
        //}
        arcadeDrive.tankDrive(-.9, .7);
      }
      if(REncoder.getDistance() < 57. && REncoder.getDistance() > 31){
        //for(int i=1; i<2; i++){
        //  leftCims.setVoltage(4.5);
        //  rightCims.setVoltage(8.);
        //}
        arcadeDrive.tankDrive(-.555, .855);
      }
      if(REncoder.getDistance() >= 57.){
        //leftCims.setVoltage(0);
        //rightCims.setVoltage(0);
        //leftCims.setInverted(false);
        //rightCims.setInverted(true);
        arcadeDrive.tankDrive(0, 0);
        
      }
      //AutonSection = 4;
      //TimeCheck = driveTimer.get();
    }
    //Backwards S-Curve Towards Scoring Hub, Shoot 3rd & 4th Cargo
    if(driveTimer.get() > 11 && driveTimer.get() < 14){//10.5-13.5
      if(REncoder.getDistance() > 45){
        //for(int i=1; i<2; i++){
        //  leftCims.setVoltage(7.15);
        //  rightCims.setVoltage(10.753);
        //}
        arcadeDrive.tankDrive(.57, -.85);
      }
      if(REncoder.getDistance() > 20 && REncoder.getDistance() < 45){
        //for(int i=1; i<2; i++){
        //  leftCims.setVoltage(11.385);
        //  rightCims.setVoltage(8.412);
        //}
        arcadeDrive.tankDrive(.9, -.665);
      }
      if(REncoder.getDistance() > 13.6 && REncoder.getDistance() < 20){
        //for(int i=1; i<2; i++){
        //  leftCims.setVoltage(9.11);
        //  rightCims.setVoltage(7.211);
        //}
        arcadeDrive.tankDrive(.72, -.57);
      }
      if(REncoder.getDistance() <= 13.6){
        //leftCims.setInverted(false);
        //rightCims.setInverted(false);
        //leftCims.setVoltage(0);
        //rightCims.setVoltage(0);
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

// Auton Path 5
  if(AutonPath == 5){
    if(AutonSection == 1){//0-3
      while(REncoder.getDistance() < 10.7){ 
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 10.7){
        arcadeDrive.tankDrive(0, 0);
      }
      AutonSection = 2;
      TimeCheck = driveTimer.get();
    }
    if(AutonSection == 2 && driveTimer.get() >= TimeCheck+1){//4-5.5
      while(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.6, -.6);
      }
      if(REncoder.getDistance() <= 8){
        arcadeDrive.tankDrive(0, 0);
      }
      AutonSection = 3;
      TimeCheck = driveTimer.get();
    }
    if(AutonSection == 3 && driveTimer.get() >= TimeCheck+1.5){//7-10
      while(REncoder.getDistance() >= 6.5){
        arcadeDrive.tankDrive(-.6, -.6);
      }
      while(REncoder.getDistance() < 18){
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 18){
        arcadeDrive.tankDrive(0, 0);
      }
      AutonSection = 4;
      TimeCheck = driveTimer.get();
    }
    if(AutonSection == 4 && driveTimer.get() >= TimeCheck+1){//11-~
      while(REncoder.getDistance() > 12.8){
        arcadeDrive.tankDrive(.58, -.68);
      }
      if(REncoder.getDistance() <= 12.8){
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

//Auton Path 6
if(AutonPath == 6){
  if(driveTimer.get() <= 3){
    if(LEncoder.getDistance() < 10.7){ 
      arcadeDrive.tankDrive(-.6, .6);
    }
    if(LEncoder.getDistance() >= 10.7){
      arcadeDrive.tankDrive(0, 0);
    }
  }
  if(driveTimer.get() >= 4 && driveTimer.get() <= 5.5){
    if(LEncoder.getDistance() > 8){
      arcadeDrive.tankDrive(.6, -.6);
    }
    if(LEncoder.getDistance() <= 8){
      arcadeDrive.tankDrive(0, 0);
    }
  }
  if(driveTimer.get() >= 7 && driveTimer.get() <= 10){
    while(LEncoder.getDistance() >= 6.5){
      arcadeDrive.tankDrive(-.6, -.6);
    }
    while(LEncoder.getDistance() < 18){
      arcadeDrive.tankDrive(-.6, .6);
    }
    if(LEncoder.getDistance() >= 18){
      arcadeDrive.tankDrive(0, 0);
    }
  }
  if(driveTimer.get() >= 11){
    if(LEncoder.getDistance() > 12.8){
      arcadeDrive.tankDrive(.58, -.68);
    }
    if(LEncoder.getDistance() <= 12.8){
      arcadeDrive.tankDrive(0, 0);
    }
  }
}
}

   public void resetGyro(){
     //navXGyro.reset();
   }

}
