// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;

public class RobotContainer {
  private Swerve swerve = new Swerve();
  private CommandPS5Controller controller = new CommandPS5Controller(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    swerve.setDefaultCommand(new RunCommand(() -> swerve.drive(new ChassisSpeeds(controller.getLeftY(), controller.getLeftX(), controller.getRightX())), swerve));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
