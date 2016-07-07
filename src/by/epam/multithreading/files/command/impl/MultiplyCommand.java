package by.epam.multithreading.files.command.impl;

import java.util.Arrays;

import by.epam.multithreading.files.command.Command;

public class MultiplyCommand extends Command {

	@Override
	public void doOperation() {
		result = 1.0;
		for (double element : inputArray) {
			result = result * element;
		}
	}
}
