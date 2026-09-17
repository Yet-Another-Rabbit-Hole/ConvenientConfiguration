package net.YaRh.ConvConf;

/**
 * A changeable double value with upper and lower limits
 *
 * @since 1.4.0
 */
public class Dial extends Attribute<Double> {
	
	private final Double minValue;
	private final Double maxValue;
	
	/**
	 * @since 1.4.0
	 */
	public Dial(Double value, Double minValue, Double maxValue) {
		super(value);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	/**
	 * @since 1.4.0
	 */
	public Dial(Double minValue, Double maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
	
	@Override
	public void set(Double pValue) {
		if (!inBounds(pValue))
			throw new IllegalStateException("Attempted to set a Dial to a value outside of [%s; %s]"
					                                .formatted(minValue, maxValue));
		
		super.set(pValue);
	}
	
	public boolean inBounds(Double pValue) {
		if (pValue > maxValue) return false;
		return pValue >= minValue;
	}
}