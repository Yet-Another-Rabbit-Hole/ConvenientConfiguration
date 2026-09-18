package net.YaRh.ConvConf;

/**
 * An {@link Attribute} with the ability to be overridden
 * either with a {@linkplain Overridable#override(Object) separate value} or
 * the {@linkplain Overridable#Overridable(Object) value it was first initialized with}
 * until being {@linkplain Overridable#free() freed}
 */
public class Overridable<T> extends Attribute<T> {
	
	private boolean overridden = false;
	private T override = null;
	
	/**
	 * Sets the base override value to the given, otherwise default is {@code null}
	 */
	public Overridable(T value) {
		super(value);
		override = value;
	}
	
	/**
	 * Overrides the value with the given value
	 * <p>
	 * The given value will be the overriding value moving forward
	 */
	public void override(T override) {
		this.override = override;
		this.overridden = true;
	}
	
	/**
	 * Overrides the value with the last overriding value or the initial value if not overwritten before.
	 */
	public void override() {
		this.overridden = true;
	}
	
	/**
	 * Frees the output to the {@linkplain Attribute#set(Object) settable value}
	 */
	public void free() {
		this.overridden = false;
	}
	
	@Override
	public T get() {
		if (overridden) return override;
		return super.get();
	}
}