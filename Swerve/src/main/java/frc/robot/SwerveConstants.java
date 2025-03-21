package frc.robot;

public class SwerveConstants{
    public static final double maxDriveSpeed = 5;
    public static final double kP = 1;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final boolean driveInverted = false; 
    public static final boolean angleInverted = false;
    public static final double trackWidth = 0.7;
    public static final double wheelBase = 0.7;
    public static final int gyroID = 3;

    public static final SwerveModuleConstants[] moduleConstants = {
        new SwerveModuleConstants(10, 11, 6),
        new SwerveModuleConstants(12, 13, 7),
        new SwerveModuleConstants(14, 15, 8),
        new SwerveModuleConstants(16, 17, 9)
    };
}
