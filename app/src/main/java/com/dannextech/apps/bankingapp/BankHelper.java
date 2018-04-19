package com.dannextech.apps.bankingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 3/3/18.
 */

public class BankHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "banking";
    public static int DATABASE_VERSION = 1;

    public static final String CREATE_TABLE_USER = "CREATE TABLE " +
            BankContractor.UserAccountDb.TABLE_NAME + " ( " +
            BankContractor.UserAccountDb.COL_NAME + " TEXT, " +
            BankContractor.UserAccountDb.COL_AGE + " TEXT, " +
            BankContractor.UserAccountDb.COL_PHONE + " TEXT, " +
            BankContractor.UserAccountDb.COL_EMAIL + " TEXT, " +
            BankContractor.UserAccountDb.COL_ID + " TEXT PRIMARY KEY, " +
            BankContractor.UserAccountDb.COL_PASSWORD + " TEXT)";

    public static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE " +
            BankContractor.BankAccountDb.TABLE_NAME + " ( " +
            BankContractor.BankAccountDb.COL_NAME + " TEXT, " +
            BankContractor.BankAccountDb.COL_NUMBER + " TEXT, " +
            BankContractor.BankAccountDb.COL_AMOUNT + " TEXT, " +
            BankContractor.BankAccountDb.COL_USER_ID + " TEXT)";

    public static final String DROP_TABLE_ACCOUNT = "DROP TABLE IF EXISTS " + BankContractor.BankAccountDb.TABLE_NAME;
    public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS " + BankContractor.UserAccountDb.TABLE_NAME;

    public BankHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
