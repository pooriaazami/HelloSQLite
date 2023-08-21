package com.example.hellosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PersonDatabaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "Person";
    private static final String TAG = "PersonDatabaseHelper";

    public PersonDatabaseHelper(@Nullable Context context) {
        super(context, "person_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = String.format("CREATE TABLE %s (\n" + "\tid INTEGER PRIMARY KEY AUTOINCREMENT,\n" + "\tname VARCHAR(30) NOT NULL,\n" + "\tfamily VARCHAR(30) NOT NULL,\n" + "\tage INT NOT NULL" + "\n)", TABLE_NAME);

        try {
            db.execSQL(query);
        } catch (SQLiteException ex) {
            Log.e(TAG, ex.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNewPerson(Person p) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", p.getName());
        values.put("family", p.getFamily());
        values.put("age", p.getAge());

        long result = database.insert(TABLE_NAME, null, values);

        database.close();
        return result;
    }

    public ArrayList<Person> readAllPerson() {
        SQLiteDatabase database = getReadableDatabase();
        String query = String.format("SELECT * FROM %s", TABLE_NAME);
        ArrayList<Person> data = new ArrayList<>();

        try {
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToNext();

            while (cursor.moveToNext()) {
                int key = cursor.getInt(0);
                String name = cursor.getString(1);
                String family = cursor.getString(2);
                int age = cursor.getInt(3);

                Person p = new Person(key, name, family, age);
                data.add(p);
            }
        } catch (SQLiteException ex) {
            Log.e(TAG, ex.toString());
        }

        return data;
    }

    public boolean removePerson(int key) {
        SQLiteDatabase database = getWritableDatabase();
        int result = database.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(key)});

        return result == 1;
    }

}
