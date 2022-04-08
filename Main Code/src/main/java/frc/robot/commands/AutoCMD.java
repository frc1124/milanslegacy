package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class AutoCMD  extends CommandBase{
    RobotContainer rc;
   public AutoCMD(RobotContainer rc)  {
        this.rc = rc;
   }
   double t;
   double r;
   double l;
   @Override
   public void initialize() {
       // TODO Auto-generated method stub
       super.initialize();

       t = Timer.getFPGATimestamp();
       r = rc.right.getMeasurement();
       l = rc.left.getMeasurement();
   }
   @Override
   public void execute() {
       super.execute();
       rc.shooter.on();
       if(t + 5 < Timer.getFPGATimestamp()) {
            rc.screw.On();
       }
       if(t + 7 < Timer.getFPGATimestamp()) {
        rc.left.set(.4);   
        rc.right.set(.4);
       }
      
    //    if(Math.abs(r - rc.right.getMeasurement()) >=48) {
    //     rc.right.set(0);
    //    }
    //    if(Math.abs(r - rc.left.getMeasurement()) >=48) {
    //     rc.left.set(0);
    
    //    }
       if(t+9 < Timer.getFPGATimestamp()) {
           rc.right.set(0);
           rc.left.set(0);
       }

    }
   
   @Override
   public void end(boolean interrupted) {
       // TODO Auto-generated method stub
       super.end(interrupted);
       rc.screw.Off();
    //    rc.shooter.stop();
       rc.right.set(0);
       rc.left.set(0);
   }
   @Override
   public boolean isFinished() {
       // TODO Auto-generated method stub
       return (t+15) < Timer.getFPGATimestamp();
       
   }

}