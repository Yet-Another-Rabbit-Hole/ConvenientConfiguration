package net.YaRh.ConvConf;

import java.util.function.Consumer;

/**
 * A parameter that can be provided with a default up until it is set via {@link OverridableDefault#set(Object)},
 * in which case further defaults will be ignored
 *
 * @since 3.2.0
 */
public class OverridableDefault<T> extends Attribute<T> {
	
	private boolean receiveDefault = true;
	
	private void updateDefault(T newDefault) {
		if (receiveDefault) this.value = newDefault;
	}
	
	/**
	 * Returns the function used to provide the default
	 */
	public Consumer<T> updater() {
		if (receiveDefault) return this::updateDefault;
		return t -> {};
	}
	
	@Override
	public void set(T pValue) {
		super.set(pValue);
		this.receiveDefault = false;
	}
}