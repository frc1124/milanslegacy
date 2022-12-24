// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.hal.AnalogJNI;
import edu.wpi.first.wpilibj.AnalogInput;

public class FatherFinder extends SubsystemBase {
    AnalogInput input;
  /** Creates a new ExampleSubsystem. */
  public FatherFinder() {
      input = new AnalogInput(Constants.FATHER_CHANNEL);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public double DistanceFromFather() {
    double distance = input.getVoltage()/9.8;
    return distance;
 }


}
