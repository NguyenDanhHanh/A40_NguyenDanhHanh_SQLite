package com.example.a40_nguyendanhhanh_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLHelper";
    static final String DB_NAME = "Contact.db";
    static final String DB_TABLE ="Contact";
    static final int DB_VERSION = 1;

    Cursor cursor;
    SQLiteDatabase sqLiteDatabase;
    ContentValues contentValues;
    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreateTable = "CREATE TABLE Contact("+
                "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT,"+
                "number Text)";
        db.execSQL(queryCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(oldVersion!=newVersion){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
            onCreate(sqLiteDatabase);
        }
    }

    public void InsertContact(Contact contact){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name",contact.getName());
        contentValues.put("number",contact.getNumber());

        sqLiteDatabase.insert(DB_TABLE,null,contentValues);
    }
    public int DelContact(int id){
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_TABLE,"id=?",new String[]{String.valueOf(id)});
    }
    public int DelContact2(){
        sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(DB_TABLE,null,null);
    }
    public void EditContacts(Contact contact,int id){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();

        contentValues.put("name",contact.getName());
        contentValues.put("number",contact.getNumber());

        sqLiteDatabase.update(DB_TABLE,contentValues,"id=?",new String[]{String.valueOf(id)});
    }
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        cursor =sqLiteDatabase.query(false, DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()){
            String Name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            contacts.add(new Contact(Name,phone));
        }
        return contacts;
    }
}
