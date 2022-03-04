// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmSubsystem extends SubsystemBase {

  //Declare Hardware Components
  private final CANSparkMax actuatorMotor = new CANSparkMax(Constants.actuatorport, MotorType.kBrushed);
  private final DigitalInput topLimitSwitch = new DigitalInput(Constants.topLimitSwitchPort);
  private final DigitalInput lowLimitSwitch = new DigitalInput(Constants.lowLimitSwitchPort);

  //--------------------------------------------------------------------------------------------//
  //Make Methods Here

  public void ArmAngleChange(
    int ButtonPort13, int ButtonPort14, Joystick ButtonPanel, 
    int zeroSpeed, double actuatorSpeed, double RactuatorSpeed){

    if(ButtonPanel.getRawButtonPressed(ButtonPort13) ||
       ButtonPanel.getRawButtonPressed(ButtonPort14)){

      if(topLimitSwitch.get()){
        actuatorMotor.set(zeroSpeed);
      }
        else{
          actuatorMotor.set(actuatorSpeed);
        }
    }
    else{
      if(lowLimitSwitch.get()){
        actuatorMotor.set(zeroSpeed);
      }
      else{
        actuatorMotor.set(RactuatorSpeed);
      }
    }
  }
}

