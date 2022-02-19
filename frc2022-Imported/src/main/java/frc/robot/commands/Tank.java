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
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;

/** An example command that uses an example subsystem. */
public class Tank extends PIDCommand {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private PIDDrive side;
  private PIDController controller;
  private SlewRateLimiter limiter = new SlewRateLimiter(1.0/3);
  private double timeout = 0.1;
  private double initialTime;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Tank(double velocity, PIDController controller, PIDDrive side){
    super(controller, side::getMeasurementV, velocity, output -> side.useOutputV(output, velocity));
    this.side = side;
    this.controller = controller;
    initialTime = Timer.getFPGATimestamp();
    
    
    // Tolerance; 1 in/s ; 0 in/s^2
    getController().setTolerance(0, 0);
    System.out.println("Tank:" + velocity);
    addRequirements(side);
  }
  // @Override
  // public void execute() {
  //   side.set(1);
  // }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println(Timer.getFPGATimestamp() - initialTime);
    if(Timer.getFPGATimestamp() - initialTime > timeout) return true;
    // System.out.println("enc" + side.getMeasurementV());
    return getController().atSetpoint();
  }
}
