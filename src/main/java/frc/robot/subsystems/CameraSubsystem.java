// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.cameraserver.CameraServer;

public class CameraSubsystem extends SubsystemBase {

  //Declare Hardware Componets 
  private CameraServer cameraServer;

  public static void Camera(){
    CameraServer.startAutomaticCapture();
  }

   
  
}
