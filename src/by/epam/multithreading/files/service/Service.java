package by.epam.multithreading.files.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import by.epam.multithreading.files.command.Command;
import by.epam.multithreading.files.command.impl.AddCommand;
import by.epam.multithreading.files.command.impl.MultiplyCommand;
import by.epam.multithreading.files.command.impl.SquareCommand;

public class Service {
	
	private static final String NEW_LINE = "\n";
	private static final String SPLITTER = " ";
	private static final Logger LOGGER = Logger.getLogger(Service.class);
	
	
	public String readFile(String pathToFile) {
		try (FileInputStream inputStream = new FileInputStream(pathToFile)) {
			byte[] content = new byte[inputStream.available()];
			inputStream.read(content);
			return new String(content);
		} catch (IOException e) {
			LOGGER.error("Error reading file.", e);
			return null;
		}
	}
	
	public void writeFile(Double result, String outputFilePath) {
		try (FileWriter fileWriter = new FileWriter(new File(outputFilePath))) {
			fileWriter.write(result.toString());
			fileWriter.flush();
		} catch (IOException e) {
			LOGGER.error("Error writing file.", e);
		}
	}
	
	public Command getCommand(String fileContent) {
		Command command = null;
		int commandNumber;
		
		int newLine = fileContent.indexOf(NEW_LINE);
		if (newLine > 0) {
			try {
				commandNumber = Integer.parseInt(fileContent.substring(0, newLine).trim());
			} catch (NumberFormatException e) {
				LOGGER.error("Not correct data in the first line of file.", e);
				return null;
			}
			switch(commandNumber) {
			case 1:
				command = new AddCommand();
				break;
			case 2:
				command = new MultiplyCommand();
				break;
			case 3:
				command = new SquareCommand();
				break;
			}
			return command;
		} else {
			LOGGER.error("Can't find required command");
			return null;
		}	
	}
	
	public double[] getArray(String fileContent) {
		double[] doubleArray;
		String[] stringArray;
		int newLine = fileContent.indexOf(NEW_LINE);
		
		if (newLine > 0) {
			fileContent = fileContent.substring(newLine).trim();
			if (!fileContent.isEmpty()) {
				stringArray = fileContent.split(SPLITTER);
				doubleArray = new double[stringArray.length];
				for (int i = 0; i < stringArray.length; i++) {
					try {
						doubleArray[i] = Double.parseDouble(stringArray[i]);
					} catch (NumberFormatException e) {
						LOGGER.error("Not correct data in the second line of file.", e);
						return null;
					}
				}
				return doubleArray;
			} else {
				LOGGER.error("Can't find required array");
				return null;
			}
		} else {
			LOGGER.error("Can't find required array");
			return null;
		}
	} 
}
