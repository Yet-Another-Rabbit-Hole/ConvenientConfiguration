package net.YaRh.ConvConf;

import java.util.ArrayList;
import java.util.List;

public final class Switch {
	
	private static final List<Switch> switches = new ArrayList<>();
	
	private static void add(Switch pSwitch) {
		switches.add(pSwitch);
	}
	
	public static void enableAll() {
		switches.forEach(Switch::enable);
	}
	public static void disableAll() {
		switches.forEach(Switch::disable);
	}
	
	public static void setAll(boolean pValue) {
		switches.forEach(swtch -> swtch.set(pValue));
	}
	
	public static void toggleAll() {
		switches.forEach(Switch::toggle);
	}
	
	public static boolean areAllActive() {
		return switches.stream().allMatch(Switch::isActive);
	}
	
	private boolean value = false;
	
	/**
	 * The default is always set to {@code false}
	 */
	public Switch() {
		Switch.add(this);
	}
	public Switch(boolean pDefault) {
		this.value = pDefault;
		Switch.add(this);
	}
	
	public void enable() {
		this.value = true;
	}
	public void disable() {
		this.value = false;
	}
	
	public void set(boolean pValue) {
		this.value = pValue;
	}
	
	public boolean toggle() {
		this.value = !value;
		return value;
	}
	
	public boolean isActive() {
		return value;
	}
}