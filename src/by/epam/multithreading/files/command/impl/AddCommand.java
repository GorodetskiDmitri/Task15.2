package by.epam.multithreading.files.command.impl;

import java.util.Arrays;

import by.epam.multithreading.files.command.Command;

public class AddCommand implements Command {
	private double[] inputArray;
	private double result;

	@Override
	public void setValues(double[] inputArray) {
		this.inputArray = inputArray;
	}

	@Override
	public void doOperation() {
		result = 0.0;
		for (double element : inputArray) {
			result = result + element;
		}
	}

	@Override
	public double getResult() {
		return result;
	}
	
}
