package by.epam.multithreading.files.command;

public abstract class Command {
	protected double[] inputArray;
	protected double result;

	public void setValues(double[] inputArray) {
		this.inputArray = inputArray;
	}
	
	public double getResult() {
		return result;
	}

	public void doOperation(){
		
	}
}
