
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

    double el_top = 10;
    double el_bottom = 0;

<<<<<<< HEAD
  public Lift() {
    
    en_coder = new Encoder(Constants.EL_CHANNEL_A, Constants.EL_CHANNEL_B);
=======
  public Lift(Encoder en_coder) {
    this.en_coder = en_coder;
>>>>>>> a96c71ae0a36daeb5a01f031eb12db98ef631c32
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);
<<<<<<< HEAD
    en_coder.setDistancePerPulse(0);
=======
  }
  public void reset() {
    en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
>>>>>>> a96c71ae0a36daeb5a01f031eb12db98ef631c32
  }

  public void motor_up(double setpoint) {

<<<<<<< HEAD
    distance_traveled = en_coder.getDistance();
    distance_from_top = el_top - distance_traveled;
    distance_from_bottom = el_bottom - distance_traveled;
    
    while (distance_traveled != setpoint) {

      if (distance_from_top < 4) {
        el_vader.set(.1);
      }
      else{
        el_vader.set(.5);
      }
    }
    el_vader.set(0);
  }
  
  public void motor_down(double setpoint) {
    distance_traveled = en_coder.getDistance();
    distance_from_top = el_top - distance_traveled;
    distance_from_bottom = el_bottom - distance_traveled;
    while (distance_traveled != setpoint) {
      if (distance_from_top < 4) {
        el_vader.set(-.1);
      }
      else{
        el_vader.set(-.5);
      }

    }
    el_vader.set(0);
=======
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
>>>>>>> a96c71ae0a36daeb5a01f031eb12db98ef631c32
    
  }
  public void stop() {
    el_vader.set(0);
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

