package net.YaRh.ConvConf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
	private Consumer<Boolean> onChange = null;
	
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
		set(true);
	}
	public void disable() {
		set(false);
	}
	
	public void set(boolean pValue) {
		if (pValue == value) return;
		
		this.value = pValue;
		onChange();
	}
	
	public boolean toggle() {
		set(!value);
		return value;
	}
	
	public boolean isActive() {
		return value;
	}
	
	public void onChange() {
		if (onChange != null) onChange.accept(value);
	}
	
	/**
	 * Calls the {@code onChange} handler
	 */
	public void setOnChange(Consumer<Boolean> onChange) {
		this.onChange = onChange;
	}
}