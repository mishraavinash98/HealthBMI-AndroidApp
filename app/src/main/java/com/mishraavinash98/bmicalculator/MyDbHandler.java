package com.mishraavinash98.bmicalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Date;

public class MyDbHandler extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    MyDbHandler(Context context){
        //DBCreation
        super(context,"userdb",null,1);
        this.context=context;
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table userdb(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT ,date TEXT,bmi REAL,condition TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addRecord(String name,String date,double bmi,String condition){

        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("date",date);
        cv.put("bmi",bmi);
        cv.put("condition",condition);

        long rid=db.insert("userdb",null,cv);

        if(rid<0){
            Toast.makeText(context, "Insert issue", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "Record Saved", Toast.LENGTH_SHORT).show();
    }
    public String viewRecord(){

        StringBuffer sb=new StringBuffer();
        Cursor c=db.query("userdb",null,null,null,null,null,null);

        if(c.getCount()>0)
        {
            c.moveToFirst();
            do{
                String date=c.getString(c.getColumnIndex("date"));
                String name=c.getString(c.getColumnIndex("name"));
                String bmi=c.getString(c.getColumnIndex("bmi"));
                String condition=c.getString(c.getColumnIndex("condition"));

                sb.append("Name : "+name+" Date : "+date+" BMI : "+bmi+" Condition : "+condition+"\n");

            }while (c.moveToNext());
        }
        return sb.toString();

    }

}
