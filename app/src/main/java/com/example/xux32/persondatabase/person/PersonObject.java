package com.example.xux32.persondatabase.person;

/**
 * Created by xux32 on 2016/8/11.
 */
public class PersonObject {

    public int _id;
    public String name;
    public int age;
    public String info;

    public PersonObject(){

    }
    public PersonObject( String name, int age, String info) {
        this.name = name;
        this.age = age;
        this.info = info;
    }
}
