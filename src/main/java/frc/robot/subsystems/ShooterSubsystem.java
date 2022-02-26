// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Timer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

  private final CANSparkMax leftShooterMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  private final CANSparkMax rightShooterMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  private final MotorControllerGroup shooter = new MotorControllerGroup(rightShooterMotor, leftShooterMotor);

  private final Timer shooterTimer = new Timer();

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void RunShooter(){

    leftShooterMotor.setInverted(true);
    rightShooterMotor.setInverted(false);
    shooter.setVoltage(Constants.shooterVolts);
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
