
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Shooter;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

public class Shoot extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  public Shoot(double v, PIDController controller, Shooter shooter){
    super(controller, shooter::getMeasurement, v, output -> shooter.useOutput(output, v));
    
    // Tolerance; 0 in  and 0 in/s
    getController().setTolerance(0, 0);
    addRequirements(shooter);

  }
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
