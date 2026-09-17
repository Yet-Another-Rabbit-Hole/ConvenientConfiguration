package net.YaRh.ConvConf;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @since 1.4.0
 */
public class SwitchBox {
	
	private final List<Switch> switches = new ArrayList<>();
	
	void add(Switch pSwitch) {
		switches.add(pSwitch);
	}
	
	public void enableAll() {
		switches.forEach(Switch::enable);
	}
	public void disableAll() {
		switches.forEach(Switch::disable);
	}
	
	public void setAll(boolean pValue) {
		switches.forEach(swtch -> swtch.set(pValue));
	}
	public void toggleAll() {
		switches.forEach(Switch::toggle);
	}
	public boolean allSet() {
		return switches.stream().allMatch(Switch::get);
	}
	
	public Switch add(boolean pDefault, Consumer<Boolean> onChange) {
		return new Switch(pDefault, onChange).withBox(this);
	}
	public Switch add(boolean pDefault) {
		return new Switch(pDefault).withBox(this);
	}
	public Switch add() {
		return new Switch().withBox(this);
	}
}