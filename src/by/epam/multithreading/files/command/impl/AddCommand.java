package by.epam.multithreading.files.command.impl;

import java.util.Arrays;

import by.epam.multithreading.files.command.Command;

public class AddCommand extends Command {

	@Override
	public void doOperation() {
		result = 0.0;
		for (double element : inputArray) {
			result = result + element;
		}
	}
}
