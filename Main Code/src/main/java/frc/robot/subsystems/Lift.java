
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

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    double distance_from_top;
    double distance_from_bottom;

    public Encoder en_coder;
    MotorControllerGroup motors;
    //double el_top = 4700; // Real Top
    double el_top = 4940; // safe top
    FileOutputStream in;
    double el_bottom = 250;

  public Lift(Encoder en_coder){
    prop = new Properties();
    file = new File("C:\\Users\\sijav\\Documents\\GitHub\\milanslegacy\\Main Code\\src\\main\\encoder_value.property");

    this.en_coder = en_coder;
    el_vader = new WPI_TalonSRX(Constants.EL_LEADER);
    el_trooper = new WPI_TalonSRX(Constants.El_FOLLOWER);

    el_vader.setNeutralMode(NeutralMode.Brake);
    el_trooper.follow(el_vader);

    motors = new MotorControllerGroup(el_vader, el_trooper);

    distance_traveled = en_coder.getDistance();
    distance_from_bottom = distance_traveled;


  }
  public void reset() {
    //en_coder.reset();
  }
  public double getDistance() {
    return en_coder.getDistance();
  }
  
  public void motor_up(double setpoint) {
    //input = new FileInputStream(file);
    //distance_traveled = prop.load(input);
    //input.close();
    distance_traveled = en_coder.getDistance();
    distance_from_top = Math.abs(distance_traveled - el_top);
    System.out.println(en_coder.getDistance());  
     if (distance_traveled <= el_top) {
       if (distance_from_top <= 500) {
         motors.set(.5);
       } else {
        motors.set(1);
       }
      }
       else{
        motors.set(0);
     }
  }
  
  public void motor_down(double setpoint) {
    SmartDashboard.putNumber("Elevator Speed", en_coder.getRate());
    distance_traveled = en_coder.getDistance();
    System.out.println(en_coder.getDistance());
     if (distance_traveled >= el_bottom) {
       if (distance_from_bottom <= 1000) {
         motors.set(-.5);
       } else {
        motors.set(-1);
       }
       }
       else{
         motors.set(0);
       }
     }
  
  public void stop() {
    el_vader.set(0);
  }
  
  public void manual_down() {
    motors.set(-1);
    en_coder.reset();
  }

  public void manual_up() {
    motors.set(1);
    en_coder.reset();
  }

  public void store_val() throws FileNotFoundException, IOException {
    prop.put("encoder_val", distance_traveled);
    in = new FileOutputStream(file);
    prop.store(in, "Last encoder value saved");
    in.close();
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

