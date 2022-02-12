package ua.knt_19_1.drink_for_love.classes;

import ua.knt_19_1.drink_for_love.intefaces.Calculator;
import ua.knt_19_1.drink_for_love.intefaces.Storage;
import ua.knt_19_1.drink_for_love.intefaces.Writer;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorClass implements Calculator {//The class deals with the processing of the basic formulas of the method

    static int counter = 0;//counter of cycles
  //static double fi = (1+Math.sqrt(5))/2;//constant Fi for calculations
    static double fi = (Math.sqrt(5)-1)/2;//constant Fi for calculations
    static double x1;
    static double x2;
    static double y1;
    static double y2;

    @Override
    public boolean checkCondition(Storage storage) {
        return Math.abs(storage.getB() - storage.getA()) > storage.getE();//Checking condition |b-a|>e
    }//Checks method condition

    @Override
   /* public boolean calculateNewIteration(Storage storage, Writer writer) throws ScriptException {
        if(++counter>30){//Checking loop condition
            System.out.println("So many iterations!");//Informing the user about looping
            return false;//Returning false in this condition
        }
        double x1 =storage.getB()-((storage.getB()-storage.getA())/fi);//Calculating intermediate value
        double x2 =storage.getA()+((storage.getB()-storage.getA())/fi);//Calculating intermediate value
        double y1 = foo(storage,x1);//Calculating intermediate value
        double y2 = foo(storage,x2);//Calculating intermediate value
        writer.addString(counter + ") a = "+storage.getA()+"; b = "+storage.getB()+"; x1 = "+x1+"; x2 = "+x2+"; y1 = "+y1+"; y2 = "+y2+";\n");//Writing new String to file
        if(y1>=y2){//Checking method inner condition f(x1)>=f(x2)
            storage.setA(x1);//Setting new left value of range
        }else {//In other case
            storage.setB(x2);//Setting new right value of range
        }
        return true;//Returning true
    }//Calculates new iteration of method (return false if we more then on 30 iteration else returns true)

    */

    public boolean calculateNewIteration(Storage storage,Writer writer) throws ScriptException {
        if(++counter>30){//Checking loop condition
            System.out.println("So many iterations!");//Informing the user about looping
            return false;//Returning false in this condition
        }
        if( counter == 1 ){//Initializing variables
            x1 = storage.getA() + (( storage.getB() - storage.getA() ) * ( 1-fi ));//Calculating x1 on first iteration
            x2 = storage.getA() + (( storage.getB() - storage.getA() ) * fi );//Calculating x2 on first iteration
            y1 = foo(storage, x1);//Calculating f(x1) on first iteration
            y2 = foo(storage, x2);//Calculating f(x2) on first iteration
        }
        writer.addString( counter + ") a = " + storage.getA() + "; b = " + storage.getB() + "; x1 = " +
                x1 + "; x2 = " + x2 + "; f(x1) = " + y1 + "; f(x2) = " + y2 + ";\n");//Adding string to file
        if( y1 > y2){//If f(x1) > f(x2)
            storage.setA(x1);//Setting new a
            x1 = x2;//Setting x1 as prev x2
            y1 = y2;//Setting f(x1) as prev f(x2)
            x2 = storage.getA() + (( storage.getB() - storage.getA() ) * fi );//Calculating new x2
            y2 = foo(storage, x2);//Calculating f(x2)
        }
        else {//If f(x2) > f(x1) or equals
            storage.setB(x2);//Setting new b
            x2 = x1;//Setting x2 as prev x1
            y2 = y1;//Setting f(x2) as prev f(x1)
            x1 = storage.getA() + (( storage.getB() - storage.getA() ) * (1 - fi));//Calculating new x1
            y1 = foo(storage, x1);//Calculating f(x1)
        }
        return true;//Rerurning true
    }//Calculates new iteration of method (return false if we more then on 30 iteration else returns true)

    private double foo(Storage storage, double x) throws ScriptException {//Function returns calculated value of function on this x
        String function = storage.getFunction().replace("x",String.valueOf(x));//Replacing 'x' in function to specific value
        ScriptEngineManager manager = new ScriptEngineManager();//Creating manager for ScriptEngines
        ScriptEngine scriptEngine = manager.getEngineByName("JavaScript");//Creating JavaScript engine object
        return Double.parseDouble(scriptEngine.eval(function).toString());//Calculating value of function on this x and parsing it to Double
    }

}
