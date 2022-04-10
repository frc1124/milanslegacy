// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
 CANSparkMax intake_motor;

  /** Creates a new ExampleSubsystem. */
  
  public Intake() {
    intake_motor = new CANSparkMax(Constants.INTAKE_ID,MotorType.kBrushless);
  }

  public void on() {
    intake_motor.set(-0.5);
  }

  public void reverse() {
    intake_motor.set(0.5);
  }

  public void stop() {
    intake_motor.set(0);
  }
  
  public void on_inverted() {
    intake_motor.set(-1);
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

