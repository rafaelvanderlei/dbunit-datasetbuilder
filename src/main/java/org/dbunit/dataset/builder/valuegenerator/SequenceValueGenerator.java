package org.dbunit.dataset.builder.valuegenerator;

public class SequenceValueGenerator implements ValueGenerator<Long> {

	private long nextValue;
	private final long increment;
	private boolean hasFurtherValue;

	public SequenceValueGenerator(long initialValue, long increment) {
		if( increment == 0 ) {
			throw new IllegalArgumentException("Increment must not be zero.");
		}
		this.nextValue = initialValue;
		this.increment = increment;
		this.checkFurtherValue();
	}

	@Override
	public Long nextValue() {
		if( !hasFurtherValue ) {
			throw new IllegalStateException("Sequence has exceeded valid values.");
		}
		long result = this.nextValue;
		
		this.checkFurtherValue();
			
		if( this.hasFurtherValue ) {
			this.nextValue += this.increment;
		}
		
		return result;
	}

	private void checkFurtherValue() {
		if ( this.increment < 0) {
			this.hasFurtherValue = ( Math.abs( Long.MIN_VALUE - this.nextValue ) >= Math.abs( this.increment ) );
		} else {
			this.hasFurtherValue = ( Long.MAX_VALUE - this.nextValue >= this.increment );
		}
	}
}
