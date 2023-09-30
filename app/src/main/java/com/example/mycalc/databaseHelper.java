package com.example.mycalc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {
    public static final String CALCULATOR = "CALCULATOR";
    public static final String calc_ID = "id";
    public static final String calc_EXPRESSION = "expression";
    public static final String calc_ANS = "ans";

    public databaseHelper(@Nullable Context context) {
        super(context, "History", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable= "CREATE TABLE " + CALCULATOR +
                "(" + calc_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + calc_EXPRESSION + " TEXT, " + calc_ANS + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }
    // Adding values to database
    public boolean addVal(calculator_hist histroyAdd)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(calc_EXPRESSION,histroyAdd.getExpression());
        cv.put(calc_ANS,histroyAdd.getAns());
        long insert = db.insert(CALCULATOR, null, cv);
        if(insert==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    // Reading values form database

    public ArrayList<calculator_hist> getVals(){
        ArrayList<calculator_hist> returnList=new ArrayList<>(); // general syntax for creating a new array list
        String query="SELECT * FROM "+CALCULATOR; // query to read all values
        SQLiteDatabase db= this.getReadableDatabase();// calling database
        Cursor cursor = db.rawQuery(query,null);// resultset: this would be holding all the values of the database
        // To traverse the resultset from the first
        if (cursor.moveToFirst()) //to check whether being pointed to 1st data
        {
            do{
              String exp=cursor.getString(1); // extract values
              String ans=cursor.getString(2);


              calculator_hist hist=new calculator_hist(exp,ans); // creating the object of class
              returnList.add(hist); //adding object to arraylist
            }while (cursor.moveToNext());// increment the pointer
        }
        else
        {
            // do nothing...
        }
        // Close the cursor and connection
        cursor.close();
        db.close();
        // return arraylist
        return returnList;
    }

    // deleting values
    public boolean deleteData(calculator_hist hist)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String q="DELETE FROM "+CALCULATOR;
        Cursor cursor = db.rawQuery(q, null);
        if(cursor.moveToFirst())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
