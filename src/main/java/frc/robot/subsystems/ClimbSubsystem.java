// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class ClimbSubsystem extends SubsystemBase {

  //Declare Hardware Components

  private final DoubleSolenoid dSolenoid2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
  public final static Compressor comp = new Compressor(0, PneumaticsModuleType.CTREPCM);

  //--------------------------------------------------------------------------------------------//
  // Make Methods Here

  public void SolenoidControl(){
    if(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort9)){
      dSolenoid2.set(Value.kForward);
    }
    if(RobotContainer.ButtonPanel.getRawButtonPressed(Constants.ButtonPort12)){
      dSolenoid2.set(Value.kReverse);
    }
  }
}
