// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int LEFTFRONT = 1;
    public static final int LEFTBACK = 4;
    public static final int RIGHTFRONT = 2;
    public static final int RIGHTBACK = 3;

    public static final int LEFTCHANNEL_A = 0;
    public static final int LEFTCHANNEL_B = 1;
    public static final int RIGHTCHANNEL_A = 2;
    public static final int RIGHTCHANNEL_B = 3;

    public static final int ARCADE_STICK = 0;
    public static final int PID_TIMEOUT = 0;
    public static final int PID_TOLLERANCE = 0;

    // P val 52:100 proportion
    // public static final double DRIVE_L_P = 0.052;
    // public static final double DRIVE_L_I = 0.001;
    // public static final double DRIVE_L_D = 0.005;
    // public static final double DRIVE_R_P = 0.100;
    // public static final double DRIVE_R_I = 0.000;
    // public static final double DRIVE_R_D = 0.001;

    public static final double DRIVE_L_P = 0.052;
    public static final double DRIVE_L_I = 0.001;
    public static final double DRIVE_L_D = 0.005;
    public static final double DRIVE_R_P = 0.052;
    public static final double DRIVE_R_I = 0.001;
    public static final double DRIVE_R_D = 0.005;

    public static final double DRIVE_KS = 0;
    public static final double DRIVE_KV = 0;

    // public static final double MAXSPEED = 132; // in per second
    public static final double MAXSPEED = 84; // in per second
    public static final double MAXANGULARSPEED = 2 * Math.PI; // one rotation per second

    public static final double TRACKWIDTH = 11; // in
    public static final double WHEELRADIUS = 3; // in
    public static final int ENCODERRESOLUTION = 4096;
}
