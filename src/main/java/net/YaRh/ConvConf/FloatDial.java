package net.YaRh.ConvConf;

/**
 * A changeable {@link Float} value with upper and lower limits
 *
 * @since 1.5.0
 */
public class FloatDial extends Attribute<Float> {
	
	private final Float minValue;
	private final Float maxValue;
	
	/**
	 * @since 1.5.0
	 */
	public FloatDial(Float value, Float minValue, Float maxValue) {
		super(value);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	/**
	 * @since 1.5.0
	 */
	public FloatDial(Float minValue, Float maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public void set(Float pValue) {
		if (!inBounds(pValue))
			throw new IllegalStateException("Attempted to set a Dial to a value outside of [%s; %s]"
					                                .formatted(minValue, maxValue));
		
		super.set(pValue);
	}
	
	public boolean inBounds(Float pValue) {
		if (pValue > maxValue) return false;
		return pValue >= minValue;
	}
}