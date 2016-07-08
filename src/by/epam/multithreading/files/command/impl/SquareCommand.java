package by.epam.multithreading.files.command.impl;

import by.epam.multithreading.files.command.Command;

public class SquareCommand extends Command {
	
	@Override
	public void doOperation() {
		result = 0.0;
		for (double element : inputArray) {
			result = result + Math.pow(element, 2);
		}
	}
}
