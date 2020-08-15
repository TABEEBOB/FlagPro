package com.example.flagsandcountries;

public class CountryChoice {

    public String name;
    public int image;


    public CountryChoice(String name, int image){

        this.name = name;
        this.image = image;

    }

    public  void setName(String name){
        this.name = name;
    }
    public void setImage(int image){
        this.image = image;
    }
    public String getName(){
        return name;
    }
    public int getImage(){
        return image;
    }
} 
