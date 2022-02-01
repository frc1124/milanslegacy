// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

public class ShooterSub extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private static MotorController shooterMoter;

  public ShooterSub() {
    shooterMoter = new PWMSparkMax(0);
  }

  public void On() {
    shooterMoter.set(1);
  }

  public void Off(){
    shooterMoter.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
