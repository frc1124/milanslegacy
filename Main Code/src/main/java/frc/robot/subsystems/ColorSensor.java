// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.I2C;

import com.kauailabs.navx.frc.Tracer;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {

  private ColorSensorV3 colorsensor;
  private Port port;
  private ColorMatch color_matcher;
  private Color kblack;
  private Color detectedColor;
  private ColorMatchResult closest_color;
  public ColorSensor() {
    port = I2C.Port.kOnboard;
    colorsensor = new ColorSensorV3(port);
    color_matcher = new ColorMatch();
    kblack = new Color(0.0, 0.0, 0.0);
    color_matcher.addColorMatch(kblack); 
  }


  public boolean detect() {

    detectedColor = colorsensor.getColor();
    closest_color = color_matcher.matchClosestColor(detectedColor);

    if(closest_color.color == kblack) {
      return true;
    }
    else{
      return false;
    }
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
