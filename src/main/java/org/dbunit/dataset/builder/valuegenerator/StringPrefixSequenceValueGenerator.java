package org.dbunit.dataset.builder.valuegenerator;

public class StringPrefixSequenceValueGenerator implements ValueGenerator<String> {

	private final String prefix;
	private final SequenceValueGenerator sequenceValueGenerator;

	public StringPrefixSequenceValueGenerator(String prefix, long initialValue, long increment) {
		if( prefix == null || prefix.trim().isEmpty() ) {
			throw new IllegalArgumentException("Prefix must not be null or empty.");
		}
		this.prefix = prefix;
		this.sequenceValueGenerator = new SequenceValueGenerator(initialValue, increment);
	}

	@Override
	public String nextValue() {
		return this.prefix + this.sequenceValueGenerator.nextValue();
	}
}
