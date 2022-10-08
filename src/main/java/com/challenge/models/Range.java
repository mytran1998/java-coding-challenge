package com.challenge.models;


import java.io.Serializable;

import com.challenge.enums.RangeType;

/**
 * The Range class to present a range of elements
 * 
 * @author my.tran
 *
 */
public final class Range<T extends Comparable<T>> implements Serializable {

	/**
	 * Serialization version
	 */
	private static final long serialVersionUID = -1;

	/**
	 * The lower bound value
	 */
	private final T lowerBound;

	/**
	 * The upper bound value
	 */
	private final T upperBound;
	
	/**
	 * The type of range
	 */
	private final RangeType rangeType;

	/***
	 * Constructor
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 */
	private Range(final T lower, final T upper, final RangeType rangeType) {
		if (lower == null || upper == null) {
			throw new IllegalArgumentException("Lower bound and upper bound must be not null");
		}
		
		if (lower.compareTo(upper) > 0) {
			throw new IllegalArgumentException("Lower bound no bigger than upper bound");
		}
		
		this.lowerBound = lower;
		this.upperBound = upper;
		this.rangeType = rangeType;
	}

	/***
	 * The method create new instance Range
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 * @return value new instance Range
	 */
	public static <T extends Comparable<T>> Range<T> of(final T lower, final T upper) {
		return new Range<T>(lower, upper, RangeType.CLOSED);
	}

	/***
	 * The method create instance open range excludes both bounds
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 * @return value new instance Range
	 */
	public static <T extends Comparable<T>> Range<T> open(final T lower, final T upper) {
		return new Range<T>(lower, upper, RangeType.OPEN);
	}

	/***
	 * The method create instance closed range includes both bounds
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 * @return value new instance Range
	 */
	public static <T extends Comparable<T>> Range<T> closed(final T lower, final T upper) {
		return new Range<T>(lower, upper, RangeType.CLOSED);
	}

	/***
	 * The method create instance open closed excludes lower bound but includes
	 * upper bound
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 * @return value new instance Range
	 */
	public static <T extends Comparable<T>> Range<T> openClosed(final T lower, final T upper) {
		return new Range<T>(lower, upper, RangeType.OPEN_CLOSED);
	}

	/***
	 * The method create instance closed open includes lower bound but excludes
	 * upper bound
	 * 
	 * @param lower The lower bound value
	 * @param upper The upper bound value
	 * @return value new instance Range
	 */
	public static <T extends Comparable<T>> Range<T> closedOpen(final T lower, final T upper) {
		return new Range<T>(lower, upper, RangeType.CLOSED_OPEN);
	}

	/***
	 * Check value valid range
	 * 
	 * @param value The input value
	 * @return true if value valid and false if value invalid
	 */
	public boolean contains(final T value) {
		if(value == null) {
			return false;
		}
		
		switch (getRangeType()) {
			case OPEN:
				return value.compareTo(getLowerBound()) > 0 && value.compareTo(getUpperBound()) < 0;
			case CLOSED:
				return value.compareTo(getLowerBound()) >= 0 && value.compareTo(getUpperBound()) <= 0;
			case OPEN_CLOSED:
				return value.compareTo(getLowerBound()) > 0 && value.compareTo(getUpperBound()) <= 0;
			case CLOSED_OPEN:
				return value.compareTo(getLowerBound()) >= 0 && value.compareTo(getUpperBound()) < 0;
			default:
				break;
		}
		
		return false;
	}

	/***
	 * Get the lower bound value
	 * 
	 * @return lower bound
	 */
	public T getLowerBound() {
		return lowerBound;
	}

	/**
	 * Get the upper bound value
	 * 
	 * @return upper bound
	 */
	public T getUpperBound() {
		return upperBound;
	}

	/***
	 * Get the type of range
	 * @return Type range
	 */
	public RangeType getRangeType() {
		return rangeType;
	}
}