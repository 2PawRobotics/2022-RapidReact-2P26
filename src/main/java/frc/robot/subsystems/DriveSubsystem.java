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
import com.kauailabs.navx.frc.AHRS;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort;


public class DriveSubsystem extends SubsystemBase {
 
  private final CANSparkMax leftCim1 = new CANSparkMax(Constants.leftCimPort1, MotorType.kBrushed);
  private final CANSparkMax leftCim2 = new CANSparkMax(Constants.leftCimPort2, MotorType.kBrushed);
  private final CANSparkMax rightCim1 = new CANSparkMax(Constants.rightCimPort1, MotorType.kBrushed);
  private final CANSparkMax rightCim2 = new CANSparkMax(Constants.rightCimPort2, MotorType.kBrushed);

  private final MotorControllerGroup leftCims = new MotorControllerGroup(leftCim1, leftCim2);
  private final MotorControllerGroup rightCims = new MotorControllerGroup(rightCim1, rightCim2);

  private final DifferentialDrive arcadeDrive = new DifferentialDrive(leftCims, rightCims);

  private final Timer driveTimer = new Timer();

  private AHRS navXGyro = new AHRS(SerialPort.Port.kUSB);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void ArcadeDrive(XboxController controller, double speedX, double speedY, double RspeedX, double RspeedY){
    while(RobotContainer.XCont.getRightBumperPressed()){
      arcadeDrive.arcadeDrive(RobotContainer.XCont.getRightX()*RspeedX, RobotContainer.XCont.getLeftY()*RspeedY);}
      Constants.reverseDrive = true;

    arcadeDrive.arcadeDrive(RobotContainer.XCont.getRightX()*speedX, RobotContainer.XCont.getLeftY()*speedY);
  }
  public void AutonDrive(){

  
    driveTimer.reset();
    driveTimer.start();

//Auton Path 1
    if(Constants.AutonPath1 = true){
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
//Auton Path 2
    if(Constants.AutonPath2 = true){
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
    if(Constants.AutonPath3 = true){
      while(driveTimer.get() < 1){
        arcadeDrive.tankDrive(.5, .5);
      }
      if(driveTimer.get() >= 1){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.stop();
      }
    }
//Auton Path 4
    if(Constants.AutonPath4 = true){
      while(driveTimer.get() < .5){
        arcadeDrive.tankDrive(.0, .3);
      }
      while(driveTimer.get() >= .5 && driveTimer.get() < 1.5){
        arcadeDrive.tankDrive(.5, .5);
      }
      if(driveTimer.get() >= 1.5){
        arcadeDrive.tankDrive(0, 0);
        driveTimer.stop();
      } 
    }
}

   public void resetGyro(){
     navXGyro.reset();
   }
}
