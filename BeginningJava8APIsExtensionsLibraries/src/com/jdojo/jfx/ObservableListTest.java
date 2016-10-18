// ObservableListTest.java
package com.jdojo.jfx;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class ObservableListTest {
	public static void main(String[] args) {
		// Create a list with some elements
		ObservableList<String> list = 
			FXCollections.observableArrayList("one", "two");
		
		System.out.println("After creating the list: " + list);

		// Add a ChangeListener to the list
		list.addListener(ObservableListTest::onChanged);

		// Add some more elements to the list
		list.addAll("three", "four");
		System.out.println("After addAll() - list: " + list);

		// We have four elements. Remove the middle two
		// from index 1 (inclusive) to index 3 (exclusive)
		list.remove(1, 3);
		System.out.println("After remove() - list: " + list);

		// Retain only the element "one"
		list.retainAll("one");
		System.out.println("After retainAll() - list: " + list);

		// Replace the first element in the list
		list.set(0, "ONE");
		System.out.println("After set() - list: " + list);
	}

	public static void onChanged(ListChangeListener.Change<? extends String> change) {
		while (change.next()) {
			if (change.wasPermutated()) {
				System.out.println("A permutation is detected.");
			} 
			else if (change.wasUpdated()) {
				System.out.println("An update is detected.");
			} 
			else if (change.wasReplaced()) {
				System.out.println("A replacement is detected.");
			} 
			else {
				if (change.wasRemoved()) {
					System.out.println("A removal is detected.");
				} 
				else if (change.wasAdded()) {
					System.out.println("An addiiton is detected.");
				}
			}
		}
	}

}
