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

  public final static CANSparkMax leftShooterMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  public final static CANSparkMax rightShooterMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  public final static MotorControllerGroup shooter = new MotorControllerGroup(rightShooterMotor, leftShooterMotor);

  private final Timer shooterTimer = new Timer();

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void RunShooter(){


    leftShooterMotor.setInverted(true);
    rightShooterMotor.setInverted(false);
    shooter.setVoltage(Constants.shooterVolts);
    RobotContainer.XCont.setRumble(RumbleType.kLeftRumble, 0.5);
    RobotContainer.XCont.setRumble(RumbleType.kRightRumble, 0.5);
    System.out.println(shooter.get());

  }

  public void ReverseShooter(){

    leftShooterMotor.setInverted(false);
    rightShooterMotor.setInverted(true);
    shooter.setVoltage(Constants.rShooterVolts);

  }

  public void AutonShooter(){

    shooterTimer.reset();
    shooterTimer.start();

    while(shooterTimer.get() >= 1.75 && shooterTimer.get() < 5.5){
      leftShooterMotor.setInverted(true);
      rightShooterMotor.setInverted(false);
      shooter.setVoltage(Constants.shooterVolts);}
  }
}
