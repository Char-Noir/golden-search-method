package ua.knt_19_1.drink_for_love.classes;

import ua.knt_19_1.drink_for_love.intefaces.Storage;
import ua.knt_19_1.drink_for_love.intefaces.Writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriterClass implements Writer {//The class for writing log to file

    String filename;     //name of file for output
    List<String> outs;   //list of strings to output
    String lastOne = "";      //last added string after 10 already added strings
    int count = 0;              //counter for added strings

    public WriterClass(String s) {//Constructor with parameter String as filename
        filename = s;
        outs=new ArrayList<>();//Creating new List object
    }

    @Override
    public String getFilename() {
        return filename;
    }//Getter for this value

    @Override
    public void setFilename(String filename) {
        this.filename = filename;
    }//Setter for this value

    @Override
    public List<String> getOuts() {
        return outs;
    }//Getter for this value

    @Override
    public void setOuts(List<String> outs) {
        this.outs=outs; //Add all outs
    }//Setter for this value

    @Override
    public void addString(String iter) {//Add String for outputting to file
        if(count<10){//Add String for outputting to file
            outs.add(iter);//Add String to list using List`s method "add"
            count++;//Increasing the counter
        }else {//If we already have 10 Strings
            lastOne = iter;//Rewrite lastOne with this String
        }

    }

    @Override
    public boolean flush(Storage storage) {//Write first 10 Strings and the last one to file
        File file = new File(filename);//Creating new File`s object for writing Strings to it

        try (//Start of "try" block
                FileWriter fileWriter = new FileWriter(file)//Using "()" after "try" to autoclose FileWriter and creating FileWriter object to control outputting stream to file
        ) {
            for (String s: outs) {//In cycle "for each" we address to each of 10 Strings
                fileWriter.write(s);//Writing each of 10 String to file
            }
            fileWriter.write(lastOne);//Writing the last one before flushing String
            fileWriter.write("Result: a:"+storage.getA()+"; b:"+storage.getB()+"; x:"+storage.getX());//Writing result to file
            lastOne="";//Reset value of this variable
            outs.clear();//Reset list for strings
        } catch (IOException e) {//End of "try" block and start of "catch" clause
            e.printStackTrace();//Printing all error branch
            return false;//Returns false when we catches error
        }

        return true;//Returns true if we don't have errors
    }
}
