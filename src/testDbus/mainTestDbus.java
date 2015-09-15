package testDbus;

import java.util.List;

import org.freedesktop.dbus.exceptions.DBusException;

import ch.epfl.mobots.ThymioConnection;

public class mainTestDbus {

	public static void main(String[] args) throws DBusException, InterruptedException {

		ThymioConnection thymioConnection = new ThymioConnection();
		thymioConnection.listVariables();
		
//		thymioConnection.loadScript("test-medulla.aesl");
//		thymioConnection.setColor((short),(short)0,(short)0);
		
//		for (short r = 0; r < 255; r+=5) {
//			for (short g = 0; g < 255; g+=5) {
//					thymioConnection.setColor(r,g,(short)0);
//					Thread.sleep(100);
//			}
//		}
//		thymioConnection.setColor((short)255,(short)0,(short)255);
//
//		thymioConnection.setRightSpeed((short)100);
		thymioConnection.setSpeed((short)0,(short)0);

		
		Short u = 0;

		for (int i = 0; i < 1000; i++) {
			thymioConnection.setVariable(thymioConnection.getVariables().get(6), u);;
			u++;
			for (String variable : thymioConnection.getVariables()) {
				System.out.print(variable);
				List<Short> data = thymioConnection.getVariable(variable);
				for (Short short1 : data) {
					System.out.print(" " + short1);
				}
				System.out.println();
			}
			Thread.sleep(500);
			System.out.println();
		}
		thymioConnection.setLeftMotorSpeed((short)0);

		//		
		//		thymioConnection.setVariable("motor.left.target", (short) 50);
		//		List<Short> motorLeftSpeed = thymioConnection.getVariable("motor.left.speed" );
		//		for (Short short1 : motorLeftSpeed) {
		//			System.out.println(short1);
		//		}
		//		Thread.sleep(1000);
		//		motorLeftSpeed = thymioConnection.getVariable("motor.left.speed" );
		//		for (Short short1 : motorLeftSpeed) {
		//			System.out.println(short1);
		//		}
		//		thymioConnection.setVariable("motor.left.target", (short) 0);
		//		Thread.sleep(1000);
		//		motorLeftSpeed = thymioConnection.getVariable("motor.left.speed" );
		//		for (Short short1 : motorLeftSpeed) {
		//			System.out.println(short1);
		//		}

		thymioConnection.finalize();

		//		conn.disconnect();
	}

}
