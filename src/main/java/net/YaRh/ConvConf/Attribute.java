package net.YaRh.ConvConf;

import java.util.function.Consumer;

/**
 * @since 1.0.0
 */
public final class Attribute<T> {
	
	private T value = null;
	private boolean changeable = true;
	private boolean nullable = false;
	/**
	 * @since 1.1.0
	 */
	private Consumer<T> onChange = null;
	
	/**
	 * @since 1.1.0
	 */
	@Deprecated(since = "1.2.0", forRemoval = true)
	public Attribute(T value, boolean nullable, Consumer<T> onChange) {
		this.value = value;
		this.nullable = nullable;
		this.onChange = onChange;
	}
	/**
	 * @since 1.1.0
	 */
	@Deprecated(since = "1.2.0", forRemoval = true)
	public Attribute(T value, boolean nullable) {
		this.value = value;
		this.nullable = nullable;
	}
	/**
	 * @since 1.1.0
	 */
	public Attribute(T value, Consumer<T> onChange) {
		this.value = value;
		this.onChange = onChange;
	}
	/**
	 * @since 1.0.0
	 */
	public Attribute(T value) {
		this.value = value;
	}
	/**
	 * @since 1.1.0
	 */
	public Attribute() {
		this.nullable = true;
	}
	
	/**
	 * {@code false} by default
	 */
	@Deprecated(since = "1.2.0", forRemoval = true)
	public Attribute<T> setFinal() {
		this.changeable = false;
		return this;
	}
	
	/**
	 * {@code false} by default
	 *
	 * @since 1.2.0
	 */
	public Attribute<T> immutable() {
		this.changeable = false;
		return this;
	}
	
	/**
	 * {@code false} by default
	 */
	public Attribute<T> nullable() {
		this.nullable = true;
		return this;
	}
	
	public T get() {
		return value;
	}
	/**
	 * @deprecated Use {@link Attribute#isImmutable()} instead
	 */
	@Deprecated(since = "1.2.0", forRemoval = true)
	public boolean isChangeable() {
		return changeable;
	}
	/**
	 * @since 1.2.0
	 */
	public boolean isImmutable() {
		return changeable;
	}
	public boolean isNullable() {
		return nullable;
	}
	
	public void set(T pValue) {
		if (!changeable)
			throw new IllegalStateException("You attempted to set an immutable attribute");
		
		if (!nullable && pValue == null)
			throw new IllegalStateException("You attempted to set a non nullable attribute to null");
		
		if (pValue == value) return;
		
		this.value = pValue;
		onChange();
	}
	
	/**
	 * Calls the {@code onChange} handler
	 *
	 * @since 1.1.0
	 */
	public void onChange() {
		if (onChange != null) onChange.accept(value);
	}
	
	/**
	 * @since 1.1.0
	 */
	public void setOnChange(Consumer<T> onChange) {
		this.onChange = onChange;
	}
	
	/**
	 * @since 1.1.1
	 */
	public Consumer<T> removeOnChange() {
		Consumer<T> h = onChange;
		this.onChange = null;
		return h;
	}
	
	/**
	 * @since 1.3.0
	 */
	@Override
	public String toString() {
		return value.toString();
	}
	
	/**
	 * @since 1.3.0
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Attribute<?> other)) return false;
		return value.equals(other.get());
	}
}