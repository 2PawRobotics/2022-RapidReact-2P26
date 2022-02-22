// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.PotentiometerRead;
import frc.robot.commands.ShootShirtCommand;
//import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.tShirtSubsystem;
//import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

// The robot's subsystems and commands are defined here...
 // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
 // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem );

  private final DriveSubsystem driveSubsystem;
  private final DriveCommand driveCommand;
  private final tShirtSubsystem tSubsystem;
  private final ShootShirtCommand shootShirtCommand;

  public static XboxController XCont;
  public static Joystick ButtonPanel;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    driveSubsystem = new DriveSubsystem();
    driveCommand = new DriveCommand(driveSubsystem);
    driveCommand.addRequirements(driveSubsystem);
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, driveCommand);

    tSubsystem = new tShirtSubsystem();
    shootShirtCommand = new ShootShirtCommand(tSubsystem);
    shootShirtCommand.addRequirements(tSubsystem);

    XCont = new XboxController(Constants.XContPort);
    ButtonPanel = new Joystick(Constants.ButtonPanelPort);

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton LBarrelButton = new JoystickButton(ButtonPanel, Constants.lBarrelButton);
    JoystickButton MBarrelButton = new JoystickButton(ButtonPanel, Constants.mBarrelButton);
    JoystickButton RBarrelButton = new JoystickButton(ButtonPanel, Constants.rBarrelButton);

   //LBarrelButton.whenPressed(new ShootShirtCommand(tSubsystem));
   // LBarrelButton.whileHeld(new PotentiometerRead(tSubsystem));

  
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
 // public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}
