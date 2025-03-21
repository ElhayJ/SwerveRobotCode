package frc.robot;

import static edu.wpi.first.units.Units.Radians;

import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveModule {
    private TalonFX driveMotor;
    private TalonFX angleMotor;
    private CANcoder canCoder;

    public SwerveModule(SwerveModuleConstants constants){
        driveMotor = new TalonFX(constants.driveMotorID);
        angleMotor = new TalonFX(constants.angleMotorID);
        canCoder = new CANcoder(constants.canCoderID);

        driveMotor.getConfigurator().apply(new TalonFXConfiguration()
        .withMotorOutput(new MotorOutputConfigs()
        .withInverted(SwerveConstants.driveInverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive)));

        angleMotor.getConfigurator().apply(new TalonFXConfiguration()
        .withMotorOutput(new MotorOutputConfigs()
        .withInverted(SwerveConstants.angleInverted ? InvertedValue.Clockwise_Positive : InvertedValue.CounterClockwise_Positive))
        .withSlot0(new Slot0Configs().withKP(SwerveConstants.kP).withKI(SwerveConstants.kI).withKD(SwerveConstants.kD))
        .withFeedback(new FeedbackConfigs().withSensorToMechanismRatio(1 / (Math.PI * 2))));

        angleMotor.setPosition(canCoder.getAbsolutePosition(true).getValue().in(Radians));
    }

    public void setDesiredState(SwerveModuleState desiredState){
        driveMotor.set(desiredState.speedMetersPerSecond / SwerveConstants.maxDriveSpeed);
        angleMotor.setControl(new PositionVoltage(desiredState.angle.getRadians()));
    }
}
