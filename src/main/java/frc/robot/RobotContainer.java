// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.PotReadCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SolenoidCommand;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
//import edu.wpi.first.wpilibj2.command.Command;
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

  //Name Subsystems Here
  private final DriveSubsystem driveSubsystem;
  private final ClimbSubsystem climbSubsystem;
  private final IntakeSubsystem intakeSubsystem;
  private final ShooterSubsystem shooterSubsystem;
  
  //Name Commands Here
  private final DriveCommand driveCommand;
  private final PotReadCommand potReadCommand;
  private final ShooterCommand shooterCommand;
  private final IntakeCommand intakeCommand;
  private final SolenoidCommand solenoidCommand;

  //Name Controllers Here
  public static XboxController XCont;
  public static Joystick ButtonPanel;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    //Instantilize Subsystems Here
    driveSubsystem = new DriveSubsystem();
    climbSubsystem = new ClimbSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    shooterSubsystem = new ShooterSubsystem();

    //Instantilize Commands Here
    driveCommand = new DriveCommand(driveSubsystem);
    potReadCommand = new PotReadCommand(climbSubsystem);
    shooterCommand = new ShooterCommand(shooterSubsystem);
    intakeCommand = new IntakeCommand(intakeSubsystem);
    solenoidCommand = new SolenoidCommand(climbSubsystem);

    // Add Requirements Here
    driveCommand.addRequirements(driveSubsystem);
    potReadCommand.addRequirements(climbSubsystem);
    shooterCommand.addRequirements(shooterSubsystem);
    intakeCommand.addRequirements(intakeSubsystem);
    solenoidCommand.addRequirements(climbSubsystem);

    //Sets the Default Command of the Scheduler, should remain the drive subsystem and command.
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, driveCommand);

    //Make Controllers Here
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

    //Make Button names and tie them to port numbers from Constants.
    JoystickButton Button1 = new JoystickButton(ButtonPanel, Constants.ButtonPort1);
    JoystickButton Button2 = new JoystickButton(ButtonPanel, Constants.ButtonPort2);
    JoystickButton Button3 = new JoystickButton(ButtonPanel, Constants.ButtonPort3);
    JoystickButton Button4 = new JoystickButton(ButtonPanel, Constants.ButtonPort4);
    JoystickButton Button5 = new JoystickButton(ButtonPanel, Constants.ButtonPort5);
    JoystickButton Button6 = new JoystickButton(ButtonPanel, Constants.ButtonPort6);
    JoystickButton Button7 = new JoystickButton(ButtonPanel, Constants.ButtonPort7);
    JoystickButton Button8 = new JoystickButton(ButtonPanel, Constants.ButtonPort8);

    //Bind buttons to Commands and Subsystems 

    Button1.whileHeld(new ShooterCommand(shooterSubsystem));
    Button2.whileHeld(new IntakeCommand(intakeSubsystem));
    Button3.whileHeld(new IntakeCommand(intakeSubsystem));
    Button4.whileHeld(new IntakeCommand(intakeSubsystem));
    Button5.whenPressed(new SolenoidCommand(climbSubsystem));
    Button6.whenPressed(new SolenoidCommand(climbSubsystem));

    //Below are some examples of doing so

    //Button1.whenPressed(new ExampleCommand(exampleSubsystem));
    //Button1.whileHeld(new ExampleCommand(exampleSubsystem));
    
    /*XCont.getRightStickButtonPressed();{
      new SolenoidCommand(climbSubsystem);
    }
    */
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
