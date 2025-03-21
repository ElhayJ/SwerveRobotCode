// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Swerve extends SubsystemBase {
  private SwerveModule[] swerveModules;
  private SwerveDriveKinematics kinematics;
  private Pigeon2 gyro;

  public Swerve() {
    swerveModules = new SwerveModule[] {
      new SwerveModule(SwerveConstants.moduleConstants[0]),
      new SwerveModule(SwerveConstants.moduleConstants[1]),
      new SwerveModule(SwerveConstants.moduleConstants[2]),
      new SwerveModule(SwerveConstants.moduleConstants[3])
    };

    kinematics = new SwerveDriveKinematics(
      new Translation2d(0.5 * SwerveConstants.trackWidth, 0.5 * SwerveConstants.wheelBase),
      new Translation2d(0.5 * SwerveConstants.trackWidth, -0.5 * SwerveConstants.wheelBase),
      new Translation2d(-0.5 * SwerveConstants.trackWidth, 0.5 * SwerveConstants.wheelBase),
      new Translation2d(-0.5 * SwerveConstants.trackWidth, -0.5 * SwerveConstants.wheelBase)
    );

    gyro = new Pigeon2(SwerveConstants.gyroID);
  }

  public void drive(ChassisSpeeds speeds){
    ChassisSpeeds robotRelativeSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(speeds, gyro.getRotation2d());

    SwerveModuleState[] desiredStates = kinematics.toSwerveModuleStates(robotRelativeSpeeds);
    for (int i = 0; i < 4; i++)
      swerveModules[i].setDesiredState(desiredStates[i]);
  }
}
