// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  //Declare Hardware Components

  private final CANSparkMax lowIntakeMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  private final CANSparkMax topIntakeMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  private final MotorControllerGroup intakeMotors = new MotorControllerGroup(lowIntakeMotor, topIntakeMotor);
  
  private final Timer intakeTimer = new Timer();

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void IntakeOff(){
     intakeMotors.setVoltage(0);
     lowIntakeMotor.setVoltage(0);
     topIntakeMotor.setVoltage(0);
  }

  public void RunIntake(Joystick ButtonPanel) {
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort4)){
      topIntakeMotor.setInverted(false);
      lowIntakeMotor.setInverted(true);
      intakeMotors.setVoltage(Constants.bothIntakeVolts);
      Constants.intakeActive = true;}
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort6)){
      lowIntakeMotor.setInverted(true);
      lowIntakeMotor.setVoltage(Constants.lIntakeVolts);
      Constants.intakeActive = true;}
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort5)){
      topIntakeMotor.setInverted(false);
      topIntakeMotor.setVoltage(Constants.tIntakeVolts);
      Constants.intakeActive = true;}
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort11)){
      lowIntakeMotor.setInverted(false);
      lowIntakeMotor.setVoltage(Constants.tIntakeVolts);
      topIntakeMotor.setInverted(true);
      topIntakeMotor.setVoltage(Constants.tIntakeVolts);
    }
  }


//Autonomous Intake Timer
  public void IntakeinitTimer() {
    intakeTimer.reset();
    intakeTimer.start();
  }

//Autonomous Intake Paths
  public void AutonIntake(int AutonPath, int lIntakeVolts, Double AutobothIntakeVolts) {
    lowIntakeMotor.setInverted(true);
    topIntakeMotor.setInverted(false);

//Autonomous Path 1
  if(AutonPath == 1){
    if(intakeTimer.get() > 3.2 && intakeTimer.get() <= 3.75){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    if(intakeTimer.get() >3.75 && intakeTimer.get() <= 8.5){
      intakeMotors.setVoltage(0);
    }
    if(intakeTimer.get() > 8.5 && intakeTimer.get() <= 10){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() >10){
      intakeMotors.setVoltage(0);
    }
  }

//Autonomous Path 2
  if(AutonPath == 2){
    if(intakeTimer.get() > 2.2 && intakeTimer.get() <= 2.6){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    if(intakeTimer.get() >2.6 && intakeTimer.get() <= 6){
      intakeMotors.setVoltage(0);
    }
    if(intakeTimer.get() > 6 && intakeTimer.get() <= 7.25){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() > 7.25){
      intakeMotors.setVoltage(0);
    }
  }

//Autonomous Path 3
  if(AutonPath == 3){
    if(intakeTimer.get() >= 3 && intakeTimer.get() <= 3.4){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    if(intakeTimer.get() >= 3.4 && intakeTimer.get() <= 7){
      lowIntakeMotor.setVoltage(0);
    }
    if(intakeTimer.get() >= 7 && intakeTimer.get() <= 8.25){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() >= 8.25){
      intakeMotors.setVoltage(0);
    }
  }

//Autonomous Path 4
  if(AutonPath == 4){
    if(intakeTimer.get() >= 6 && intakeTimer.get() <= 7){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() >= 7){
      intakeMotors.setVoltage(0);
    }
  }

}

}


