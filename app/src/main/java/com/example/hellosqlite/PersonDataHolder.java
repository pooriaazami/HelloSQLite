package com.example.hellosqlite;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

public class PersonDataHolder {

    private ArrayList<Person> data;
    private PersonDatabaseHelper databaseHelper;

    public PersonDataHolder(Context context) {
        databaseHelper = new PersonDatabaseHelper(context);
        data = databaseHelper.readAllPerson();
    }

    public void addPerson(Person person) {
        databaseHelper.addNewPerson(person);
        data.add(person);
    }

    public Person getPerson(int idx) {
        return data.get(idx);
    }

    public int size() {
        return data.size();
    }

    public void remove(int idx) {
        databaseHelper.removePerson(data.get(idx).getDatabaseKey());
        data.remove(idx);
    }


}
