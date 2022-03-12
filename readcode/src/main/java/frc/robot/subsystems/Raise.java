// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class Raise extends SubsystemBase {

  private static WPI_TalonSRX firstFlywheel;
  private static WPI_TalonSRX secondFlywheel;
  private static WPI_TalonSRX screw;

  /** Creates a new ExampleSubsystem. */
  public Raise() {
    firstFlywheel = new WPI_TalonSRX(Constants.FIRSTFLYWHEEL);
    secondFlywheel = new WPI_TalonSRX(Constants.SECONDFLYWHEEL);
    screw = new WPI_TalonSRX(Constants.SCREW);
  }

  public void up(){
    screw.set(1);
  }

  public void stop(){
    screw.set(0);
  }

  public void shooterOn(){
    firstFlywheel.set(1);
    secondFlywheel.set(1);
  }

  public void shooterOff(){
    firstFlywheel.set(0);
    secondFlywheel.set(0);
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
