// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class El_down extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  Lift lift;
  double setpoint;
  public El_down(Lift lift, double setpoint) {
    this.lift = lift;
    this.setpoint = setpoint;
    



    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(lift);
  
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    lift.motor_down(setpoint);
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    lift.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // return lift.getDistance() < 1;
    return false;
  }
}
