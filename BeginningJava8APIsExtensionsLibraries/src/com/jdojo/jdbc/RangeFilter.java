// RangeFilter.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

public class RangeFilter implements Predicate {
	private final int columnIndex;
	private final String columnName;
	private final double min;
	private final double max;

	public RangeFilter(int columnIndex, String columnName,
		double min, double max) {
		this.columnIndex = columnIndex;
		this.columnName = columnName;
		this.min = min;
		this.max = max;
	}

	@Override
	public boolean evaluate(RowSet rs) {
		// Make sure we have a good row number to evaluate 
		try {
			if (rs.getRow() <= 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		boolean showRow = false;
		Object value = null;

		try {
			value = rs.getObject(columnName);
			if (value instanceof Number) {
				double num = ((Number) value).doubleValue();
				showRow = (num >= min && num <= max);
			}
		} 
		catch (SQLException e) {
			showRow = false;
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return showRow;
	}

	@Override
	public boolean evaluate(Object value, int columnIndex) {
		boolean showRow = false;
		if (columnIndex == this.columnIndex
			&& value instanceof Number) {
			double num = ((Number) value).doubleValue();
			showRow = (num >= min && num <= max);
		}
		return showRow;
	}

	@Override
	public boolean evaluate(Object value, String columnName) {
		boolean showRow = false;
		if (this.columnName.equalsIgnoreCase(columnName)
			&& value instanceof Number) {
			double num = ((Number)value).doubleValue();
			showRow = (num >= min && num <= max);
		}
		return showRow;
	}
}
