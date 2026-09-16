package net.YaRh.ConvConf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @since 1.0.0
 */
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
	/**
	 * @deprecated Use {@link Switch#allSet()}
	 */
	@Deprecated(since = "1.2.0", forRemoval = true)
	public static boolean areAllActive() {
		return switches.stream().allMatch(Switch::get);
	}
	public static boolean allSet() {
		return switches.stream().allMatch(Switch::get);
	}
	
	private boolean value = false;
	
	/**
	 * @since 1.1.0
	 */
	private Consumer<Boolean> onChange = null;
	
	/**
	 * @since 1.1.1
	 */
	public Switch(boolean pDefault, Consumer<Boolean> onChange) {
		this.value = pDefault;
		this.onChange = onChange;
	}
	/**
	 * @since 1.0.0
	 */
	public Switch(boolean pDefault) {
		this.value = pDefault;
		Switch.add(this);
	}
	/**
	 * The default is always set to {@code false}
	 */
	public Switch() {
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
	
	@Deprecated(since = "1.1.1", forRemoval = true)
	public boolean isActive() {
		return value;
	}
	
	/**
	 * @since 1.1.1
	 */
	public boolean get() {
		return value;
	}
	
	/**
	 * @since 1.1.0
	 */
	public void onChange() {
		if (onChange != null) onChange.accept(value);
	}
	
	/**
	 * Calls the {@code onChange} handler
	 *
	 * @since 1.1.0
	 */
	public void setOnChange(Consumer<Boolean> onChange) {
		this.onChange = onChange;
	}
	
	/**
	 * @since 1.1.1
	 */
	public Consumer<Boolean> removeOnChange() {
		Consumer<Boolean> h = onChange;
		this.onChange = null;
		return h;
	}
}