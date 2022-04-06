
package frc.robot.subsystems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    File file;
    FileInputStream input;
    Properties prop;
    String encoder_value;
    WPI_TalonSRX el_vader;
    WPI_TalonSRX el_trooper;
    public double distance_traveled;
    public Encoder en_coder;
    MotorControllerGroup motors;
    double el_top = 4700; // Top
    //double el_top = 3000; //Mid Safe
    FileOutputStream in;
    double el_bottom = 100;

  public Lift(Encoder en_coder){
    //prop = new Properties();
    //file = new File("encoder_val.property");
    //input = new FileInputStream(file);

    this.en_coder = en_coder;
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);

    motors = new MotorControllerGroup(el_vader, el_trooper);
    distance_traveled = en_coder.getDistance();

  }
  public void reset() {
    //en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
  }

  public void motor_up(double setpoint) {
    //distance_traveled = prop.load(input);
    //input.close();
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
    distance_traveled = en_coder.getDistance();
    System.out.println(en_coder.getDistance());
     if (distance_traveled >= el_bottom) {
         motors.set(-.3);
       }
       else{
         motors.set(0);
       }
     }
  
  public void stop() {
    el_vader.set(0);
  }
  
  public void reset_elevetor() {
    motors.set(-1);
    en_coder.reset();
  }

  public void store_val() throws FileNotFoundException, IOException {
    //prop.put("encoder_val", distance_traveled);
    //in = new FileOutputStream(file);
    //prop.store(in, "Last encoder value saved");
    //in.close();

    
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

