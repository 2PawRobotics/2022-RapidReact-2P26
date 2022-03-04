// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
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

  public void ArmAngleDown(){

    actuatorMotor.setInverted(true);
    
     while(lowLimitSwitch.get() == false);{
        actuatorMotor.set(Constants.actuatorSpeed);}
     while(lowLimitSwitch.get() == true);{
        actuatorMotor.set(Constants.zeroSpeed);}
  }
  
  public void ArmAngleUp(){
    actuatorMotor.setInverted(false);

     while(topLimitSwitch.get() == false);{
        actuatorMotor.set(Constants.actuatorSpeed);}
     while(topLimitSwitch.get() == true);{
        actuatorMotor.set(Constants.zeroSpeed);}
  }

}
