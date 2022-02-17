// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.math.controller.PIDController;

public class ShooterSub extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public static CANSparkMax shooterMoter;
  
  //Encode
  private RelativeEncoder encoder;
  private PIDController pidController;
  public ShooterSub() {

    //initialize SparkMax and Encoder
    shooterMoter = new CANSparkMax(Constants.MOTOR_CAN_ID, MotorType.kBrushless);
    encoder = shooterMoter.getEncoder(SparkMaxRelativeEncoder.Type.kQuadrature, 4096);
    
    shooterMoter.restoreFactoryDefaults();
    
    pidController = new PIDController(Constants.kP,Constants.kI,Constants.kD);
    pidController.setIntegratorRange(-0.5, 0.5);

  }


  public void On() {
    shooterMoter.set(pidController.calculate(encoder.getPosition(), Constants.setpoint));
  }

  public void Off(){
    shooterMoter.set(0);
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
