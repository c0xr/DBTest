package com.cory.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static String name="test db";
    private static int version=1;

    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table employee(" +
                "ssn varchar(20) primary key," +
                "name varchar(1) not null,"+
                "bdate varchar(10) not null," +
                "address varchar(30) not null," +
                "sex varchar(2) not null," +
                "salary float not null," +
                "superssn varchar(18) not null," +
                "dno varchar(3) not null)");

        db.execSQL("create table department(" +
                "dnumber varchar(3) primary key," +
                "dname varchar(30) not null," +
                "mgrssn varchar(18) not null," +
                "mgrstartdate date not null) ");

        db.execSQL("create table depart_location(" +
                "dnumber char(3) primary key," +
                "dlocation varchar(30) not null) ");

        db.execSQL("create table project(" +
                "pnumber char(3) primary key," +
                "pname varchar(30) not null," +
                "plocation varcahr(30) not null," +
                "dnum char(3) not null)");

        db.execSQL("create table works_on(" +
                "essn char(18)," +
                "pno char(3)," +
                "hours int not null," +
                "primary key(essn,pno))");

        db.execSQL("create table dependent(" +
                "essn char(18)," +
                "dependent_name char(10)," +
                "sex char(2) not null," +
                "bdate char(10) not null," +
                "relationship char(10) not null," +
                "primary key(essn,dependent_name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
