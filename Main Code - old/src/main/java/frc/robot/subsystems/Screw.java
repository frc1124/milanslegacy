// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Screw extends SubsystemBase {
  private static CANSparkMax screwMotor;

  public Screw() {
<<<<<<< HEAD
    screwMotor = new CANSparkMax(Constants.SCREW_ID, MotorType.kBrushless);
=======
    screwMotor = new CANSparkMax(Constants.SCREW_PORT, MotorType.kBrushless);
>>>>>>> a96c71ae0a36daeb5a01f031eb12db98ef631c32
  }

  public void On() {
    screwMotor.set(.2);
  }
  public void back() {
    screwMotor.set(-.2);
  }

  public void Off() {
    screwMotor.set(0);

























































































































































































































    
    // among us
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
