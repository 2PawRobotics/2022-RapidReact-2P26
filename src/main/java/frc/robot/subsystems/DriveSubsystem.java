// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Encoder;
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

  private final Encoder LEncoder = new Encoder(0,1, true, Encoder.EncodingType.k2X);
  private final Encoder REncoder = new Encoder(2,3, true, Encoder.EncodingType.k2X);

  private final Timer driveTimer = new Timer();
  private final SlewRateLimiter slewRateLimiter = new SlewRateLimiter(Constants.rateLimit);


  //private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ArcadeDrive(XboxController XCont, double speedX, double speedY, double RspeedY/*, double XContY*/){

    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, slewRateLimiter.calculate(XCont.getLeftY()));
    System.out.println(LEncoder.getRate() + "Left Encoder");
    System.out.println(REncoder.getRate() + "Right Encoder");
    //System.out.println(XContY);
    //System.out.println("left: ");
    //System.out.print(leftCims.get());
    //System.out.println("right: ");
    //System.out.print(rightCims.get());
  }

  public void ReverseDrive(XboxController XCont, double speedX, double RspeedY){
    while(RobotContainer.XCont.getRightBumperPressed()){
      arcadeDrive.arcadeDrive(speedX, RspeedY);
      Constants.reverseDrive = true;
    }
  }

  public void AutonDrive(int AutonPath){

    LEncoder.setDistancePerPulse(1./76.);
    REncoder.setDistancePerPulse(1./76.);
    Constants.LEncoderCorrection = LEncoder.getDistance() + 20;


//Low Port Auton 5
    if(AutonPath == 1){
      if(driveTimer.get() >= 1.5 && driveTimer.get() < 3){
        System.out.println("Thank you BlitzCreek");
        arcadeDrive.tankDrive(-0.68, 0.68);
      }
      if(driveTimer.get() >= 3){
        arcadeDrive.tankDrive(0, 0);
      } 

    }
    if(AutonPath == 2){
      if(driveTimer.get() >= 1.5 && driveTimer.get()< 3){
        arcadeDrive.tankDrive(-0.68, 0.68);
      }
      if(driveTimer.get() >3 && driveTimer.get() <4.5){
        arcadeDrive.tankDrive(0, 0);
      }
      if(driveTimer.get() >= 4.5 && driveTimer.get() <=5.5){
        arcadeDrive.tankDrive(0.48, -0.48);
      }
      if(driveTimer.get() >5.5 ){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    if(AutonPath == 3){
      System.out.println(LEncoder.getDistance() + "Left Encoder");
      System.out.println(REncoder.getDistance() + "Right Encoder");
      if(LEncoder.getDistance() < 60 && REncoder.getDistance() < 60){
        if(LEncoder.getDistance() < REncoder.getDistance()){
          arcadeDrive.tankDrive(-.5, .495);
        }else if (LEncoder.getDistance() > REncoder.getDistance()){
          arcadeDrive.tankDrive(-.495, .5);
        }else{
          arcadeDrive.tankDrive(.5, .5);
        }
      }
      if(LEncoder.getDistance() >= 60 && REncoder.getDistance() >= 60){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.start();
      }
      if(driveTimer.get() >= 3){
        if(LEncoder.getDistance() > 45 && REncoder.getDistance() > 45){
          if(LEncoder.getDistance() > REncoder.getDistance()){
            arcadeDrive.tankDrive(-.5, .495);
          }else if (LEncoder.getDistance() < REncoder.getDistance()){
            arcadeDrive.tankDrive(-.495, .5);
          }else{
            arcadeDrive.tankDrive(.5, .5);
          }
        }
        if(LEncoder.getDistance() <= 45 && REncoder.getDistance() <= 45){
          arcadeDrive.tankDrive(0, 0);
        }
      }
    }
    if(AutonPath == 4){
      System.out.println(LEncoder.getDistance() + "Left Encoder");
      System.out.println(REncoder.getDistance() + "Right Encoder");
      if(LEncoder.getDistance() < 5 && REncoder.getDistance() < 5){
        if(LEncoder.getDistance() < REncoder.getDistance()){
          arcadeDrive.tankDrive(-.5, .495);
        }else if (LEncoder.getDistance() > REncoder.getDistance()){
          arcadeDrive.tankDrive(-.495, .5);
        }else{
          arcadeDrive.tankDrive(.5, .5);
        }
      }
      if(LEncoder.getDistance() >= 5 && REncoder.getDistance() >= 5 && LEncoder.getDistance() < 15 && REncoder.getDistance() < 35){
        arcadeDrive.tankDrive(.3, .5);
        //driveTimer.start();
      }
      if(LEncoder.getDistance() >= 15 && REncoder.getDistance() >= 35 && LEncoder.getDistance() < 30 && REncoder.getDistance() < 50){
        if(Constants.LEncoderCorrection < REncoder.getDistance()){
          arcadeDrive.tankDrive(-.5, .495);
        }else if (Constants.LEncoderCorrection > REncoder.getDistance()){
          arcadeDrive.tankDrive(-.495, .5);
        }else{
          arcadeDrive.tankDrive(.5, .5);
        }
      }
      if(LEncoder.getDistance() >= 30 && REncoder.getDistance() >= 50){
      }
    }
    arcadeDrive.feed();
}
public void DriveinitTimer() {
  driveTimer.reset();
  LEncoder.reset();
  REncoder.reset();
}

   public void resetGyro(){
     //navXGyro.reset();
   }
}
