package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.subsystems.Shifter;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2526.robot.commands.groups.TimeTurn;
import org.usfirst.frc.team2526.robot.commands.test.AutoCommandGroup;
import org.usfirst.frc.team2526.robot.commands.test.TestSpeedDriveCommand;
import org.usfirst.frc.team2526.robot.commands.test.TimeDrive;
import org.usfirst.frc.team2526.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2526.robot.commands.DoNothing;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	/*
	 * SENSORS
	 */
	//Gyroscope
	public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
	/*
	 * SUBSYSTEMS
	 */
	//public static edu.wpi.first.wpilibj.Compressor Compressor;
	//DriveTrain Subsystem
	public static final DriveTrain driveTrain = new DriveTrain(RobotMap.DRIVETRAIN_FRONTLEFT, RobotMap.DRIVETRAIN_BACKLEFT, RobotMap.DRIVETRAIN_FRONTRIGHT, RobotMap.DRIVETRAIN_BACKRIGHT, RobotMap.DRIVETRAIN_GAINS_LEFT,RobotMap.DRIVETRAIN_GAINS_RIGHT);
	//Shifter Subsystem
	public static final Shifter shifter = new Shifter(RobotMap.CHANNEL);
	
	/*
	 * OI CONTROLS
	 */
	//OI
	public static final OI oi = new OI();
	/*
	 * AUTONOMOUS AND SMARTDASHBOARD
	 */
	//AutonomousCommand
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Autonomous mode", chooser);
		chooser.addObject("DriveForward", new TimeDrive(5, .25, -0.25));
		chooser.addObject("Time Turn Blue Boiler", new TimeTurn(.5, -.3));
		chooser.addDefault("Time Turn Red Boiler", new TimeTurn(.3, -.5));
		chooser.addObject("GearDrop", new AutoCommandGroup());
		chooser.addObject("Test Speed Drive", new TestSpeedDriveCommand(200));
		chooser.addObject("Do Nothing", new DoNothing());
		//new Compressor(0).start();
		gyro.calibrate();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = new MotionProfileDriver()
//		autonomousCommand = new TestSpeedDriveCommand(25);
//		autonomousCommand = new AutoCommandGroup(25);
		//autonomousCommand = chooser.getSelected();
		//autonomousCommand = new TestSpeedDriveCommand(200);
//		autonomousCommand = new AutoCommandGroup();
		autonomousCommand = (Command) chooser.getSelected();
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}