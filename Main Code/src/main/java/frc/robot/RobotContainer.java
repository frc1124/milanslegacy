// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.El_down;
import frc.robot.commands.El_up;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ReleaseBallz;
import frc.robot.commands.ScrewUp;
import frc.robot.commands.Shoot;
import frc.robot.commands.SuckBallz;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.LimitSwitch;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.PIDDrive;
import frc.robot.subsystems.Screw;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public static XboxController j;


  // The robot's subsystems and commands are defined here...
  
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
  public final PIDDrive left =  new PIDDrive(lefts, leftEncoder, leftVController, leftDController, true);
  public final PIDDrive right = new PIDDrive(rights, rightEncoder, rightVController, rightDController, false);
  

   public final WPI_TalonSRX liftLeader = new WPI_TalonSRX(Constants.LIFTLEADER);
   public final WPI_TalonSRX liftFollower = new WPI_TalonSRX(Constants.LIFTFOLLOWER);
   public final MotorControllerGroup lifts = new MotorControllerGroup(liftLeader, liftFollower);
  
   public final Spark shooterSpark = new Spark(Constants.SHOOTER);
   public final PIDController shootController = new PIDController(
     Constants.SHOOT_P, Constants.SHOOT_I, Constants.SHOOT_D);

  CANSparkMax motor = new CANSparkMax(1 , MotorType.kBrushless);
  RelativeEncoder encoder = motor.getEncoder();
  PIDController controller = new PIDController(Constants.SHOOT_P,Constants.SHOOT_I,Constants.SHOOT_D);

  Shooter shooter = new Shooter(motor, encoder, controller);
  Intake intake = new Intake();
  Screw screw = new Screw();
  Lift lift = new Lift();
  LimitSwitch limitswitch = new LimitSwitch();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */

  public RobotContainer() {
    j = new XboxController(0);
    SmartDashboard.putData("Run Shooter", new Shoot(Constants.SHOOT_POINT, controller, shooter));
    // Configure the button bindings
    configureButtonBindings();


  }
  
  public static HashMap<String, JoystickButton> logitechMap = new HashMap<String, JoystickButton>();


  public static JoystickButton getKey(String key) { 
    logitechMap.put("A", new JoystickButton(j, 1));
    logitechMap.put("B", new JoystickButton(j, 2));
    logitechMap.put("X", new JoystickButton(j, 3));
    logitechMap.put("Y", new JoystickButton(j, 4));
    logitechMap.put("LB", new JoystickButton(j, 5));
    logitechMap.put("RB", new JoystickButton(j, 6));
    logitechMap.put("Back", new JoystickButton(j, 7));
    logitechMap.put("Start", new JoystickButton(j, 8));
    //JoystickButton exampleButton = new JoystickButton(exampleStick, 1);
    return logitechMap.get(key);
}
  
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */

  private void configureButtonBindings() {
    
    getKey("RB").whileHeld(new Shoot(Constants.SHOOT_POINT, controller, shooter));
    getKey("A").whenPressed(new ScrewUp(screw));
    getKey("B").whenPressed(new SuckBallz(intake));
    getKey("X").whenPressed(new ReleaseBallz(intake));
    getKey("Y").whenPressed(new El_up(lift, Constants.Lift_POINT, limitswitch));
    getKey("LB").whenPressed(new El_down(lift, Constants.Lift_POINT, limitswitch));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public void getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return arcadeDrive;
  }
}
