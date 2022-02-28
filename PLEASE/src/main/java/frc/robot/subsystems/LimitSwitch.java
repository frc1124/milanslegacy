// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import java.lang.invoke.ConstantBootstraps;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;


public class LimitSwitch extends SubsystemBase {
  static DigitalInput top_Switch;
  static DigitalInput bottom_Switch;
  /** Creates a new ExampleSubsystem. */
  public LimitSwitch() {

    top_Switch = new DigitalInput(Constants.TOP_SWITCH);
    bottom_Switch = new DigitalInput(Constants.BOTTOM_SWITCH);





  }
  public static boolean get_top() {
    return top_Switch.get();
  }

  public static  boolean get_bottom() {
    return bottom_Switch.get();
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