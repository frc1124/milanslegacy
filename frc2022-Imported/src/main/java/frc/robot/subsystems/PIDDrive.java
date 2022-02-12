package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

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

  private final static PIDController controller = new PIDController(
    Constants.DRIVE_L_P,
    Constants.DRIVE_L_I, 
    Constants.DRIVE_L_D);

  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.DRIVE_KS, Constants.Drive_KV);

  /**
   * Constructs a differential drive object. Sets the encoder distance per pulse and resets the
   * gyro.
   */
  public PIDDrive() {
    super(controller);
    navx.reset();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    motors.setInverted(true);

    // Set the distance per pulse for the drive encoders. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    encoder.setDistancePerPulse(2 * Math.PI * Constants.WHEELRADIUS / Constants.ENCODERRESOLUTION);

    encoder.reset();
  }

  /**
   * Sets the desired wheel speeds.
   *
   * @param speeds The desired wheel speeds.
   */
  public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
    motors.setVoltage(output + ff);
  }

  /**
   * Drives the robot with the given linear velocity and angular velocity.
   *
   * @param xSpeed Linear velocity in m/s.
   * @param rot Angular velocity in rad/s.
   */
  @SuppressWarnings("ParameterName")
  public void drive(double xSpeed, double rot) {
    var wheelSpeeds = kinematics.toWheelSpeeds(new ChassisSpeeds(xSpeed, 0.0, rot));
    setSpeeds(wheelSpeeds);
  }

  /** Updates the field-relative position. */
  public void updateOdometry() {
    odometry.update(
        new Rotation2d((navx.getYaw()+180)/360 * 2 * Math.PI), leftEncoder.getDistance(), rightEncoder.getDistance());
  }

  @Override
  protected void useOutput(double output, double setpoint) {
    final double ff = feedforward.calculate(setpoint);

    final double out = controller.calculate(encoder.getRate(), setpoint);
    
    motors.setVoltage(ff + out);
  }

  @Override
  protected double getMeasurement() {

  }
    
}
