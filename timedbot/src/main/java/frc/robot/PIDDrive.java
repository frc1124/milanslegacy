package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;

public class PIDDrive{

  private final MotorController leftLeader = new WPI_TalonSRX(Constants.LEFTFRONT);
  private final MotorController leftFollower = new WPI_TalonSRX(Constants.LEFTBACK);
  private final MotorController rightLeader = new WPI_TalonSRX(Constants.RIGHTFRONT);
  private final MotorController rightFollower = new WPI_TalonSRX(Constants.RIGHTBACK);

  private final Encoder leftEncoder = new Encoder( Constants.LEFTCHANNEL_A, Constants.LEFTCHANNEL_B);
  private final Encoder rightEncoder = new Encoder(Constants.RIGHTCHANNEL_A, Constants.RIGHTCHANNEL_B);

  private final MotorControllerGroup leftGroup =
      new MotorControllerGroup(leftLeader, leftFollower);
  private final MotorControllerGroup rightGroup =
      new MotorControllerGroup(rightLeader, rightFollower);

  // private final AnalogGyro gyro = new AnalogGyro(0);
  private final AHRS navx = new AHRS();

  private final PIDController leftPIDController = new PIDController(
    Constants.DRIVE_L_P,
    Constants.DRIVE_L_I, 
    Constants.DRIVE_L_D);
  private final PIDController rightPIDController = new PIDController(
    Constants.DRIVE_R_P,
    Constants.DRIVE_R_I, 
    Constants.DRIVE_R_D);
  private final DifferentialDriveKinematics kinematics =
      new DifferentialDriveKinematics(Constants.TRACKWIDTH);

  private final DifferentialDriveOdometry odometry;

  // Gains are for example purposes only - must be determined for your own robot!
  private final SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.DRIVE_KS, Constants.DRIVE_KV);

  /**
   * Constructs a differential drive object. Sets the encoder distance per pulse and resets the
   * gyro.
   */
  public PIDDrive() {
    navx.zeroYaw();

    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    leftGroup.setInverted(true);

    // Set the distance per pulse for the drive encoders. We can simply use the
    // distance traveled for one rotation of the wheel divided by the encoder
    // resolution.
    leftEncoder.setDistancePerPulse(2 * Math.PI *  Constants.WHEELRADIUS / Constants.ENCODERRESOLUTION);
    rightEncoder.setDistancePerPulse(2 * Math.PI * Constants.WHEELRADIUS / Constants.ENCODERRESOLUTION);

    leftEncoder.reset();
    rightEncoder.reset();


    odometry = new DifferentialDriveOdometry(navx.getRotation2d());
  }

  /**
   * Sets the desired wheel speeds.
   *
   * @param speeds The desired wheel speeds.
   */
  public void setSpeeds(DifferentialDriveWheelSpeeds speeds) {
    final double leftFeedforward = feedforward.calculate(speeds.leftMetersPerSecond);
    final double rightFeedforward = feedforward.calculate(speeds.rightMetersPerSecond);

    final double leftOutput =
        leftPIDController.calculate(leftEncoder.getRate(), speeds.leftMetersPerSecond);
    final double rightOutput =
        rightPIDController.calculate(rightEncoder.getRate(), speeds.rightMetersPerSecond);
    leftGroup.setVoltage(leftOutput + leftFeedforward);
    rightGroup.setVoltage(rightOutput + rightFeedforward);
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
        navx.getRotation2d(), leftEncoder.getDistance(), rightEncoder.getDistance());
  }
  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }
  public void reset() {
    resetEncoders();
    navx.reset();
  }
  public double getAngle() {
    return navx.getAngle();
  }
  public void softStop() {
    drive(0,0);
  }
  public void hardStop() {
    leftGroup.set(0);
    rightGroup.set(0);
  }
  public void stop() {
    hardStop();
  }
  public int getRightEncoder() {
    return rightEncoder.get();
  }
  public int getLeftEncoder() {
    return -leftEncoder.get();
  }
  public double getRightDistance() {
    return rightEncoder.getDistance();
  }
  public double getLeftDistance() {
    return -leftEncoder.getDistance();
  }

  public double getDistance() {
    return (rightEncoder.getDistance() + leftEncoder.getDistance())/2;
  }

  public void forward() {
    rightGroup.set(1);
    leftGroup.set(1);
  }

  public boolean turn(double angle) {
    angle -= (double) navx.getYaw();
    if (angle < -180)
      angle += 360;
    if (angle != 0) {
      drive(0, angle / 360 * 2 * Math.PI);
      return true;
    } else
      return false;
  }
}
