// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
  private final WPI_TalonSRX m1 = new WPI_TalonSRX(1);
  private final WPI_TalonSRX m2 = new WPI_TalonSRX(2);

  @Override
  public void robotInit() {
  }

  @Override
  public void teleopPeriodic() {
    StringBuilder sb = new StringBuilder();
    m1.getSensorCollection().setQuadraturePosition(0, 0);
    m2.getSensorCollection().setQuadraturePosition(0, 0);
    m1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    m2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);


    sb.append("\t1 pos:");
    sb.append(m1.getSelectedSensorPosition(0));
    sb.append("u"); // Native units

    sb.append("\t2 pos:");
    sb.append(m2.getSelectedSensorPosition(0));
    sb.append("u"); // Native units

    System.out.println(sb);
    // m1.set(1);
    // m2.set(1);
    // m3.set(1);
    // m4.set(1);
  }
}
