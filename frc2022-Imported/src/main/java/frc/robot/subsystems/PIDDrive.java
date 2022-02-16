package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class PIDDrive extends PIDSubsystem{

  private final Encoder encoder;

  private final MotorControllerGroup motors;
  

  // private final AnalogGyro gyro = new AnalogGyro(0);
  private final AHRS navx = new AHRS();

  
  private PIDController controller;

  /**
   * Constructs a differential drive object. Sets the encoder distance per pulse and resets the
   * gyro.
   */
  public PIDDrive(MotorControllerGroup motors, Encoder encoder, PIDController controller, boolean isLeft) {
    super(controller);
    this.controller = controller;
    this.motors = motors;
    this.encoder = encoder;
    navx.reset();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    if(isLeft) {
      motors.setInverted(true);
    }

    // Set the distance per pulse for the drive encoders. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    encoder.setDistancePerPulse(2 * Math.PI * Constants.WHEELRADIUS / Constants.ENCODERRESOLUTION);

    encoder.reset();
  }


  public AHRS getNavxInstance() {
    return navx;
  }



  @Override
  public void useOutput(double output, double setpoint) {
    final double out = controller.calculate(encoder.getDistance(), setpoint);
    motors.setVoltage(MathUtil.clamp(out, -8, 8));
  }

  @Override
  public double getMeasurement() {
    return encoder.getDistance();
  }
  public void useOutputV(double output, double setpoint) {
    final double out = controller.calculate(encoder.getRate(), setpoint);
    motors.setVoltage(MathUtil.clamp(out, -8, 8));
  }


  public void stop() {
    motors.set(0);
  }
  public double getMeasurementV() {
    return encoder.getRate();
  }
    
  public void set(double x) {
    motors.set(x);
  }
}
