package by.epam.multithreading.files.command.impl;

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
