package ua.knt_19_1.drink_for_love.classes;

import ua.knt_19_1.drink_for_love.intefaces.Storage;

import java.util.Scanner;

public class StorageClass implements Storage {//The class stores all variables needed for method

     static double a = 0;//left value of range a<=x<=b
     static double b = 0;//right value of range a<=x<=b

     static double e = 0;//accuracy of our calculations
     static String[] function={"",""};//outer(from the user) and inner(for calculations) function for method
     static double x =0;//minimum extremum point of the function


    @Override
    public void setA(double a) {
    StorageClass.a =a;
    }//Setter for this variable

    @Override
    public void setB(double b) {
    StorageClass.b =b;
    }//Setter for this variable

    @Override
    public void setE(double e) {
    StorageClass.e =e;
    }//Setter for this variable

    @Override
    public double getA() {
        return a;
    }//Getter for this variable

    public  String getFunction() {
        return function[1];
    }//Getter for this variable(return only inner function like 3*x*x*x)

    public  void setFunction(String function) {
        StorageClass.function[0] = function;
    }//Setter for this variable(set only outer function)

    @Override
    public double getB() {
        return b;
    }//Getter for this variable

    @Override
    public double getE() {
        return e;
    }//Getter for this variable

    @Override
    public void takeValues() {
        Scanner in = new Scanner(System.in);//Creating new Scanner object for reading from incoming stream System.in which linked with console input
        System.out.print("Input a function: \n");//Outputting String to console using System.out - output stream witch linked to console output
        function[0] = in.nextLine();//Reading new line from console
        function[1] = changeFunction(function[0]);//Rewrite function from human-friendly form to JavaScript-friendly form
        System.out.print("Input a arguments for method: a:");//Outputting String to console
        a = Double.parseDouble( in.nextLine().replace(",","."));//Reading new double from console
        System.out.print( "b:" );//Outputting String to console
        b = Double.parseDouble( in.nextLine().replace(",","."));//Reading new double from console
        System.out.print( "and accuracy:");//Outputting String to console
        e = Double.parseDouble( in.nextLine().replace(",","."));//Reading new double from console using "replace" method to avoid differences between '.' and ','
        System.out.print("Thank you!\n");//Thanks for using our program ;)
    }//Read values for method from console

    @Override
    public  double getX() {
        return x;
    }//Returns X value

    @Override
    public  void setX(double x) {
        StorageClass.x = x;
    }//Setter for X

    @Override
    public void putValues() {
        setX((getA()+getB())/2);//Calculating x before outputting
        System.out.printf("Out x for function %s is %.2f  %n",function[0],x);//Writing function and x to console

    }//Writes x for this function to console

    private String changeFunction(String function){
        int index;//index of first entry of pattern

        function=function.replace(" ", "") + " ";//Replacing whitespaces to none in this string
        StringBuilder part = new StringBuilder();//Creating object for parts between non-digits and etc(x,^)
        String number = "";//String for numbers after special symbol '^'
        boolean checkpoint = false;//Boolean variable for checking '^' for calculating variable "number"
        function=function.replaceAll("(\\d)x","$1*x" );//Replacing "digit x" to "digit*x" by RegEx (4x -> 4*x for example)
        function=function.replaceAll("(\\d)\\(","$1*(" );//Replacing "digit (..." to "digit*(..." by RegEx (4(.. -> 4*(... for example)
        function=function.replaceAll("x\\(","x*(" );//Replacing "x(..." to "x*(..." by RegEx (x(... -> x*(... for example)
        function=function.replaceAll("\\)x",")*x");//Replacing "...)x" to "...)*x" by RegEx (...)x -> ...)*x for example)

        for(int i = 0; i<function.length(); i++){//Loop for brute force all symbols in string
            if(Character.isDigit(function.charAt(i)) || function.charAt(i)=='x' || function.charAt(i)=='^'){//if this symbol is digit or 'x' or '^'
                part.append(function.charAt(i));//Adding this symbol to "part" string
                if(function.charAt(i)=='^'){//If this symbol is '^' next is digit defining power of x
                    checkpoint = true;//Next symbol is digit defining power of x
                }
                else if(checkpoint){//If one of previous symbols is '^' this symbol is digit defining power of x
                    number+=function.charAt(i);//Adding symbol to "number" variable
                }
            }
            else {//If this symbol is something else like '+', '-', '*' and etc.
                for(int j = 0; j<part.length(); j++){//Loop for brute forcing all "part" string for replacing all entries
                    if(!number.equals("")){//If "number" string isn`t empty
                        function=function.replace("x^"+Integer.parseInt(number), "x" + "*x".repeat(Math.max(0, Integer.parseInt(number) - 1)));//Replacing "x^55151" to "x*x*x*x*...*x*x"
                    }
                }
                part = new StringBuilder();//Flushing "part" string
                checkpoint = false;//Flushing "checkpoint" variable
                number="";//Flushing "number" string 
            }
        }



        while (true){//While endless loop with break option inside
            index = function.indexOf(")^");//Finding "(...)^" pattern in string
            if(index==-1){//If no such pattern
                break;//Breaking the loop
            }else {//In another case
                StringBuilder str=new StringBuilder(")");//Creating object for finding brackets pattern
                int count=1;//Counter for finding opened and closed brackets
                for(int i = index-1;i>=0;i--){//Reverse "for" loop for finding brackets pattern in String from ")^"
                    if(function.charAt(i)==')'){//If this character is closed brackets
                        count++;//We increasing counter of brackets
                    }else if(function.charAt(i)=='('){//If this character is opened brackets
                        count--;//We decreasing counter of brackets
                    }
                    str.append(function.charAt(i));//Appending StringBuilder object. After loop we will have String like ")x-01("
                    if(count==0){//If we found all brackets hierarchy
                        break;//Breaking the loop ahead of time
                    }
                }
                str = str.reverse();//Reversing our brackets String from ")x-01(" to "(10-x)"
                number= "";//Stringi for me
                for(int i=index+2;i<function.length();i++){
                    if(Character.isDigit(function.charAt(i))){
                        number+=function.charAt(i);
                    }
                    else{
                        break;
                    }
                }
                if(!number.equals("")){
                int count2 = Integer.parseInt(number);//Finding amount for pow
                function = function.replace(str.toString()+"^"+count2, str.toString() + ("*" + str.toString()).repeat(count2 - 1));//Rewriting function String by replacing (10-x)^2 to (10-x)*(10-x) for example
                }
            }
        }


        return function;//Returning new String
    }//Rewrites function from human-friendly form to JavaScript-friendly form


}
