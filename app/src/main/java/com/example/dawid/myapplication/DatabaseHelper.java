package com.example.dawid.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;


/**
 * Created by Dawid on 2017-06-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String database_name="Baza Telefonow";
    public static final String database_table="Telefony";


    public DatabaseHelper(Context context) {
        super(context, database_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table "+database_table+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MODEL TEXT, MARKA TEXT)");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ database_table);
        onCreate(db);

    }

    public boolean wstawDane(String Model, String Marka)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("Marka",Marka);
        cv.put("Model",Model);
        if(db.insert(database_table,null,cv)==-1)
        {
            return false ;
        }
        else
        {
            return true;
        }
    }

    public  SQLiteCursor pobierzdane()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteCursor kursor =(SQLiteCursor) db.rawQuery("SELECT * FROM " + database_table,null);
        return kursor;
    }

    public boolean aktualizuj(String id, String Model, String Marka)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("Marka",Marka);
        cv.put("Model",Model);
        db.update(database_table,cv, "ID = ?", new String[]{id});
        return true;
    }

    public boolean usun(String id)
    {
        SQLiteDatabase db= this.getWritableDatabase();

        if(db.delete(database_table,"ID = ?", new String[]{id})>0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}