package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class Drive extends SubsystemBase{
  public static DifferentialDrive drive;
  public static WPI_TalonSRX leftFront;
  public static WPI_TalonSRX leftBack;
  public static WPI_TalonSRX rightFront;
  public static WPI_TalonSRX rightBack;

  // private static MotorControllerGroup lefts;
  // private static MotorControllerGroup rights;

  // public static Encoder leftEncoder;
  // public static Encoder rightEncoder;


  public AHRS navx; 

  public Drive() {
    leftFront = new WPI_TalonSRX(Constants.LEFTFRONT);
    rightFront = new WPI_TalonSRX(Constants.RIGHTFRONT);
    leftBack = new WPI_TalonSRX(Constants.LEFTBACK);
    rightBack = new WPI_TalonSRX(Constants.RIGHTBACK);

    // lefts = new MotorControllerGroup(leftFront, leftBack);
    // rights = new MotorControllerGroup(rightFront, rightBack);

    // leftEncoder = new Encoder(Constants.LEFTCHANNEL_A, Constants.LEFTCHANNEL_B);
    // rightEncoder = new Encoder(Constants.RIGHTCHANNEL_A, Constants.RIGHTCHANNEL_B);

    leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    rightFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    leftFront.setInverted(true);

    leftFront.config_kP(0, Constants.DRIVE_L_P);
    leftFront.config_kI(0, Constants.DRIVE_L_I);
    leftFront.config_kD(0, Constants.DRIVE_L_D);
    leftFront.config_kF(0, Constants.DRIVE_L_F);

    rightFront.config_kP(0, Constants.DRIVE_R_P);
    rightFront.config_kI(0, Constants.DRIVE_R_I);
    rightFront.config_kD(0, Constants.DRIVE_R_D);
    rightFront.config_kF(0, Constants.DRIVE_R_F);

    navx = new AHRS();

    // set modes to break
    leftFront.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    // leftBack.setNeutralMode(NeutralMode.Brake);
    // rightBack.setNeutralMode(NeutralMode.Brake);

    // 8192 ticks per rev; 3 in radius
    // ThroughBore: 1024 ticks/rev
    // CTRE: 4096 ticks/rev
    // Gear ratio 10.71:1
    // leftEncoder.setDistancePerPulse(2 * 3 * Math.PI / 1024);
    // rightEncoder.setDistancePerPulse(2 * 3 * Math.PI / 1024);
    
    drive = new DifferentialDrive(leftFront, rightFront);

    // leftBack.follow(leftFront);
    // rightBack.follow(rightFront);

  }
    public void resetEncoders() {
    // leftEncoder.reset();
    // rightEncoder.reset();
  }

  public AHRS getNavxInstance() {
    return navx;
  }

  // public double getAvgDistance() {
  //   // inches per second
  //   return (leftEncoder.getDistance() + rightEncoder.getDistance()) / 2;
  // }

  // public double getAvgVelocity() {
  //   // inches per second
  //   return (leftEncoder.getRate() + rightEncoder.getRate()) / 2;
  // }

  public void stop() {
    leftFront.set(0);
    rightFront.set(0);
  }

  public void forward() {
    leftFront.set(1);
    rightFront.set(1);
  }

  public void forward(double in) {
    leftFront.set(ControlMode.Position, toTicks(in));
    rightFront.set(ControlMode.Position, toTicks(in));
    // leftFront.set(ControlMode.Position, in);
    // rightFront.set(ControlMode.Position, in);
  }

  public void forwardAt(double inPerS) {
    leftFront.set(ControlMode.Velocity, toTicksPer100ms(inPerS));
    rightFront.set(ControlMode.Velocity, toTicksPer100ms(inPerS));
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
  public double toTicksPer100ms(double inPerS) {
    double circ = 2 * Math.PI * 3;
    double revPerS = inPerS / circ;
    double ticksPerS =  revPerS * 4096;
    return ticksPerS/10;
  }
  public double toTicks(double in) {
    double circ = 2 * Math.PI * 3;
    double rev = in / circ;
    return rev * 4096;
  }

  public void arcadeDrive(double fwd, double rot) {
      drive.arcadeDrive(Math.pow(fwd,3), Math.pow(rot,3));
  }
}
