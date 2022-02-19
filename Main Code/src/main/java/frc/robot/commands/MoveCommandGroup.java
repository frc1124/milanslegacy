/*
package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.RobotContainer;

public class MoveCommandGroup extends CommandGroupBase{
    private RobotContainer rc;
    private double distance;
    public MoveCommandGroup(double distance, RobotContainer rc) {
        this.rc = rc;
        this.distance = distance;

    }

    @Override
    public void addCommands(Command... commands) {

        parallel((Command) new Move(distance, rc.rightDController, rc.right));
        parallel((Command) new Move(distance, rc.leftDController, rc.left));
        
    }
    
}

*/
