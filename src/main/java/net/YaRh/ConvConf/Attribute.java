package net.YaRh.ConvConf;

import java.util.function.Consumer;

public final class Attribute<T> {
	
	private T value = null;
	private boolean changeable = true;
	private boolean nullable = false;
	private Consumer<T> onChange = null;
	
	public Attribute(T value, boolean nullable, Consumer<T> onChange) {
		this.value = value;
		this.nullable = nullable;
		this.onChange = onChange;
	}
	public Attribute(T value, boolean nullable) {
		this.value = value;
		this.nullable = nullable;
	}
	public Attribute(T value, Consumer<T> onChange) {
		this.value = value;
		this.onChange = onChange;
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
	
	public void setOnChange(Consumer<T> onChange) {
		this.onChange = onChange;
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
	
	public void set(T pValue) {
		if (!changeable)
			throw new IllegalStateException("You attempted to set an unchangeable attribute");
		
		if (!nullable && value == null)
			throw new IllegalStateException("You attempted to set a non nullable attribute to null");
		
		if (pValue == value) return;
		
		this.value = pValue;
		onChange();
	}
	
	/**
	 * Calls the {@code onChange} handler
	 */
	public void onChange() {
		if (onChange != null) onChange.accept(value);
	}
}