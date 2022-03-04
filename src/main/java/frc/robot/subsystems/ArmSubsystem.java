// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ArmSubsystem extends SubsystemBase {

  //Declare Hardware Components
  private final CANSparkMax actuatorMotor = new CANSparkMax(Constants.actuatorport, MotorType.kBrushed);
  private final DigitalInput topLimitSwitch = new DigitalInput(Constants.topLimitSwitchPort);
  private final DigitalInput lowLimitSwitch = new DigitalInput(Constants.lowLimitSwitchPort);
  private final Timer armTimer = new Timer();

  //--------------------------------------------------------------------------------------------//
  //Make Methods Here

  public void ArmChangeNull(){
    actuatorMotor.set(0);
    armTimer.reset();
  }

  public void ArmChangeDown(){
    if(lowLimitSwitch.get() == false){
      actuatorMotor.set(Constants.actuatorSpeed);
    }
    else{
      actuatorMotor.set(0);
    }
  }
  
  public void ArmChangeUp(){
    if(topLimitSwitch.get() == false){
      actuatorMotor.set(Constants.RactuatorSpeed);
    }
    else{
      actuatorMotor.set(0);
    }
  }
}

