package frc.robot.commands;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class MoveCommandGroup extends ParallelCommandGroup{
    private RobotContainer rc;
    private double leftD;
    private double rightD;
    public MoveCommandGroup(double distance, RobotContainer rc) {
        super();
        this.rc = rc;
        this.leftD = leftD;
        this.rightD = rightD;

        parallel((Command) new Move(rightD, rc.rightDController, rc.right, rc.rightEncoder));
        parallel((Command) new Move(leftD, rc.leftDController, rc.left, rc.leftEncoder));

    }
    
}