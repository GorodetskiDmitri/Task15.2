package by.epam.multithreading.files.main;

import java.io.IOException;

import by.epam.multithreading.files.controller.Controller;

public class Main {
	public static void main(String[] args) throws IOException, InterruptedException {
		Controller controller = new Controller();
		controller.doAction(args[0]);
	}

}
