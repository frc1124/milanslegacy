
package frc.robot.subsystems;

import java.util.Properties;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Lift extends SubsystemBase {
    double prev_encoder_val;
    WPI_TalonSRX el_vader;
    WPI_TalonSRX el_trooper;
    double distance_traveled;
    double distance_from_top;
    double distance_from_bottom;
    Encoder en_coder;
    MotorControllerGroup motors;
    double el_top = 4550; // Top
    //double el_top = 3000; //Mid Safe

    double el_bottom = 100;

  public Lift(Encoder en_coder) {
    this.en_coder = en_coder;
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);

    motors = new MotorControllerGroup(el_vader, el_trooper);

    distance_traveled = en_coder.getDistance();
    //distance_from_top = Math.abs(el_top - distance_traveled);
    //distance_from_bottom = Math.abs(el_bottom - distance_traveled);
  }
  public void reset() {
    //en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
  }

  public void motor_up(double setpoint) {
    distance_traveled = en_coder.getDistance();
    System.out.println(en_coder.getDistance());  
     if (distance_traveled <= el_top) {
      motors.set(.3);
       }
       else{
        motors.set(0);
     }
  }
  
  public void motor_down(double setpoint) {
    System.out.println(en_coder.getDistance());
     if (distance_traveled >= el_bottom) {
         motors.set(-.3);
       }
       else{
         motors.set(-.3);
       }
     }
  
  public void stop() {
    el_vader.set(0);
  }
  
  public void reset_elevetor() {
    motors.set(-1);
    en_coder.reset();
  }

  public void store_val() {
    
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

