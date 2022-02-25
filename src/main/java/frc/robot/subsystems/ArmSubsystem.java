// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

  //Declare Hardware Components
  private final CANSparkMax actuatorMotor = new CANSparkMax(Constants.actuatorport, MotorType.kBrushed);
  private final AnalogPotentiometer pot = new AnalogPotentiometer(0, 500, 0);

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void Readpot(){
    System.out.println(pot.get());}

  public void ArmAngleDown(){

    actuatorMotor.setInverted(true);

    while(pot.get() < 300);{
      actuatorMotor.setVoltage(Constants.fastAngleVolts);
    }
    while(pot.get() >= 300 && pot.get() <350);{
      actuatorMotor.setVoltage(Constants.slowAngleVolts);
    }
    while(pot.get() >= 350);{
      actuatorMotor.setVoltage(Constants.zeroAngleVolts);
    }
    if(actuatorMotor.getOutputCurrent() > 5);{
      actuatorMotor.setVoltage(Constants.zeroAngleVolts);
    }
  }                                                         //ranges are from aprox 150 at top and 350

  public void ArmAngleUp(){

    actuatorMotor.setInverted(false);

    while(pot.get() > 200);{
      actuatorMotor.setVoltage(Constants.fastAngleVolts);
    }
    while(pot.get() <= 200 && pot.get() > 150);{
      actuatorMotor.setVoltage(Constants.slowAngleVolts);
    }
    while(pot.get() <= 150);{
      actuatorMotor.setVoltage(Constants.zeroAngleVolts);
    }
    if(actuatorMotor.getOutputCurrent() > 5);{
      actuatorMotor.setVoltage(Constants.zeroAngleVolts);
    }
  }
}
