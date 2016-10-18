// BindingTest.java
package com.jdojo.jfx;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BindingTest {
	public static void main(String[] args) {
		// Create two properties x and y
		IntegerProperty x = new SimpleIntegerProperty(100);
		IntegerProperty y = new SimpleIntegerProperty(200);

		// Create a binding: sum = x + y
		NumberBinding sum = x.add(y);

		System.out.println("After creating sum");
		System.out.println("sum.isValid(): " + sum.isValid());

		// Let us get the value of sum, so it computes its value and
		// becomes valid
		int value = sum.intValue();

		System.out.println();
		System.out.println("After requesting value");
		System.out.println("sum.isValid(): " + sum.isValid());
		System.out.println("sum = " + value);

		// Change the value of x
		x.set(250);

		System.out.println();
		System.out.println("After changing x");
		System.out.println("sum.isValid(): " + sum.isValid());

		// Get the value of sum again
		value = sum.intValue();

		System.out.println();
		System.out.println("After requesting value");
		System.out.println("sum.isValid(): " + sum.isValid());
		System.out.println("sum = " + value);
	}
}
