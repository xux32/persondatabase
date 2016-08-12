package com.example.xux32.persondatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.xux32.persondatabase.database.DBManger;
import com.example.xux32.persondatabase.person.PersonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonActivity extends AppCompatActivity {

    private DBManger manger;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        manger = new DBManger(this);
        listView = (ListView) findViewById(R.id.id_list);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        manger.closeDB();
    }

    public void add(View view){

        List<PersonObject> persons = new ArrayList<PersonObject>();

        PersonObject person1 = new PersonObject("Ella", 22, "lively girl");
        PersonObject person2 = new PersonObject("Jenny", 22, "beautiful girl");
        PersonObject person3 = new PersonObject("Jessica", 23, "sexy girl");
        PersonObject person4 = new PersonObject("Kelly", 23, "hot baby");
        PersonObject person5 = new PersonObject("Jane", 25, "a pretty woman");
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person4);
        persons.add(person5);

        manger.add(persons);
    }

    public void update(View view){

        PersonObject person = new PersonObject();
        person.name = "Jane";
        person.age = 30;
        manger.updateAge(person);
    }

    public void delete(View view){

        PersonObject person = new PersonObject();
        person.age = 30;
        manger.deleteOldPerson(person);
    }

    public void query(View view){

        List<PersonObject> persons = new ArrayList<PersonObject>();
        persons = manger.query();
        List<Map<String,String>> list = new ArrayList<Map<String,String>>();
        for(PersonObject person : persons){
            Map<String,String> map = new HashMap<String,String>();
            map.put("name",person.name);
            map.put("info",person.age + "years old" + person.info);
            list.add(map);
        }
        listView.setAdapter(new SimpleAdapter(this,list,android.R.layout.simple_list_item_2,new String[]{"name","info"},new int[]{android.R.id.text1,android.R.id.text2}));

    }
}
