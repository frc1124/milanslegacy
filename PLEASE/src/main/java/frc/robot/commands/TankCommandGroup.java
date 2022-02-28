package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.RobotContainer;

public class TankCommandGroup extends ParallelCommandGroup{
    private RobotContainer rc;
    private double leftV;
    private double rightV;
    public TankCommandGroup(double leftV, double rightV, RobotContainer rc) {
        super();
        this.rc = rc;
        this.leftV = leftV;
        this.rightV = rightV;

        addCommands((Command) new Tank(rightV, rc.rightVController, rc.right));
        addCommands((Command) new Tank(leftV, rc.leftVController, rc.left));
    }

}
    
// package frc.robot.commands;

// import edu.wpi.first.wpilibj.RobotController;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.CommandGroupBase;
// import frc.robot.RobotContainer;

// public class TankCommandGroup extends CommandGroupBase{
//     private RobotContainer rc;
//     private double leftV;
//     private double rightV;
//     public TankCommandGroup(double leftV, double rightV, RobotContainer rc) {
//         super();
//         this.rc = rc;
//         this.leftV = leftV;
//         this.rightV = rightV;
//     }

//     @Override
//     public void addCommands(Command... commands) {
//         parallel((Command) new Tank(rightV, rc.rightController, rc.right));
//         parallel((Command) new Tank(leftV, rc.leftController, rc.left));
//     }
    
// }
