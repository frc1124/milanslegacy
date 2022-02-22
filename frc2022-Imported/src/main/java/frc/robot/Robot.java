// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Move;
import frc.robot.commands.MoveCommandGroup;
import frc.robot.commands.Tank;
import frc.robot.commands.TankCommandGroup;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autoCMD;
  private RobotContainer rc;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    rc = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    CommandScheduler.getInstance().schedule(new MoveCommandGroup(24.0, rc));
    // autoCMD = rc.getAutonomousCommand();

    // schedule the autonomous command (example)
    // if (autoCMD != null) {
    //   autoCMD.schedule();
    // }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autoCMD != null) {
      autoCMD.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // double rightJ = rc.rightLimiter.calculate(rc.j.getRightY());
    // double leftJ = rc.leftLimiter.calculate(rc.j.getRightY());
    // Joystick is wierd, we right side is leftt, idk
    double rightJ = Math.pow(rc.j.getLeftY(),3);
    double leftJ =  Math.pow(rc.j.getRightY(),3);
    double rightV = -1 * Constants.MAXSPEED * rightJ;
    double leftV = -1 * Constants.MAXSPEED * leftJ;
    // System.out.println("" +rightV + "," +leftV);
    CommandScheduler.getInstance().schedule(new TankCommandGroup(leftV, rightV, rc));
    // CommandScheduler.getInstance().schedule(new TankCommandGroup(60, 60, rc));
    CommandScheduler.getInstance().run();
    debug();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    // CommandScheduler.getInstance().cancelAll();
    // CommandScheduler.getInstance().schedule(new MoveCommandGroup(24.0, rc));
    // CommandScheduler.getInstance().schedule( new Move(48, rc.leftDController, rc.left));

  }

  /** This function is called periodically during test mode. */

  public void debug() {

    // System.out.println("Right Target V:" + Constants.MAXSPEED * rc.j.getLeftX());
    // System.out.println("Left Target V:" + Constants.MAXSPEED * rc.j.getLeftY());

    // System.out.println("Right Joystick:" + rc.j.getRightY());
    // System.out.println("Left Joystick:" + rc.j.getLeftY());

    // System.out.println("Right Encoder:" + rc.right.getMeasurement());
    // System.out.println("Left Encoder:" + rc.left.getMeasurement());

    // System.out.println("Right Velocity:" + rc.right.getMeasurementV());
    // System.out.println("Left Velocity:" + rc.left.getMeasurementV());

    // System.out.println("Angle:" + rc.right.getNavxInstance().getAngle());
  }
  @Override
  public void testPeriodic() {

    // System.out.println(CommandScheduler.getInstance().isScheduled());
    CommandScheduler.getInstance().run();
    // debug();
  }

}