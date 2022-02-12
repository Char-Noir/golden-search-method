package ua.knt_19_1.drink_for_love.intefaces;

import javax.script.ScriptException;

public interface Calculator {//The interface deals with the processing of the basic formulas of the method.
    boolean checkCondition(Storage storage);//returns true if conditions(method`s) is true

    boolean calculateNewIteration(Storage storage, Writer writer) throws ScriptException;//returns false if we in cycle and calculates new iteration of method
}
