package com.example.xux32.persondatabase.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.xux32.persondatabase.person.PersonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xux32 on 2016/8/11.
 */
public class DBManger {

    private DBHelper helper;
    private SQLiteDatabase db;

    public DBManger(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public void add(List<PersonObject> persons) {
        db.delete("person",null,null);
        db.beginTransaction();
        try {
            for (PersonObject person : persons) {
                db.execSQL("INSERT INTO person VALUES(null,?,?,?)", new Object[]{person.name, person.age, person.info});
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();

        }
    }

    public void updateAge(PersonObject person){
        ContentValues contentValues = new ContentValues();
        contentValues.put("age",person.age);
        db.update("person",contentValues,"name = ?",new String[]{person.name});
    }

    public void deleteOldPerson(PersonObject person){
        db.delete("person","age >= ?", new String[]{String.valueOf(30)});
    }

    public List<PersonObject> query(){

        List<PersonObject> persons = new ArrayList<PersonObject>();
        Cursor cursor = queryTheCursor();
        while(cursor.moveToNext()){
            PersonObject person = new PersonObject();
            person._id = cursor.getInt(cursor.getColumnIndex("_id"));
            person.name = cursor.getString(cursor.getColumnIndex("name"));
            person.age = cursor.getInt(cursor.getColumnIndex("age"));
            person.info = cursor.getString(cursor.getColumnIndex("info"));
            persons.add(person);
        }
        cursor.close();
        return persons;
    }

    public Cursor queryTheCursor(){
        Cursor cursor = db.rawQuery("SELECT * FROM person", null);
        return cursor;
    }
    public void closeDB(){
        db.close();
    }
}
