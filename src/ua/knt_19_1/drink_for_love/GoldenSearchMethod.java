package ua.knt_19_1.drink_for_love;

import ua.knt_19_1.drink_for_love.classes.CalculatorClass;
import ua.knt_19_1.drink_for_love.classes.StorageClass;
import ua.knt_19_1.drink_for_love.classes.WriterClass;
import ua.knt_19_1.drink_for_love.intefaces.Calculator;
import ua.knt_19_1.drink_for_love.intefaces.Storage;
import ua.knt_19_1.drink_for_love.intefaces.Writer;

import javax.script.ScriptException;

public class GoldenSearchMethod {//The main class for our program

    private static Writer writer = new WriterClass("log.txt");//Variable for Writer interface//Creating new WriterClass object with file name
    private static Calculator calculator= new CalculatorClass();//Creating new CalculatorClass object//Variable for Calculator interface
    private static Storage storage = new StorageClass();;//Variable for Storage interface//Creating new StorageClass object

    public static void main(String[] args) throws ScriptException {//The main method for our method

        storage.takeValues();//Read values from console and put it to variables in StorageClass object

        do{}//Using empty cycle "do while" because we calculate new iteration in "while" part
        while(calculator.calculateNewIteration(storage, writer) && calculator.checkCondition(storage));//Checking looping in iterations and method conditions

        storage.putValues();//Write values from StorageClass object to console

        writer.flush(storage);//Write all log Strings to file
    }
}
