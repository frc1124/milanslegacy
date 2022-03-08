// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int LEFTFRONT =  2;
    public static final int LEFTBACK =   3;
    public static final int RIGHTFRONT = 1;
    public static final int RIGHTBACK =  4;

    public static final int LIFTLEADER =  5;
    public static final int LIFTFOLLOWER =  6;

    public static final int LEFTCHANNEL_A = 0;
    public static final int LEFTCHANNEL_B = 1;
    public static final int RIGHTCHANNEL_A = 2;
    public static final int RIGHTCHANNEL_B = 3;

    public static final int PID_TIMEOUT = 0;
    public static final int PID_TOLLERANCE = 0;

    public static final double VEL_L_P = 0.2;
    public static final double VEL_L_I = 0.002;
    public static final double VEL_L_D = 0;

    public static final double VEL_R_P = 0.097;
    public static final double VEL_R_I = 0.065;
    public static final double VEL_R_D = 0;

    public static final double DIST_L_P = 0.2;
    public static final double DIST_L_I = 0;
    public static final double DIST_L_D = 0;

    public static final double DIST_R_P = 0.1;
    public static final double DIST_R_I = 1;
    public static final double DIST_R_D = .5;

    public static final double SHOOT_POINT = 1354;
    public static final double SHOOT_P = 0.0001;
    public static final double SHOOT_I = 0;
    public static final double SHOOT_D = 0;

    public static final double SHOOT_KS = .2;

    public static final double SHOOT_KV = .001919;
    //.00192
    public static final double DRIVE_KS = 0;
    public static final double DRIVE_KV = 0;

    public static final double MAXSPEED = 10.0 * 12; // in per second
    public static final double MAXANGULARSPEED = 2 * Math.PI; // one rotation per second

    public static final double TRACKWIDTH = 11; // in
    public static final double WHEELRADIUS = 3; // in
    public static final int ENCODERRESOLUTION = 4096;

    public static final int EL_A = 1;
    public static final int EL_B = 1;

    public static final int EL_LEADER = 0;
    public static final int El_FOLLOWER = 0;

    public static final int BOTTOM_SWITCH = 0;
    public static final int TOP_SWITCH = 0;

    public static final double Lift_POINT = 10;


    // SparkMax IDS
    public static final int INTAKE_ID = 12;
    public static final int SCREW_ID = 11;
    public static final int SHOOTER_ID = 13;


}
