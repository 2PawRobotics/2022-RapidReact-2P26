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
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort10)){
      topIntakeMotor.setInverted(true);
      topIntakeMotor.setVoltage(Constants.tIntakeVolts);
      Constants.reverseIntakeActive = true;}
    while(ButtonPanel.getRawButtonPressed(Constants.ButtonPort11)){
      lowIntakeMotor.setInverted(false);
      lowIntakeMotor.setVoltage(Constants.tIntakeVolts);
      Constants.reverseIntakeActive = true;}
  }
//Auton Intake
  public void AutonIntake() {

    intakeTimer.reset();
    intakeTimer.start();
    lowIntakeMotor.setInverted(true);
    topIntakeMotor.setInverted(false);

    if(intakeTimer.get() <= 1.5){
    intakeMotors.setVoltage(Constants.bothIntakeVolts);
    }else{intakeMotors.setVoltage(0);}

    
//Auton Path 1, 2, and 4
    if(Constants.AutonPath == 1 || Constants.AutonPath == 2 || Constants.AutonPath == 4){
      while(intakeTimer.get() >= .5 && intakeTimer.get() < 1.5){
        lowIntakeMotor.setVoltage(Constants.lIntakeVolts);}
      while(intakeTimer.get() >= 1.5 && intakeTimer.get() < 4.25){
        intakeMotors.setVoltage(0);}
      while(intakeTimer.get() >= 4.25 && intakeTimer.get() < 5.5){
        intakeMotors.setVoltage(Constants.bothIntakeVolts);}
      if(intakeTimer.get() >= 5.5){
        intakeMotors.setVoltage(0);}

//Auton Path 3
    }else if(Constants.AutonPath == 3){
      while(intakeTimer.get() >= 4.25 && intakeTimer.get() < 5.5){
        intakeMotors.setVoltage(Constants.bothIntakeVolts);}
      if(intakeTimer.get() >= 5.5){
        intakeMotors.setVoltage(0);}

//Low Port Auton Path 5
    }else if(Constants.AutonPath == 5){
      while(intakeTimer.get() >= .5 && intakeTimer.get() < 1.5){
        intakeMotors.setVoltage(Constants.bothIntakeVolts);}
      if(intakeTimer.get() >= 1.5){
        intakeMotors.setVoltage(0);}
      }
  }
}


