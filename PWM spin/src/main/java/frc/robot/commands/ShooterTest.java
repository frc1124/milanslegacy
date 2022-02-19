// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.ShooterSub;
import edu.wpi.first.wpilibj2.command.CommandBase;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import javax.print.CancelablePrintJob;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/** An example command that uses an example subsystem. */
public class ShooterTest extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ShooterSub shootersub;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShooterTest(ShooterSub shootersub) {
    this.shootersub = shootersub;
    

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shootersub);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shootersub.On();
    
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