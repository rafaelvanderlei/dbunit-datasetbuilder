package org.dbunit.dataset.builder.valuegenerator;

/**
 * Facade for constructors calls of concrete implementations for {@link ValueGenerator}.   
 */
public class ValueGeneratorMaker {

	public static <T> FixedValueGenerator<T> newFixedValueGenerator(T value) {
		return new FixedValueGenerator<T>( value );
	}
	
	public static SequenceValueGenerator newSequenceValueGenerator(long initialValue, long increment) {
		return new SequenceValueGenerator( initialValue, increment );
	}
	
	public static StringPrefixSequenceValueGenerator newStringPrefixSequenceValueGenerator(String prefix, long initialValue, long increment) {
		return new StringPrefixSequenceValueGenerator( prefix, initialValue, increment );
	}
}
