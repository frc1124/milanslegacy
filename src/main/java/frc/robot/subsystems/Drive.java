// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Drive extends SubsystemBase {
  private DifferentialDrive drive;
  private TalonSRX leftFront;
  private TalonSRX leftBack;
  private TalonSRX rightFront;
  private TalonSRX rightBack;
  /** Creates a new ExampleSubsystem. */
  public Drive() {
    leftFront = new TalonSRX(Constants.LEFTFRONT);
    rightFront = new TalonSRX(Constants.RIGHTFRONT);
    leftBack = new TalonSRX(Constants.LEFTFRONT);
    rightBack = new TalonSRX(Constants.RIGHTFRONT);


    leftFront.setInverted(true);
    leftBack.setInverted(true);

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);

    drive = new DifferentialDrive(leftFront, rightFront);
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
