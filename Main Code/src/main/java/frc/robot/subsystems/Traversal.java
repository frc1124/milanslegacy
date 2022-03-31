
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.MotorCommutation;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Traversal extends SubsystemBase {

    CANSparkMax motor;

  public Traversal() {
    motor = new CANSparkMax(Constants.TRAVERSAL_ID, MotorType.kBrushless);
  }

  public void motor_front() {
    motor.set(.5);
  }
  
  public void motor_back() {
    motor.set(-.5);
  }
  
  public void stop() {
    motor.set(0);

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

