
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lift extends SubsystemBase {

    WPI_TalonSRX el_vader;
    WPI_TalonSRX el_trooper;
    double distance_traveled;
    double distance_from_top;
    double distance_from_bottom;
    Encoder en_coder;
    MotorControllerGroup motors;

    double el_top = 17;
    double el_bottom = 0;

  public Lift(Encoder en_coder) {
    this.en_coder = en_coder;
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);
    motors = new MotorControllerGroup(el_vader, el_trooper);
    
    en_coder.setDistancePerPulse(2*Math.PI/Constants.ENCODERRESOLUTION);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);
  }
  public void reset() {
    en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
  }

  public void motor_up(double setpoint) {

    motors.set(.4);
    // distance_traveled = en_coder.getDistance();
    // distance_from_top = Math.abs(el_top - distance_traveled);
    // distance_from_bottom = Math.abs(el_bottom - distance_traveled);
    
    // while (distance_traveled != setpoint) {
    //   if (distance_from_top < 8) {
    //     motors.set(.1);
    //   }
    //   else{
    //     motors.set(.5);
    //   }
    // }
    // motors.set(0);
  }
  
  public void motor_down(double setpoint) {
    motors.set(-.4);
    // while (distance_traveled != setpoint) {
    //   if (distance_from_bottom < 8) {
    //     motors.set(-.1);
    //   }
    //   else{
    //     motors.set(-.5);
    //   }
    // }
    // motors.set(0);
    
  }
  public void stop() {
    motors.set(0);
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

