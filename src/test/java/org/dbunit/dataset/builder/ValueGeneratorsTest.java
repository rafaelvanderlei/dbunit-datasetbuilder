package org.dbunit.dataset.builder;

import org.dbunit.dataset.builder.valuegenerator.FixedValueGenerator;
import org.dbunit.dataset.builder.valuegenerator.SequenceValueGenerator;
import org.dbunit.dataset.builder.valuegenerator.StringPrefixSequenceValueGenerator;
import org.junit.Assert;
import org.junit.Test;


public class ValueGeneratorsTest {
	
	public static class FixedValueGeneratorTest {

		@Test
		public void nextValueShouldAlwaysReturnFixedValue() {
			FixedValueGenerator<String> fixedValueGenerator = new FixedValueGenerator<String>("fixed_value");
			//Yeah, 5 is a magic number!
			for( int i = 0; i < 5; i++ ) {
				Assert.assertEquals("fixed_value", fixedValueGenerator.nextValue());
			}
		}
		
		@Test
		public void nextValueWithNullShouldReturnNull() {
			FixedValueGenerator<String> fixedValueGenerator = new FixedValueGenerator<String>(null);
			Assert.assertNull( fixedValueGenerator.nextValue() );
		}
	}
	
	public static class SequenceValueGeneratorTest {

		@Test(expected=IllegalArgumentException.class)
		public void sequenceGeneratorWithIncrementZero() {
			new SequenceValueGenerator( 1, 0 );
		}
		
		@Test
		public void sequenceGeneratorShouldAlwaysReturnNextIncrement() {
			SequenceValueGenerator fixedValueGenerator = new SequenceValueGenerator( 1, 5 );
			Assert.assertEquals(1, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(6, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(11, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(16, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(21, fixedValueGenerator.nextValue().longValue());
		}
		
		@Test
		public void sequenceGeneratorAllowsNegativeStartAndNegativeIncrement() {
			SequenceValueGenerator fixedValueGenerator = new SequenceValueGenerator( -1, -5 );
			Assert.assertEquals(-1, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(-6, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(-11, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(-16, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(-21, fixedValueGenerator.nextValue().longValue());
		}
		
		@Test(expected=IllegalStateException.class)
		public void sequenceGeneratorPositiveLimit() {
			SequenceValueGenerator fixedValueGenerator = new SequenceValueGenerator( Long.MAX_VALUE - 3, 1 );
			Assert.assertEquals(Long.MAX_VALUE - 3, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MAX_VALUE - 2, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MAX_VALUE - 1, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MAX_VALUE, fixedValueGenerator.nextValue().longValue());
			fixedValueGenerator.nextValue().longValue(); //Must throw exception.
		}
		
		@Test(expected=IllegalStateException.class)
		public void sequenceGeneratorNegativeLimit() {
			SequenceValueGenerator fixedValueGenerator = new SequenceValueGenerator( Long.MIN_VALUE + 3, -1 );
			Assert.assertEquals(Long.MIN_VALUE + 3, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MIN_VALUE + 2, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MIN_VALUE + 1, fixedValueGenerator.nextValue().longValue());
			Assert.assertEquals(Long.MIN_VALUE, fixedValueGenerator.nextValue().longValue());
			fixedValueGenerator.nextValue().longValue(); //Must throw exception.
		}
	}
	
	public static class StringPrefixSequenceValueGeneratorTest {

		@Test(expected=IllegalArgumentException.class)
		public void stringPrefixSequenceGeneratorWithNullPrefix() {
			new StringPrefixSequenceValueGenerator( null, 1, 0 );
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void stringPrefixSequenceGeneratorWithEmptyPrefix() {
			new StringPrefixSequenceValueGenerator( "", 1, 0 );
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void stringPrefixSequenceGeneratorWithTrimEmptyPrefix() {
			new StringPrefixSequenceValueGenerator( " ", 1, 0 );
		}
		
		@Test
		public void stringPrefixSequenceGeneratorShouldAlwaysReturnNextIncrement() {
			StringPrefixSequenceValueGenerator fixedValueGenerator = new StringPrefixSequenceValueGenerator( "Prefix_", 1, 5 );
			Assert.assertEquals( "Prefix_1", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_6", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_11", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_16", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_21", fixedValueGenerator.nextValue() );
		}
		
		@Test
		public void stringPrefixSequenceGeneratorAllowsNegative() {
			StringPrefixSequenceValueGenerator fixedValueGenerator = new StringPrefixSequenceValueGenerator( "Prefix_", -1, -5 );
			Assert.assertEquals( "Prefix_-1", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_-6", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_-11", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_-16", fixedValueGenerator.nextValue() );
			Assert.assertEquals( "Prefix_-21", fixedValueGenerator.nextValue() );
		}
	}
}
