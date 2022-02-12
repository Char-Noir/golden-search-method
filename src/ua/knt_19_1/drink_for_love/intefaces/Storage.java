package ua.knt_19_1.drink_for_love.intefaces;

public interface Storage {//The interface stores all variables needed for method

    void setA(double a);//Setter for this variable
    void setB(double b);//Setter for this variable
    void setE(double e);//Setter for this variable
    void setFunction(String function);//Setter for this variable(set only outer function)
    void setX(double x);//Setter for this variable

    double getA();//Getter for this variable
    double getB();//Getter for this variable
    double getE();//Getter for this variable
    double getX();//Getter for this variable
    String getFunction();//Getter for this variable(return only inner function like 3*x*x*x)

    void takeValues();//read values from console

    void putValues();//write values to console

}
