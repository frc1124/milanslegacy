// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {

  private static WPI_TalonSRX firstRoller;
  private static WPI_TalonSRX secondRoller;

  /** Creates a new ExampleSubsystem. */
  public Intake() {

    firstRoller = new WPI_TalonSRX(Constants.FIRSTROLLER);
    secondRoller = new WPI_TalonSRX(Constants.SECONDROLLER);

  }
  public void firstOn() {
    firstRoller.set(1);
  }

  public void firstOff() {
    firstRoller.set(0);
  }

  public void secondOn(){
    secondRoller.set(1);
  }

  public void secondOff() {
    secondRoller.set(0);
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