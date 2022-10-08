package com.challenge;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.challenge.models.Range;

class RangeStringTest {

	@Test
	void testInputNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(null, null);
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputLowerNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(null, "abc");
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputUpperNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of("abc", null);
		}, "Lower bound and upper bound must be not null");
	}
	
	@Test
	void testInputLowerGraterThanUpper() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of("c", "a");
		}, "Lower bound no bigger than upper bound");
	}
	
	@Test
	void testOfMethod() {
		Range<String> rangeTest = Range.of("e", "g");
		assertTrue(rangeTest.contains("e"));
		assertTrue(rangeTest.contains("g"));
		assertFalse(rangeTest.contains("a"));
		assertEquals("e", rangeTest.getLowerBound());
		assertEquals("g", rangeTest.getUpperBound());
	}
	
	@Test
	void testOpen() {
		Range<String> range = Range.open("a", "c");
		assertFalse(range.contains("a"));
		assertFalse(range.contains("c"));
		
		Range<String> rangeTest2 = Range.open("b", "b");
		assertFalse(rangeTest2.contains("a"));
		assertFalse(rangeTest2.contains("b"));
		assertFalse(rangeTest2.contains("c"));
		assertEquals("b", rangeTest2.getLowerBound());
		assertEquals("b", rangeTest2.getUpperBound());
	}
	
	@Test
	void testClosed() {
		Range<String> range = Range.closed("a", "c");
		assertTrue(range.contains("a"));
		assertTrue(range.contains("c"));
		
		Range<String> rangeTest2 = Range.closed("b", "b");
		assertFalse(rangeTest2.contains("a"));
		assertTrue(rangeTest2.contains("b"));
		assertFalse(rangeTest2.contains("c"));
		assertEquals("b", rangeTest2.getLowerBound());
		assertEquals("b", rangeTest2.getUpperBound());
	}
	
	@Test
	void testOpenClosed() {
		Range<String> range = Range.openClosed("a", "c");
		assertFalse(range.contains("a"));
		assertTrue(range.contains("c"));
		
		Range<String> rangeTest2 = Range.openClosed("b", "b");
		assertFalse(rangeTest2.contains("a"));
		assertFalse(rangeTest2.contains("b"));
		assertFalse(rangeTest2.contains("c"));
		assertEquals("b", rangeTest2.getLowerBound());
		assertEquals("b", rangeTest2.getUpperBound());
	}
	
	@Test
	void testClosedOpen() {
		Range<String> range = Range.closedOpen("a", "c");
		assertTrue(range.contains("a"));
		assertFalse(range.contains("c"));
		
		Range<String> rangeTest2 = Range.closedOpen("b", "b");
		assertFalse(rangeTest2.contains("a"));
		assertFalse(rangeTest2.contains("b"));
		assertFalse(rangeTest2.contains("c"));
		assertEquals("b", rangeTest2.getLowerBound());
		assertEquals("b", rangeTest2.getUpperBound());
	}

}
