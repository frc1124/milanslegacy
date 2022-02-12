// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.Move;
import frc.robot.subsystems.Drive;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private RobotContainer robotContainer;
  private Drive drive;
  private Joystick j;
  public StringBuilder sb = new StringBuilder();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
    this.drive = robotContainer.drive;
    this.j = robotContainer.joystick;

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    // m_autonomousCommand.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    // m_autonomousCommand.cancel();
    // }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().schedule(robotContainer.arcade());

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    // CommandScheduler.getInstance().cancelAll();
    // drive.resetEncoders();
    Drive.leftFront.getSensorCollection().setQuadraturePosition(0, 10);
    // Drive.leftFront.getSensorCollection().setQuadraturePosition(0, 10);
    // Drive.rightFront.
    Drive.leftFront.setSensorPhase(true);
		Drive.leftFront.setSelectedSensorPosition(0, 0, 0);
    int absolutePosition1 = Drive.leftFront.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition1 &= 0xFFF;
		absolutePosition1 *= -1; 
		Drive.leftFront.setSelectedSensorPosition(absolutePosition1, 0, 0);
		// remove for  absolutePosition1 *= -1; 
		
		/* Set the quadrature (relative) sensor to match absolute */
		Drive.rightFront.setSelectedSensorPosition(0, 0, 0);
    Drive.rightFront.setSensorPhase(true);
    int absolutePosition2 = Drive.rightFront.getSensorCollection().getPulseWidthPosition();
		/* Mask out overflows, keep bottom 12 bits */
		absolutePosition2 &= 0xFFF;
		absolutePosition2 *= -1;
    absolutePosition2 *= -1;
		
		/* Set the quadrature (relative) sensor to match absolute */
		Drive.rightFront.setSelectedSensorPosition(absolutePosition2, 0, 0);
    CommandScheduler.getInstance().schedule(new Move(drive, 24));
  }

  /** This function is called periodically during test mode. */
  int a = 0;

  @Override
  public void testPeriodic() {

    // Drive.leftFront.set(0.2);
    // Drive.rightFront.set(0.15);
    // if(a < 1) {
    //   Drive.leftFront.set(ControlMode.Position, 1000);
    //   Drive.rightFront.set(ControlMode.Position, 1000);
    //   a++;
    // }
    // if(a<1) {
    // System.out.println("ABC");
    // // System.out.println(drive.forward(1000));
    //   // Drive.leftFront.set(ControlMode.Position, 1000);
    //   // Drive.rightFront.set(ControlMode.Position, 1000);
    // a++;
    // }

    // /* Prepare line to print */
    // sb.append("\tleft voltage:");
    // /* Cast to int to remove decimal places */
    // sb.append((Drive.leftFront.getMotorOutputVoltage()));
    // sb.append("V"); // Percent

    sb.append("\tleft pos:");
    sb.append(Drive.leftFront.getSelectedSensorPosition(0));
    sb.append("u"); // Native units

    //  sb.append("\n");
    // //  Prepare line to print */
    //  sb.append("\tright voltage:");
    //  /* Cast to int to remove decimal places */
    //  sb.append((Drive.rightFront.getMotorOutputVoltage()));
    //  sb.append("V"); // Percent

     sb.append("\tright pos:");
     sb.append(Drive.rightFront.getSelectedSensorPosition(0));
     sb.append("u"); // Native units

    System.out.println(sb);
    sb.setLength(0);
    // Drive.leftFront.set(ControlMode.Position, .01);
    // Drive.rightFront.set(ControlMode.Position, 1000);
    // Drive.rightFront.set(1);
    // Drive.leftFront.set(1);

    // drive.arcadeDrive(j.getY(), -1*j.getX());

  }
}
