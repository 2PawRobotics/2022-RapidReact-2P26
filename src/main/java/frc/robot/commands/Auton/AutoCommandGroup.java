// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AutoCommandGroup extends ParallelCommandGroup {
  /** Creates a new AutoCommandGroup. */
  public AutoCommandGroup(DriveSubsystem drive, ShooterSubsystem shoot, IntakeSubsystem intake) {
    addCommands(

      new AutonShootCommand(shoot),
      new AutoIntakeCommand(intake),
      new AutoDriveCommand(drive)

    );
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive, shoot, intake);
  }

 

// Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
