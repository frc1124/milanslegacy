// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.Shooter;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** An example command that uses an example subsystem. */
public class Move extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PIDDrive side;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Move(double distance, PIDController controller, PIDDrive side, Encoder e){
    super(controller, side::getMeasurement, distance, output -> side.useOutput(output, distance));
    this.side = side;
    e.reset();
    
    // Tolerance; 0 in  and 0 in/s
    getController().setTolerance(0, 0);
    addRequirements(side);

  }
  // @Override
  // public void execute() {
  //   side.set(.5);
  // }
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.print("in progress");
    // return getController().atSetpoint();
    return false;
  }
}
