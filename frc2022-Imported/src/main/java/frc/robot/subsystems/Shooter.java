
package frc.robot.subsystems;


import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants; 
import com.revrobotics.*;

public class Shooter extends PIDSubsystem{

  public static RelativeEncoder encoder;

  public CANSparkMax motors;

  public SimpleMotorFeedforward ff = new SimpleMotorFeedforward(Constants.SHOOT_KS, Constants.SHOOT_KV);

  public Shooter(CANSparkMax motors, RelativeEncoder encoder, PIDController controller) {
    super(controller);
    this.motors = motors;
    this.encoder = encoder;
    // set one distance unit to be a revolution
    ;
    encoder.setPosition(0);
    SmartDashboard.putNumber("Shooter RPM", encoder.getVelocity());
  }
  @Override
  public void useOutput(double output, double setpoint) {

    double feedfrwrd = ff.calculate(setpoint);


    final double out = getController().calculate(encoder.getVelocity(), setpoint);
    motors.setVoltage(MathUtil.clamp(out + feedfrwrd, -12, 12));
  }

  public void on(){
    motors.set(1);
  }

  @Override
  public double getMeasurement() {
    return encoder.getVelocity();
  }

  public void stop() {
    motors.set(0);
  }

  public void set(double x) {
    motors.set(x);
  }
}