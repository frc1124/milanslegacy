// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
  private final WPI_TalonSRX m1 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX m2 = new WPI_TalonSRX(2);
  private final WPI_TalonSRX m3 = new WPI_TalonSRX(3);
  private final WPI_TalonSRX m4 = new WPI_TalonSRX(4);

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopPeriodic() {
    SmartDashboard.putString("called", "abc");
    m1.set(1);
    m2.set(1);
    m3.set(1);
    m4.set(1);
  }
}
