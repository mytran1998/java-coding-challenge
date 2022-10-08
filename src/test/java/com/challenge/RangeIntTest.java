package com.challenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.challenge.models.Range;

class RangeIntTest {

	@Test
	void testInputNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(null, null);
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputLowerNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(null, 10);
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputUpperNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(10, null);
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputMaxValue() {
		Range<Integer> range = Range.of(Integer.MAX_VALUE, Integer.MAX_VALUE);
		assertTrue(range.contains(Integer.MAX_VALUE));
	}

	@Test
	void testInputLowerMaxValue() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(Integer.MAX_VALUE, 0);
		}, "Lower bound no bigger than upper bound");
	}

	@Test
	void testInputUpperMaxValue() {
		Range<Integer> range = Range.of(0, Integer.MAX_VALUE);
		assertTrue(range.contains(0));
		assertTrue(range.contains(Integer.MAX_VALUE));
	}

	@Test
	void testInputLowerGraterThanUpper() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(18, 16);
		}, "Lower bound no bigger than upper bound");
	}
	
	@Test
	void testOfMethod() {
		Range<Integer> rangeTest = Range.of(16, 18);
		assertTrue(rangeTest.contains(16));
		assertTrue(rangeTest.contains(18));
		assertFalse(rangeTest.contains(5));
		assertEquals(16, rangeTest.getLowerBound());
		assertEquals(18, rangeTest.getUpperBound());
	}

	@Test
	void testOpen() {
		Range<Integer> rangeTest = Range.open(5, 7);
		assertFalse(rangeTest.contains(5));
		assertFalse(rangeTest.contains(7));
		
		Range<Integer> rangeTest2 = Range.open(4, 4);
		assertFalse(rangeTest2.contains(3));
		assertFalse(rangeTest2.contains(4));
		assertFalse(rangeTest2.contains(5));
		assertEquals(4, (int) rangeTest2.getLowerBound());
		assertEquals(4, (int) rangeTest2.getUpperBound());
	}

	@Test
	void testClosed() {
		Range<Integer> rangeTest = Range.closed(5, 7);
		assertTrue(rangeTest.contains(5));
		assertTrue(rangeTest.contains(7));
		
		Range<Integer> rangeTest2 = Range.closed(4, 4);
		assertFalse(rangeTest2.contains(3));
		assertTrue(rangeTest2.contains(4));
		assertFalse(rangeTest2.contains(5));
		assertEquals(4, (int) rangeTest2.getLowerBound());
		assertEquals(4, (int) rangeTest2.getUpperBound());
	}

	@Test
	void testOpenClosed() {
		Range<Integer> rangeTest = Range.openClosed(5, 7);
		assertFalse(rangeTest.contains(5));
		assertTrue(rangeTest.contains(7));

		Range<Integer> rangeTest2 = Range.openClosed(4, 4);
		assertFalse(rangeTest2.contains(3));
		assertFalse(rangeTest2.contains(4));
		assertFalse(rangeTest2.contains(5));
		assertEquals(4, (int) rangeTest2.getLowerBound());
		assertEquals(4, (int) rangeTest2.getUpperBound());
	}

	@Test
	void testClosedOpen() {
		Range<Integer> rangeTest = Range.closedOpen(5, 7);
		assertTrue(rangeTest.contains(5));
		assertFalse(rangeTest.contains(7));
		
		Range<Integer> rangeTest2 = Range.closedOpen(4, 4);
		assertFalse(rangeTest2.contains(3));
		assertFalse(rangeTest2.contains(4));
		assertFalse(rangeTest2.contains(5));
		assertEquals(4, (int) rangeTest2.getLowerBound());
		assertEquals(4, (int) rangeTest2.getUpperBound());
	}
}
