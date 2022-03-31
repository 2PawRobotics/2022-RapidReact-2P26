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
  private final SlewRateLimiter slewRateLimiter = new SlewRateLimiter(Constants.RampRateLimit);


  //private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void ArcadeDrive(XboxController XCont, double speedX, double speedY, double RspeedY/*, double XContY*/){

    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, slewRateLimiter.calculate(XCont.getLeftY())*speedY);
  }

  public void FullDrive(XboxController XCont, double speedX, double RspeedY){
    
    arcadeDrive.arcadeDrive(XCont.getRightX()*speedX, XCont.getRightY());
  }

//Autonomous Drive Timer & Encoder
  public void DriveinitTimer() {
    driveTimer.reset();
    driveTimer.start();
    LEncoder.reset();
    REncoder.reset();
  }

//Autonomous Drive Paths
  public void AutonDrive(int AutonPath){

    LEncoder.setDistancePerPulse(1./76.);
    REncoder.setDistancePerPulse(1./76.);
    Constants.LEncoderCorrection = LEncoder.getDistance() + 20;

//Autonomous Path 1
  if(AutonPath == 1){
    if(driveTimer.get() <= 4){
      if(REncoder.getDistance() < 20){
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 20){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    if(driveTimer.get() >= 5){
      if(REncoder.getDistance() > 18){
        arcadeDrive.tankDrive(.6, -.65);
      }
      if(REncoder.getDistance() <= 18){
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

//Autonomous Path 2
  if(AutonPath == 2){
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

//Autonomous Path 3
  if(AutonPath == 3){
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

//Autonomous Path 4
  if(AutonPath == 4){
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

//Autonomous Path 5
  if(AutonPath == 5){
    //Forward to first Cargo
    if(driveTimer.get() < 2){
      if(REncoder.getDistance() < 3){
        arcadeDrive.tankDrive(-.6, .6);
      }
      else if(REncoder.getDistance() < 10){
        arcadeDrive.tankDrive(-.8, .8);
      }
      else if (REncoder.getDistance() < 12){
        arcadeDrive.tankDrive(-.6, .6);
      }
      if(REncoder.getDistance() >= 12){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    //Backwards w/ Slight Angle to Align With Scoring Hub to Shoot Cargo
    if(driveTimer.get() > 3 && driveTimer.get() < 4.5){
      if(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.65, -.6);
      }
      if(REncoder.getDistance() <= 8){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    //Forward S-Curve Towards Human Player Station, grab 3rd & 4th Cargo
    if(driveTimer.get() > 6 && driveTimer.get() < 9){
      if(REncoder.getDistance() < 10){
        arcadeDrive.tankDrive(-.67, .65);
      }
      else if(REncoder.getDistance() < 25){
        arcadeDrive.tankDrive(-.87, .85);
      }
      else if(REncoder.getDistance() < 28){
        arcadeDrive.tankDrive(-.53, 6);
      }
      else if (REncoder.getDistance() > 28){
        arcadeDrive.tankDrive(0, 0);
      }
    }
    //Backwards S-Curve Towards Human Player Station, Shoot 3rd & 4th Cargo
    if(driveTimer.get() > 10.5 && driveTimer.get() < 13.5){
      if(REncoder.getDistance() > 25){
        arcadeDrive.tankDrive(6, -.53);
      }
      else if(REncoder.getDistance() > 10){
        arcadeDrive.tankDrive(.85, -.87);
      }
      else if(REncoder.getDistance() > 8){
        arcadeDrive.tankDrive(.65, -.67);
      }
      if(REncoder.getDistance() <= 8){
        arcadeDrive.tankDrive(0, 0);
      }
    }
  }

}

   public void resetGyro(){
     //navXGyro.reset();
   }

}
