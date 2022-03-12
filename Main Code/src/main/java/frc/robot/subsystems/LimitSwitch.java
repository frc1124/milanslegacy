// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import java.lang.invoke.ConstantBootstraps;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;


public class LimitSwitch extends SubsystemBase {
  // static DigitalInput top_Switch;
  // static AnalogInput top_Switch;
  /** Creates a new ExampleSubsystem. */
  public LimitSwitch() {

    // top_Switch = new DigitalInput(Constants.TOP_SWITCH);
    // top_Switch = new AnalogInput(0);
      





  }
  public static boolean get_top() {
    // System.out.println(top_Switch.get());
    // System.out.println(top_Switch.getValue());
    // return (top_Switch.getValue() != 0);
    return false;
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
