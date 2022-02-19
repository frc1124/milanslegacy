
package frc.robot.subsystems;


import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Shooter extends PIDSubsystem{

  private final Encoder encoder;

  private final Spark motors;

  public Shooter(Spark motors, Encoder encoder, PIDController controller) {
    super(controller);
    this.motors = motors;
    this.encoder = encoder;

    // set one distance unit to be a revolution
    encoder.setDistancePerPulse(1 / Constants.ENCODERRESOLUTION);

    encoder.reset();
  }
  @Override
  public void useOutput(double output, double setpoint) {
    final double out = getController().calculate(encoder.getRate(), setpoint);
    motors.setVoltage(MathUtil.clamp(out, -8, 8));
  }

  @Override
  public double getMeasurement() {
    return encoder.getRate();
  }

  public void stop() {
    motors.set(0);
  }

  public void set(double x) {
    motors.set(x);
  }
}
