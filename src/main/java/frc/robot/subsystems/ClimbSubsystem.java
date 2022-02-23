// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbSubsystem extends SubsystemBase {

  private final CANSparkMax actuatorMotor = new CANSparkMax(Constants.actuatorport, MotorType.kBrushed);
  private final AnalogPotentiometer pot = new AnalogPotentiometer(0, 500, 0);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // This method will be called once per scheduler run
  public void Readpot(){

    System.out.println(pot.get());

  }
}
