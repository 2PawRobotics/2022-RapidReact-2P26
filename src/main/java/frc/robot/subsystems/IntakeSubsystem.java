// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  private final CANSparkMax lowIntakeMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  private final CANSparkMax topIntakeMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  private final MotorControllerGroup intakeMotors = new MotorControllerGroup(lowIntakeMotor, topIntakeMotor);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void RunBothIntake() {
    intakeMotors.setVoltage(Constants.bothIntakeVolts);
  }
  public void RunLowIntake() {
    lowIntakeMotor.setVoltage(Constants.lIntakeVolts);
  }
  public void RunTopIntake() {
    topIntakeMotor.setVoltage(Constants.tIntakeVolts);
  }
}
