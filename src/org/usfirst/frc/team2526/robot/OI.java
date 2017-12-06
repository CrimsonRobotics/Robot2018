package org.usfirst.frc.team2526.robot;

import org.usfirst.frc.team2526.robot.commands.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/*
	 * DRIVER CONTROLS
	 */
	private Joystick driverLeft = new Joystick(0);
	private Joystick driverRight = new Joystick(1);
	private Button highGear = new JoystickButton(driverLeft, 2);
	/*
	 * CO DRIVER CONTROLS
	 */
	private Joystick coDriver = new Joystick(2);
	public OI(){
		/*
		 * DRIVER COMMANDS
		 */
		highGear.whileHeld(new Shift());
		/*
		 * CO DRIVER COMMANDS
		 */
		}
	public Joystick getDriverLeft(){
		return driverLeft;
	}
	public Joystick getDriverRight(){
		return driverRight;
	}
	public Joystick getCoDriver(){
		return coDriver;
	}
}
