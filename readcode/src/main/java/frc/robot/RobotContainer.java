// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Absorb;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Drive drive = new Drive();
  public static final Joystick joystick = new Joystick(Constants.ARCADE_STICK);

  private final ArcadeDrive arcadeDrive = new ArcadeDrive(drive, joystick);
  public final Intake intake = new Intake();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  
  public static HashMap<String, JoystickButton> logitechMap = new HashMap<String, JoystickButton>();

  public static JoystickButton getKey(String key){
    logitechMap.put("A", new JoystickButton(joystick, 1));
    logitechMap.put("B", new JoystickButton(joystick, 2));
    logitechMap.put("X", new JoystickButton(joystick, 3));
    logitechMap.put("Y", new JoystickButton(joystick, 4));
    logitechMap.put("LB", new JoystickButton(joystick, 5));
    logitechMap.put("RB", new JoystickButton(joystick, 6));
    logitechMap.put("Back", new JoystickButton(joystick, 7));
    logitechMap.put("Start", new JoystickButton(joystick, 8));
    return logitechMap.get(key);
  }

  private void configureButtonBindings() {
    getKey("A").whenPressed(new Absorb(intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command arcade() {
    return new ArcadeDrive(drive, joystick);
  }
}
