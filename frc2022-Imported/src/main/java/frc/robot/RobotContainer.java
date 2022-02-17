// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  public final PIDDrive left;
  public final PIDDrive right;

  public final Encoder leftEncoder = new Encoder(Constants.LEFTCHANNEL_A, Constants.LEFTCHANNEL_B);
  public final Encoder rightEncoder = new Encoder(Constants.RIGHTCHANNEL_A, Constants.RIGHTCHANNEL_B);
  public final WPI_TalonSRX leftLeader = new WPI_TalonSRX(Constants.LEFTFRONT);
  public final WPI_TalonSRX leftFollower = new WPI_TalonSRX(Constants.LEFTBACK);
  public final WPI_TalonSRX rightLeader = new WPI_TalonSRX(Constants.RIGHTFRONT);
  public final WPI_TalonSRX rightFollower = new WPI_TalonSRX(Constants.RIGHTBACK);
  public final MotorControllerGroup lefts = new MotorControllerGroup(leftLeader, leftFollower);
  public final MotorControllerGroup rights = new MotorControllerGroup(rightLeader, rightFollower);
  public final PIDController leftDController = new PIDController(
    Constants.DIST_L_P, Constants.DIST_L_I, Constants.DIST_L_D);
  public final PIDController rightDController = new PIDController(
    Constants.DIST_R_P, Constants.DIST_R_I, Constants.DIST_R_D);
  public final PIDController leftVController = new PIDController(
    Constants.VEL_L_P, Constants.VEL_L_I, Constants.VEL_L_D);
  public final PIDController rightVController = new PIDController(
    Constants.VEL_R_P, Constants.VEL_R_I, Constants.VEL_R_D);

  
  // public final Spark shooterSpark = new Spark(Constants.SHOOTER);
  // public final PIDController shootController = new PIDController(
  //   Constants.SHOOT_P, Constants.SHOOT_I, Constants.SHOOT_D);

  
  public final XboxController j = new XboxController(0);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    left =  new PIDDrive(lefts, leftEncoder, leftVController, leftDController, true);
    right = new PIDDrive(rights, rightEncoder, rightVController, rightDController, false);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  // public Command getTeleopDrive() {
  // }

  private void configureButtonBindings() {
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return arcadeDrive;
  // }
}
