// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class SpeedRamping extends SubsystemBase {
  RobotContainer rc;
  /** Creates a new ExampleSubsystem. */
  double speed_increment;
  double current_speed;
  double joystick_speed;
  double currentEncoderVal;
  double lastEncoderVal;
  double DPMS;
  double current_value;
  public SpeedRamping(double current_value) {
    current_speed = current_value;
    speed_increment = 6500;
    //speed_increment = 7000;
  }

  public double increment_speed(double joystick_speed) {

    if (current_speed < joystick_speed) {
      current_speed += speed_increment;
      return current_speed;
    } else if (current_speed > joystick_speed) {
      current_speed -= speed_increment;
      return current_speed;

    } else {
      return current_speed;
    }
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
