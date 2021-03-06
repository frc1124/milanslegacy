// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.util.Color;
// import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.MoveCommandGroup;

/** An example command that uses an example subsystem. */

public class BackToBlack extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  MoveCommandGroup move;
  ColorSensor colorsensor;
  BackToBlack backToBlack;
  RobotContainer rc;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */


  public BackToBlack(ColorSensor colorsensor) {
    this.colorsensor = colorsensor;
    move = new MoveCommandGroup(-1, rc);
    //Use addRequirements() here to declare subsystem dependencies.
    addRequirements(colorsensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    while(!colorsensor.detect()) {
      move.addCommands(backToBlack);
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //intake.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}