// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

  //Declare Hardware Components

  private final CANSparkMax lowIntakeMotor = new CANSparkMax(Constants.intakelowport, MotorType.kBrushed);
  private final CANSparkMax topIntakeMotor = new CANSparkMax(Constants.intakehighport, MotorType.kBrushed);
  private final MotorControllerGroup intakeMotors = new MotorControllerGroup(lowIntakeMotor, topIntakeMotor);
  
  private final Timer intakeTimer = new Timer();

  private ShuffleboardTab tab = Shuffleboard.getTab("AutonPath");
  private NetworkTableEntry AutonPathChoice = tab.add("AutonPath", 2).getEntry();

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

  public void EasterEgg (){
    System.out.println("This Robot, 2P26, was created and made possible by the 2PawRobotics team of 2022.");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Our Leadership Team");
    System.out.println(" ");
    System.out.println("  Captains  - Emily Dalecki & Derek Keene");
    System.out.println("  Secretary - Olivia Garrod");
    System.out.println(" ");
    System.out.println("  Team Leads:");
    System.out.println(" ");
    System.out.println("Build Team Lead       - Tyler Houston");
    System.out.println("Programming Team Lead - William Mackeller");
    System.out.println("Buisness Team Lead    - Natalie Atwater");
    System.out.println("Marketing Team Leads  - Carter Buck & Meah Hameed");
    System.out.println("Media Team Lead       - Jaxin Lovorn");
    System.out.println(" ");
    System.out.println("----------------------------------");
    System.out.println(" ");
    System.out.println("Our Drive Team:");
    System.out.println(" ");
    System.out.println("Driver       - Tyler Houston");
    System.out.println("Operator     - Derek Keene");
    System.out.println("Drive Coach  - Logan Keene");
    System.out.println("Human Player - Brenden Muessig");
    System.out.println("Technician   - William Mackeller");
    System.out.println("Media        - Jaxin Lovorn");
    System.out.println(" ");
    System.out.println("----------------------------------");
    System.out.println(" ");
    System.out.println("Full Team Roster:");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Build Team");
    System.out.println("Tyler Houston, Abigal Goodwin, Brenden Muessig, Hassei Omori, Jaxin Lovorn, William Mackeller, Phil Staffen, Derek Keene,");
    System.out.println(" ");
    System.out.println("Programming Team");
    System.out.println("Derek Keene, William Mackeller, Logan Keene, Phil Staffen, Ethan Skowronski, Carter Borah");
    System.out.println(" ");
    System.out.println("Marketing & Buisness Team");
    System.out.println("Emily Dalecki, Natalie Atwater, Carter Buck, Olivia Garrod, Meah Hameed, Jaxin Lovorn, Chante (Creed) Rayborn, Teagen TenBrink");
    System.out.println(" ");
    System.out.println("----------------------------------");
    System.out.println(" ");
    System.out.println("Our Mentors:");
    System.out.println(" ");
    System.out.println(" ");
    System.out.println("Lead Mentor - Mr.Tomlinson");
    System.out.println(" ");
    System.out.println("Arthur Keene, Bruce Mackeller, Clayton Meyers, Sherri Keene");
  }

//Autonomous Intake Timer
  public void IntakeinitTimer() {
    intakeTimer.reset();
    intakeTimer.start();
  }

//Autonomous Intake Paths
  public void AutonIntake(int lIntakeVolts, int tIntakeVolts, Double AutobothIntakeVolts) {
    double AutonPath = AutonPathChoice.getDouble(2.0);
    lowIntakeMotor.setInverted(true);
    topIntakeMotor.setInverted(false);

//Autonomous Path 1
  if(AutonPath == 1){
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

//Autonomous Path 2
  if(AutonPath == 2){
    if(intakeTimer.get() >= 2.5 && intakeTimer.get() <= 2.9){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    if(intakeTimer.get() >= 2.9 && intakeTimer.get() <= 7){
      lowIntakeMotor.setVoltage(0);
    }
    if(intakeTimer.get() >= 7 && intakeTimer.get() <= 8.25){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() >= 8.25){
      intakeMotors.setVoltage(0);
    }
  }

//Autonomous Path 3
  if(AutonPath == 3){
    if(intakeTimer.get() >= 6 && intakeTimer.get() <= 7){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() >= 7){
      intakeMotors.setVoltage(0);
    }
  }

//Autonomous Path 4
  if(AutonPath == 4){
    //Intake First Cargo
    if(intakeTimer.get() > 2.3 && intakeTimer.get() < 2.7){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    else {
      lowIntakeMotor.setVoltage(0);
    }
    //Shoot First Two Cargo
    if(intakeTimer.get() > 4.5 && intakeTimer.get() < 6){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    else {
      intakeMotors.setVoltage(0);
    }
    //Intake 3rd & 4th Cargo and Index Appropriately 
    if(intakeTimer.get() > 9 && intakeTimer.get() < 10.5){
      lowIntakeMotor.setVoltage(lIntakeVolts);
      if(intakeTimer.get() > 9.7 && intakeTimer.get() < 10.3){
        topIntakeMotor.setVoltage(tIntakeVolts);
      }
    }
    else {
      lowIntakeMotor.setVoltage(0);
      topIntakeMotor.setVoltage(0);
    }
    //Shoot 3rd & 4th Cargo
    if(intakeTimer.get() > 13.5){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
  }

//Auton Path 5
  if(AutonPath == 5){
    if(intakeTimer.get() > 3.3 && intakeTimer.get() <= 3.7){
      lowIntakeMotor.setVoltage(lIntakeVolts);
    }
    if(intakeTimer.get() > 3.7 && intakeTimer.get() <= 5.8){
      intakeMotors.setVoltage(0);
    }
    if(intakeTimer.get() > 5.8 && intakeTimer.get() <= 6.8){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() > 6.8 && intakeTimer.get() <= 10.3){
      intakeMotors.setVoltage(0);
    }
    if(intakeTimer.get() > 10.3 && intakeTimer.get() <= 10.7){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
    if(intakeTimer.get() > 10.7 && intakeTimer.get() <= 13){
      intakeMotors.setVoltage(0);
    }
    if(intakeTimer.get() > 13.5){
      intakeMotors.setVoltage(AutobothIntakeVolts);
    }
  }
}
}


