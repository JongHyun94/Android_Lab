package com.example.android.lab6_3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {    //DB를 생성해주기 위한 문장을 담고 있는 메소드.
       //Student 라는 테이블 생성 문장.
        String sql = "create table student (" + "name text, " + "studentNo integer);";
        db.execSQL(sql); //Query문 실행.
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists student"; //만약에 "student" 라는 테이블이 이미 있다면 그 테이블을 삭제.
        db.execSQL(sql); //Query문 실행.
        onCreate(db);
    }
}