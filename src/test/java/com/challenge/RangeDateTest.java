package com.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;

import org.junit.jupiter.api.Test;

import com.challenge.models.Range;

class RangeDateTest {

	ChronoLocalDate startTime = LocalDate.of(2022, Month.SEPTEMBER, 01);
	ChronoLocalDate endTime = LocalDate.of(2022, Month.SEPTEMBER, 30);
	ChronoLocalDate betweenTime = LocalDate.of(2022, Month.SEPTEMBER, 15);
	ChronoLocalDate overTime = LocalDate.of(2022, Month.AUGUST, 1);

	@Test
	void testInputLowerNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(null, LocalDate.of(2022, Month.SEPTEMBER, 01));
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputUpperNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(LocalDate.of(2022, Month.SEPTEMBER, 01), null);
		}, "Lower bound and upper bound must be not null");
	}

	@Test
	void testInputLowerGraterThanUpper() {
		assertThrows(IllegalArgumentException.class, () -> {
			Range.of(LocalDate.of(2022, Month.SEPTEMBER, 20), LocalDate.of(2022, Month.SEPTEMBER, 01));
		}, "Lower bound no bigger than upper bound");
	}

	@Test
	void testOfMethod() {
		Range<ChronoLocalDate> rangeTest = Range.of(startTime, endTime);
		assertTrue(rangeTest.contains(startTime));
		assertTrue(rangeTest.contains(endTime));
		assertFalse(rangeTest.contains(overTime));
		assertEquals(startTime, rangeTest.getLowerBound());
		assertEquals(endTime, rangeTest.getUpperBound());
	}

	@Test
	void testOpen() {
		Range<ChronoLocalDate> range = Range.open(startTime, endTime);
		assertFalse(range.contains(startTime));
		assertFalse(range.contains(endTime));

		Range<ChronoLocalDate> rangeTest2 = Range.open(betweenTime, betweenTime);
		assertFalse(rangeTest2.contains(startTime));
		assertFalse(rangeTest2.contains(betweenTime));
		assertFalse(rangeTest2.contains(endTime));
		assertEquals(betweenTime, rangeTest2.getLowerBound());
		assertEquals(betweenTime, rangeTest2.getUpperBound());
	}

	@Test
	void testClosed() {
		Range<ChronoLocalDate> range = Range.closed(startTime, endTime);
		assertTrue(range.contains(startTime));
		assertTrue(range.contains(endTime));

		Range<ChronoLocalDate> rangeTest2 = Range.closed(betweenTime, betweenTime);
		assertFalse(rangeTest2.contains(startTime));
		assertTrue(rangeTest2.contains(betweenTime));
		assertFalse(rangeTest2.contains(endTime));
		assertEquals(betweenTime, rangeTest2.getLowerBound());
		assertEquals(betweenTime, rangeTest2.getUpperBound());
	}

	@Test
	void testOpenClosed() {
		Range<ChronoLocalDate> range = Range.openClosed(startTime, endTime);
		assertFalse(range.contains(startTime));
		assertTrue(range.contains(endTime));

		Range<ChronoLocalDate> rangeTest2 = Range.openClosed(betweenTime, betweenTime);
		assertFalse(rangeTest2.contains(startTime));
		assertFalse(rangeTest2.contains(betweenTime));
		assertFalse(rangeTest2.contains(endTime));
		assertEquals(betweenTime, rangeTest2.getLowerBound());
		assertEquals(betweenTime, rangeTest2.getUpperBound());
	}

	@Test
	void testClosedOpen() {
		Range<ChronoLocalDate> range = Range.closedOpen(startTime, endTime);
		assertFalse(range.contains(startTime));
		assertTrue(range.contains(endTime));

		Range<ChronoLocalDate> rangeTest2 = Range.closedOpen(betweenTime, betweenTime);
		assertFalse(rangeTest2.contains(startTime));
		assertFalse(rangeTest2.contains(betweenTime));
		assertFalse(rangeTest2.contains(endTime));
		assertEquals(betweenTime, rangeTest2.getLowerBound());
		assertEquals(betweenTime, rangeTest2.getUpperBound());
	}
}
