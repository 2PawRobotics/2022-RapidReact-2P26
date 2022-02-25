// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class IntakeSubsystem extends SubsystemBase {

  private final CANSparkMax lowIntakeMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  private final CANSparkMax topIntakeMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  private final MotorControllerGroup intakeMotors = new MotorControllerGroup(lowIntakeMotor, topIntakeMotor);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void RunIntake() {
    while(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort4)){
      intakeMotors.setVoltage(Constants.bothIntakeVolts);}
    while(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort6)){
      lowIntakeMotor.setVoltage(Constants.lIntakeVolts);}
    while(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort5)){
      topIntakeMotor.setVoltage(Constants.tIntakeVolts);}
    while(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort10)){
        lowIntakeMotor.setInverted(true);
        lowIntakeMotor.setVoltage(Constants.tIntakeVolts);}
    while(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort11)){
        topIntakeMotor.setInverted(true);
        topIntakeMotor.setVoltage(Constants.tIntakeVolts);}
  }
}

