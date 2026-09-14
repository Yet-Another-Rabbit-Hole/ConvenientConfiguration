package net.YaRh.ConvConf;

public final class Attribute<T> {
	
	private T value = null;
	private boolean changeable = true;
	private boolean nullable = false;
	
	public Attribute(T value, boolean changeable, boolean nullable) {
		this.value = value;
		this.changeable = changeable;
		this.nullable = nullable;
	}
	public Attribute(T value, boolean changeable) {
		this.value = value;
		this.changeable = changeable;
	}
	public Attribute(T value) {
		this.value = value;
	}
	public Attribute() {}
	
	public void setFinal() {
		this.changeable = false;
	}
	
	/**
	 * {@code false} by default
	 */
	public void nullable() {
		this.nullable = true;
	}
	
	public T get() {
		return value;
	}
	public boolean isChangeable() {
		return changeable;
	}
	public boolean isNullable() {
		return nullable;
	}
	
	public void set(T value) {
		if (!changeable)
			throw new IllegalStateException("You attempted to set an unchangeable attribute");
		
		if (!nullable && value == null)
			throw new IllegalStateException("You attempted to set a non nullable attribute to null");
			
		this.value = value;
	}
}