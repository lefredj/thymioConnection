package ch.epfl.mobots;

import java.util.ArrayList;
import java.util.List;

import org.freedesktop.dbus.DBusConnection;
import org.freedesktop.dbus.exceptions.DBusException;

public class ThymioConnection {
	DBusConnection bus ;
	AsebaNetwork network;
	String node;
	private List<String> variables;
	public ThymioConnection(){
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			bus = DBusConnection.getConnection(DBusConnection.SESSION);
			network = bus.getRemoteObject("ch.epfl.mobots.Aseba", "/", AsebaNetwork.class);
		} catch (DBusException e) {
			System.err.println("thymio not found - dbus error\n");
			e.printStackTrace();
		}
		node = network.GetNodesList().get(0);
		setVariables(network.GetVariablesList(node));
		network.LoadScripts(classLoader.getResource("resources/thymioEvents.aesl").getPath());
	}
	
	public void loadScript(String filePath) {
		network.LoadScripts(filePath);
	}
	
	public String listVariables() {
		String listOfVariables = "";
		for (String string : getVariables()) {
			listOfVariables += string + "\n";
		}
		System.out.println(listOfVariables);
		return listOfVariables;
	}
	
	public List<Short> getVariable(String variable) {
		List<Short> result = network.GetVariable(node, variable );
		return result;
	}

	public void setVariable(String variable,List<Short> data) {
		network.SetVariable(node, variable,data );
	}

	public void setVariable(String variable,Short data) {
		List<Short> dataList = new ArrayList<>();
		dataList.add(data);
		network.SetVariable(node, variable,dataList );
	}
	
	public void setLeftMotorSpeed(Short speed) {
		setVariable("motor.left.target", speed);
	}

	public void setRightMotorSpeed(Short speed) {
		setVariable("motor.right.target", speed);
	}

	
	public List<Short> getProximitySensor() {
		List<Short> result = network.GetVariable(node, "prox.horizontal");
		return result;
	}
	
	public List<Short> getGroundSensorAmbiant() {
		List<Short> result = network.GetVariable(node, "prox.ground.ambiant");
		return result;
	}

	public List<Short> getGroundSensorReflected() {
		List<Short> result = network.GetVariable(node, "prox.ground.reflected");
		return result;
	}

	public List<Short> getGroundSensorDelta() {
		List<Short> result = network.GetVariable(node, "prox.ground.delta");
		return result;
	}
	
	public List<Short> getMotorLeftSpeed() {
		List<Short> result = network.GetVariable(node, "motor.left.speed");
		return result;
	}

	public List<Short> getMotorRightSpeed() {
		List<Short> result = network.GetVariable(node, "motor.right.speed");
		return result;
	}
	
	public List<Short> getAcc() {
		List<Short> result = network.GetVariable(node, "acc");
		return result;
	}

	public List<Short> getTemperature() {
		List<Short> result = network.GetVariable(node, "temperature");
		return result;
	}
	
	
	
	public void finalize() {
		System.out.println("disconnection");
		bus.disconnect();
	}

	public void setColor(int i, int j, int k) {
		List<Short> data = new ArrayList<>();
		data.add((short)i);
		data.add((short)j);
		data.add((short)k);	
		network.SendEventName("SetColor", data);		
	}

	public void setSpeed(short left, short right) {
		List<Short> data = new ArrayList<>();
		data.add(left);
		data.add(right);
		network.SendEventName("SetSpeed", data);		
	}

	public void setLeftSpeed(short left) {
		List<Short> data = new ArrayList<>();
		data.add(left);
		network.SendEventName("SetLeftSpeed", data);		
	}

	public void setRightSpeed(short right) {
		List<Short> data = new ArrayList<>();
		data.add(right);
		network.SendEventName("SetRightSpeed", data);		
	}

	public List<String> getVariables() {
		return variables;
	}

	public void setVariables(List<String> variables) {
		this.variables = variables;
	}

}
