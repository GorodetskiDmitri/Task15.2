package by.epam.multithreading.files.command;

public interface Command {
	void setValues(double[] inputArray);
	void doOperation();
	double getResult();
}
