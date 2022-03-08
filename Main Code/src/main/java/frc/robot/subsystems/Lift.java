
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

  public Lift() {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    
    en_coder = new Encoder(Constants.EL_CHANNEL_A, Constants.EL_CHANNEL_B);
=======
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
    en_coder = new Encoder(Constants.EL_A, Constants.EL_B);
>>>>>>> ecae8981a6ff07369e4910d444bd2a4057db81fa
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);
    motors = new MotorControllerGroup(el_vader, el_trooper);
    
    en_coder.setDistancePerPulse(2*Math.PI/Constants.ENCODERRESOLUTION);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    en_coder.setDistancePerPulse(0);
=======
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
    en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> ecae8981a6ff07369e4910d444bd2a4057db81fa
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
=======
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
  }

  public void motor_up(double setpoint) {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
 
    
    while (distance_traveled != setpoint) {
      distance_traveled = en_coder.getDistance();
      distance_from_top = el_top - distance_traveled;
      distance_from_bottom = el_bottom - distance_traveled;

      if (distance_from_top < 4) {
        el_vader.set(.1);
=======
    distance_traveled = en_coder.getDistance();
    distance_from_top = Math.abs(el_top - distance_traveled);
    distance_from_bottom = Math.abs(el_bottom - distance_traveled);
    
    while (distance_traveled != setpoint) {
      if (distance_from_top < 8) {
        motors.set(.1);
>>>>>>> ecae8981a6ff07369e4910d444bd2a4057db81fa
=======
    distance_traveled = en_coder.getDistance();
    distance_from_top = Math.abs(el_top - distance_traveled);
    distance_from_bottom = Math.abs(el_bottom - distance_traveled);
    
    while (distance_traveled != setpoint) {
      if (distance_from_top < 8) {
        motors.set(.1);
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
=======
    distance_traveled = en_coder.getDistance();
    distance_from_top = Math.abs(el_top - distance_traveled);
    distance_from_bottom = Math.abs(el_bottom - distance_traveled);
    
    while (distance_traveled != setpoint) {
      if (distance_from_top < 8) {
        motors.set(.1);
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
      }
      else{
        motors.set(.5);
      }
    }
    motors.set(0);
  }
  
  public void motor_down(double setpoint) {
    while (distance_traveled != setpoint) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
      distance_traveled = en_coder.getDistance();
      distance_from_top = el_top - distance_traveled;
      distance_from_bottom = el_bottom - distance_traveled;
      if (distance_from_top < 4) {
        el_vader.set(-.1);
=======
      if (distance_from_bottom < 8) {
        motors.set(-.1);
>>>>>>> ecae8981a6ff07369e4910d444bd2a4057db81fa
=======
      if (distance_from_bottom < 8) {
        motors.set(-.1);
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
=======
      if (distance_from_bottom < 8) {
        motors.set(-.1);
>>>>>>> 80fc5a7ede0ac0d128e116536bfd8a3eaf366a4e
      }
      else{
        motors.set(-.5);
      }
    }
    motors.set(0);
    
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

