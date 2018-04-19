package com.dannextech.apps.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by root on 3/3/18.
 */

public class BankQueries {

    Context context;
    BankHelper helper;
    SQLiteDatabase database;

    public BankQueries(Context context) {
        this.context = context;
        helper = new BankHelper(context);
    }

    public boolean createAccount(String name, String id, String age, String email, String phone, String password){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BankContractor.UserAccountDb.COL_NAME,name);
        values.put(BankContractor.UserAccountDb.COL_ID,id);
        values.put(BankContractor.UserAccountDb.COL_AGE,age);
        values.put(BankContractor.UserAccountDb.COL_EMAIL,email);
        values.put(BankContractor.UserAccountDb.COL_PHONE,phone);
        values.put(BankContractor.UserAccountDb.COL_PASSWORD,password);

        long result = database.insert(BankContractor.UserAccountDb.TABLE_NAME,null,values);

        return result==-1?false:true;
    }

    public boolean authenticateUser(String userName, String password){
        database = helper.getReadableDatabase();

        String user = null, pass = null, email = null, age = null, phone = null, uId = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();

        String columns[] = {BankContractor.UserAccountDb.COL_EMAIL, BankContractor.UserAccountDb.COL_PASSWORD,BankContractor.UserAccountDb.COL_NAME,BankContractor.UserAccountDb.COL_ID,BankContractor.UserAccountDb.COL_PHONE,BankContractor.UserAccountDb.COL_AGE};
        String selection = BankContractor.UserAccountDb.COL_EMAIL + " = ? ";
        String selectionArgs[] = {userName};

        Cursor cursor = database.query(BankContractor.UserAccountDb.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        while (cursor.moveToNext()){
            user = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_NAME));
            pass = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_PASSWORD));
            email = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_EMAIL));
            age = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_AGE));
            phone = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_PHONE));
            uId = cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.UserAccountDb.COL_ID));
        }

        if (email==null){
            Toast.makeText(context,"User with email address "+ userName + " doesn't exist",Toast.LENGTH_LONG).show();
        }

        if (userName.equals(email) && password.equals(pass)){
            edit.putString(BankContractor.UserAccountDb.COL_NAME,user);
            edit.putString(BankContractor.UserAccountDb.COL_EMAIL,email);
            edit.putString(BankContractor.UserAccountDb.COL_ID,uId);
            edit.putString(BankContractor.UserAccountDb.COL_PHONE,phone);
            edit.putString(BankContractor.UserAccountDb.COL_AGE,age);
            edit.putString(BankContractor.UserAccountDb.COL_NAME,user);
            edit.apply();
            return true;
        }else {
            return false;
        }
    }

    public boolean createBankAccount(String userId, String bankName, String accountNo, String deposit){
        database = helper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BankContractor.BankAccountDb.COL_USER_ID,userId);
        values.put(BankContractor.BankAccountDb.COL_NAME,bankName);
        values.put(BankContractor.BankAccountDb.COL_NUMBER,accountNo);
        values.put(BankContractor.BankAccountDb.COL_AMOUNT,deposit);

        long result = database.insert(BankContractor.BankAccountDb.TABLE_NAME,null,values);

        return result==-1?false:true;
    }

    public BankModel[] retrieveBankDetails(){
        database = helper.getReadableDatabase();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        String columns[] = {BankContractor.BankAccountDb.COL_USER_ID,BankContractor.BankAccountDb.COL_NAME,BankContractor.BankAccountDb.COL_NUMBER,BankContractor.BankAccountDb.COL_AMOUNT};
        String selection = BankContractor.BankAccountDb.COL_USER_ID + " =? ";
        String selectionArgs[] = {preferences.getString(BankContractor.UserAccountDb.COL_ID,null)};

        Cursor cursor = database.query(BankContractor.BankAccountDb.TABLE_NAME,columns,selection,selectionArgs,null,null,null);

        BankModel[] models = new BankModel[cursor.getCount()];

        int position = 0;
        while (cursor.moveToNext()){
            models[position] = new BankModel();
            models[position].setAccountNo(cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.BankAccountDb.COL_NUMBER)));
            models[position].setBankName(cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.BankAccountDb.COL_NAME)));
            models[position].setAmount(cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.BankAccountDb.COL_AMOUNT)));
            models[position].setUserId(cursor.getString(cursor.getColumnIndexOrThrow(BankContractor.BankAccountDb.COL_USER_ID)));

            position++;
        }
        return models;
    }

}
