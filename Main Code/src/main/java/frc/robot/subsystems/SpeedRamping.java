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
  double speed_constant;
  double increase_increment;
  double decrease_increment;
  double speed_increment;
  double current_speed;
  double joystick_speed;
  double currentEncoderVal;
  double lastEncoderVal;
  double DPMS;
  double current_value;
  //
    public SpeedRamping(double current_value) {
    current_speed = current_value;
    speed_increment = 6500;
    decrease_increment = 20000;
    increase_increment = 22000;
    speed_constant = 6500;
  }

  public double increment_speed(double joystick_speed) {

    if (current_speed < joystick_speed) {
      current_speed += speed_increment;
      return current_speed;
    } else if (current_speed > joystick_speed) {
      current_speed -= decrease_increment;
      return current_speed;

    } else {
      return current_speed;
    }
  }
  
  /*public double increment_speed(double joystick_speed) {
    while((int) joystick_speed != 0) {  //new
       if (current_speed < joystick_speed) {
         current_speed += increase_increment;
         increase_increment *= 1.02;
         return current_speed;
          
       } else if (current_speed > joystick_speed) {
        current_speed -= decrease_increment;
        decrease_increment *= 1.02;
        return current_speed;
       } else {
         return current_speed;
       }
     }
    increase_increment = speed_constant; //new
    decrease_increment = (speed_constant + 2500); //new4
    return 0.0;
   }*/
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
