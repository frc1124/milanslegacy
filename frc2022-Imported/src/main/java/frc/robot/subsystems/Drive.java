// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class Drive extends SubsystemBase {
  private static DifferentialDrive drive;
  private static WPI_TalonSRX leftFront;
  private static WPI_TalonSRX leftBack;
  private static WPI_TalonSRX rightFront;
  private static WPI_TalonSRX rightBack;

  public static Encoder leftEncoder;
  public static Encoder rightEncoder;

  public AHRS navx;

  /** Creates a new ExampleSubsystem. */
  public Drive() {

    leftFront.configFactoryDefault();
    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 
      0, // only using 1 feedback loop
      Constants.PID_TIMEOUT);
    leftFront.configNominalOutputForward(0, Constants.PID_TIMEOUT);
		leftFront.configNominalOutputReverse(0, Constants.PID_TIMEOUT);
		leftFront.configPeakOutputForward(1, Constants.PID_TIMEOUT);
		leftFront.configPeakOutputReverse(-1, Constants.PID_TIMEOUT);

    leftFront.configAllowableClosedloopError(Constants.PID_TOLLERANCE, 0, Constants.PID_TIMEOUT);

    /**
		 * Grab the 360 degree position of the MagEncoder's absolute
		 * position, and intitally set the relative sensor to match.
		 */
		int absolutePosition = leftFront.getSensorCollection().getPulseWidthPosition();

		/* Mask out overflows, keep bottom 12 bits */
    // Multiply by -1 if sensorPhase and if inverted. If both, dont do anything
		absolutePosition &= 0xFFF;
		
		/* Set the quadrature (relative) sensor to match absolute */
		leftFront.setSelectedSensorPosition(absolutePosition, 0, Constants.PID_TIMEOUT);
    		/* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
		leftFront.config_kF(0, Constants.DRIVE_F, Constants.PID_TIMEOUT);
		leftFront.config_kP(0, Constants.DRIVE_P, Constants.PID_TIMEOUT);
		leftFront.config_kI(0, Constants.DRIVE_I, Constants.PID_TIMEOUT);
		leftFront.config_kD(0, Constants.DRIVE_D, Constants.PID_TIMEOUT);

    leftFront = new WPI_TalonSRX(Constants.LEFTFRONT);
    rightFront = new WPI_TalonSRX(Constants.RIGHTFRONT);
    leftBack = new WPI_TalonSRX(Constants.LEFTFRONT);
    rightBack = new WPI_TalonSRX(Constants.RIGHTFRONT);

    leftFront.setInverted(true);
    leftBack.setInverted(true);

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);

    navx = new AHRS();

    // set modes to break
    leftFront.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    leftBack.setNeutralMode(NeutralMode.Brake);
    rightBack.setNeutralMode(NeutralMode.Brake);

    // 8192 ticks per rev; 6 in diameter
    leftEncoder.setDistancePerPulse(2 * 3 * Math.PI / 2048);
    rightEncoder.setDistancePerPulse(2 * 3 * Math.PI / 2048);
    
    leftEncoder = new Encoder(Constants.LEFTCHANNEL_A, Constants.LEFTCHANNEL_B);
    rightEncoder = new Encoder(Constants.RIGHTCHANNEL_A, Constants.RIGHTCHANNEL_B);
    drive = new DifferentialDrive(leftFront, rightBack);
  }


  public void resetEncoders() {
    leftEncoder.reset();
    rightEncoder.reset();
  }

  public AHRS getNavxInstance() {
    return navx;
  }

  public double getAvgDistance() {
    // inches per second
    return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  }

  public double getAvgVelocity() {
    // inches per second
    return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
  }

  public void stop() {
    leftFront.set(0);
    rightFront.set(0);
  }

  public void forward() {
    leftFront.set(1);
    rightFront.set(1);
  }

  public void backward() {
    leftFront.set(-1);
    rightFront.set(-1);
  }

  public boolean turn(double angle) {
    angle -= (double) navx.getYaw();
    if (angle < -180)
      angle += 360;
    if (angle != 0) {
      arcadeDrive(0, angle / 360);
      return true;
    } else
      return false;
  }

  public void arcadeDrive(double fwd, double rot) {
      drive.arcadeDrive(fwd, rot);
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
