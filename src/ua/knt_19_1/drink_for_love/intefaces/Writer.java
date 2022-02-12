package ua.knt_19_1.drink_for_love.intefaces;

import java.util.List;

public interface Writer {//The interface for writing log to file

    String getFilename();//Getter for this variable
    void setFilename(String filename);//Setter for this variable

    List<String> getOuts();//Getter for this variable
    void setOuts(List<String> outs);//Setter for this variable

    void addString(String iter);//Add String for outputting to file

    boolean flush(Storage storage);//Write first 10 Strings and the last one to file

}
