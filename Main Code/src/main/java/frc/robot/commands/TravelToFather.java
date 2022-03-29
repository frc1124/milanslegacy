// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FatherFinder;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

/** An example command that uses an example subsystem. */
public class TravelToFather extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private RobotContainer rc;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  FatherFinder fatherWhy;
  Shooter shooter;
  TankCommandGroup tankCommandGroup;
  Shoot shoot;

  public TravelToFather(FatherFinder fatherWhy, Shooter shooter) {
    this.fatherWhy = fatherWhy;
    this.shooter =  shooter;
 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(fatherWhy);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = fatherWhy.DistanceFromFather();
    if (!(distance < 420.69 && distance > 69.420 )) {
        SmartDashboard.putBoolean("In Range: ", false);
    } else {
        new Shoot(Constants.SHOOTER, rc.controller, rc.shooter);
        SmartDashboard.putBoolean("In Range: ", true);
        
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
