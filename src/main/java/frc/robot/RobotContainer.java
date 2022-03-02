// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ArmCommand;
import frc.robot.commands.AutoCommandGroup;
import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.AutoGyroCommand;
import frc.robot.commands.AutoIntakeCommand;
import frc.robot.commands.AutonShootCommand;
import frc.robot.commands.CompressorCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ReverseShooterCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.commands.SolenoidCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClimbSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
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
  private final ArmSubsystem armSubsystem;
  
  //Name Commands Here
  private final DriveCommand driveCommand;
  private final ShooterCommand shooterCommand;
  private final IntakeCommand intakeCommand;
  private final SolenoidCommand solenoidCommand;
  private final AutonShootCommand autonShootCommand;
  private final AutoDriveCommand autoDriveCommand;
  private final AutoGyroCommand autoGyroCommand;
  private final ReverseShooterCommand reverseShooterCommand;
  private final AutoIntakeCommand autoIntakeCommand;
  private final CompressorCommand compressorCommand;

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
    armSubsystem = new ArmSubsystem();

    //Instantilize Commands Here
    driveCommand = new DriveCommand(driveSubsystem);
    shooterCommand = new ShooterCommand(shooterSubsystem);
    intakeCommand = new IntakeCommand(intakeSubsystem);
    solenoidCommand = new SolenoidCommand(climbSubsystem);
    reverseShooterCommand = new ReverseShooterCommand(shooterSubsystem);
    compressorCommand = new CompressorCommand(climbSubsystem);

    //Instantilize Autonomous Commands Here
    autonShootCommand = new AutonShootCommand(shooterSubsystem);
    autoDriveCommand = new AutoDriveCommand(driveSubsystem);
    autoGyroCommand = new AutoGyroCommand(driveSubsystem);
    autoIntakeCommand = new AutoIntakeCommand(intakeSubsystem);

    //Add Requirements Here
    driveCommand.addRequirements(driveSubsystem);
    shooterCommand.addRequirements(shooterSubsystem);
    intakeCommand.addRequirements(intakeSubsystem);
    solenoidCommand.addRequirements(climbSubsystem);
    reverseShooterCommand.addRequirements(shooterSubsystem);
    compressorCommand.addRequirements(climbSubsystem);

    //Add Autonomous Requirements Here
    autonShootCommand.addRequirements(shooterSubsystem);
    autoDriveCommand.addRequirements(driveSubsystem);
    autoGyroCommand.addRequirements(driveSubsystem);
    autoIntakeCommand.addRequirements(intakeSubsystem);

    //Sets the Default Command of a subsystem, should remain the drive subsystem and command.
    CommandScheduler.getInstance().setDefaultCommand(driveSubsystem, driveCommand);
    CommandScheduler.getInstance().setDefaultCommand(climbSubsystem, compressorCommand);

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
    JoystickButton button9 = new JoystickButton(ButtonPanel, Constants.ButtonPort9);
    JoystickButton Button10 = new JoystickButton(ButtonPanel, Constants.ButtonPort10);
    JoystickButton Button11 = new JoystickButton(ButtonPanel, Constants.ButtonPort11);
    JoystickButton Button12 = new JoystickButton(ButtonPanel, Constants.ButtonPort12);
    JoystickButton Button13 = new JoystickButton(ButtonPanel, Constants.ButtonPort13);
    JoystickButton Button14 = new JoystickButton(ButtonPanel, Constants.ButtonPort14);

    //Bind buttons to Commands and Subsystems 

    Button1.whileHeld(new ShooterCommand(shooterSubsystem));
    Button2.whileHeld(new ReverseShooterCommand(shooterSubsystem));
    Button4.whileHeld(new IntakeCommand(intakeSubsystem));
    Button5.whileHeld(new IntakeCommand(intakeSubsystem));
    Button6.whileHeld(new IntakeCommand(intakeSubsystem));
    button9.whenPressed(new SolenoidCommand(climbSubsystem));
    Button12.whenPressed(new SolenoidCommand(climbSubsystem));
    Button10.whileHeld(new IntakeCommand(intakeSubsystem));
    Button11.whileHeld(new IntakeCommand(intakeSubsystem));
    Button13.whenPressed(new ArmCommand(armSubsystem));
    Button14.whenPressed(new ArmCommand(armSubsystem));

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
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new AutoCommandGroup(shooterSubsystem, driveSubsystem, driveSubsystem, intakeSubsystem);
  }
}