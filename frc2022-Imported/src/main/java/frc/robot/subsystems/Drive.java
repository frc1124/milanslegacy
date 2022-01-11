// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Drive extends SubsystemBase {
  private static DifferentialDrive drive;
  private static WPI_TalonSRX leftFront;
  private static WPI_TalonSRX leftBack;
  private static WPI_TalonSRX rightFront;
  private static WPI_TalonSRX rightBack;

  private static MotorControllerGroup lefts, rights;

  /** Creates a new ExampleSubsystem. */
  public Drive() {
    leftFront = new WPI_TalonSRX(Constants.LEFTFRONT);
    rightFront = new WPI_TalonSRX(Constants.RIGHTFRONT);
    leftBack = new WPI_TalonSRX(Constants.LEFTFRONT);
    rightBack = new WPI_TalonSRX(Constants.RIGHTFRONT);


    leftFront.setInverted(true);
    leftBack.setInverted(true);

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);

    drive = new DifferentialDrive(leftFront, rightBack);
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
