package by.epam.multithreading.files.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.multithreading.files.command.Command;
import by.epam.multithreading.files.service.Service;

public class Controller {

	private static final String DOUBLE_SLASH = "\\";
	private static final String INPUT_FILE_NAME = "inN.dat";
	private static final String REPLACE_SYMBOL = "N";
	private static final String OUTPUT_FILE_NAME = "out.dat";
	private static final Logger LOGGER = Logger.getLogger(Controller.class);
	
	private Service service;
	
	private Double result = 0.0;
	private List<ThreadOperation> threads = new ArrayList<ThreadOperation>();
	
	public void doAction(String directory) throws IOException, InterruptedException {
		service = new Service();
		String inputFileName = INPUT_FILE_NAME;
		Integer i = 1;
		String pathToFile = directory + DOUBLE_SLASH + inputFileName.replace(REPLACE_SYMBOL, i.toString());
		File file = new File(pathToFile);
		ThreadOperation thread = null;
		
		while (file.exists()) {
			thread = new ThreadOperation(pathToFile);
			threads.add(thread);
			thread.start();
			i++;
			pathToFile = directory + DOUBLE_SLASH + inputFileName.replace(REPLACE_SYMBOL, i.toString());
			file = new File(pathToFile);
		}
		
		for (Thread t : threads) {
			t.join();
		}
		
		service.writeFile(result, directory + DOUBLE_SLASH + OUTPUT_FILE_NAME);
	}
		
	class ThreadOperation extends Thread {
		private String pathToFile;
		
		public ThreadOperation(String pathToFile) {
			this.pathToFile = pathToFile;
		}
		
		@Override
		public void run() {
			Command command;
			double operationResult;
			String fileContent = service.readFile(pathToFile);
			if (fileContent != null && fileContent.length() > 0) {
				command = service.getCommand(fileContent);
				if (command != null) {
					double[] doubleArray = service.getArray(fileContent);
					if (doubleArray != null) {
						command.setValues(doubleArray);
						command.doOperation();
						operationResult = command.getResult();
						synchronized (result) {
							result = result + operationResult;
						}
					}
				}
			} else {
				LOGGER.error("File is empty");
			}
		}
	}
}
