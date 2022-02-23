// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ClimbSubsystem extends SubsystemBase {

  //Declare Hardware Components
  private final CANSparkMax actuatorMotor = new CANSparkMax(Constants.actuatorport, MotorType.kBrushed);
  private final AnalogPotentiometer pot = new AnalogPotentiometer(0, 500, 0);

  private final DoubleSolenoid dSolenoid1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);
  private final DoubleSolenoid dSolenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 3, 4);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  // This method will be called once per scheduler run
  public void Readpot(){

    System.out.println(pot.get());

  }

  public void SolenoidControl(){
    if(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort5)){
      dSolenoid1.set(Value.kForward);
      dSolenoid2.set(Value.kForward);
    }
    if(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort6)){
      dSolenoid1.set(Value.kReverse);
      dSolenoid2.set(Value.kReverse);
    }
  }
}
