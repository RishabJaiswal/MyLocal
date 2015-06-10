package com.example.Rishab.myapplication.backend2;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean
{

    private String myData;
    public boolean dataStored;
    public String getData() {
        return myData;
    }

    public void setData(String data)
    {
        myData = data;
    }

    public void setDataStored(boolean dataStored)
    {
        this.dataStored =  dataStored;
    }

}