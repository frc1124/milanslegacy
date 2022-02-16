package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends PIDSubsystem {

    public MotorControllerGroup motors;
    public Drive(MotorControllerGroup motors, int P, int I, int D) {
        super(new PIDController(P, I, D));
        this.motors = motors;
        
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    protected void useOutput(double output, double setpoint) {
        // TODO Auto-generated method stub

    }

    @Override
    protected double getMeasurement() {
        // TODO Auto-generated method stub
        return 0;
    }
}
