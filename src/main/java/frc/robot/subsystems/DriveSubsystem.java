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

    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, slewRateLimiter.calculate(XCont.getLeftY()*speedY));
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

//Low Port Auton 5
    if(AutonPath == 5){
      if(driveTimer.get() >= 1.5 && driveTimer.get() < 3){
        System.out.println("Thank you BlitzCreek");
        arcadeDrive.tankDrive(-0.68, 0.68);
      }
      if(driveTimer.get() >= 3){
        arcadeDrive.tankDrive(0, 0);
      } 

    }
    if(AutonPath == 6){
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
    if(AutonPath == 7){
      System.out.println(LEncoder.getDistance() + "Left Encoder");
      System.out.println(REncoder.getDistance() + "Right Encoder");
      if(LEncoder.getDistance() < 110 && REncoder.getDistance() < 110){
        arcadeDrive.tankDrive(-.5, .5);
      }
      if(LEncoder.getDistance() >= 110 && REncoder.getDistance() >= 110){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    arcadeDrive.feed();
}
public void DriveinitTimer() {
  driveTimer.reset();
  driveTimer.start();
  LEncoder.reset();
  REncoder.reset();
}

   public void resetGyro(){
     //navXGyro.reset();
   }
}
