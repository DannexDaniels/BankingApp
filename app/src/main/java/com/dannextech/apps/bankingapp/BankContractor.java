package com.dannextech.apps.bankingapp;

import android.provider.BaseColumns;

/**
 * Created by Dannex Daniels on 3/3/18.
 */

public class BankContractor {
    public BankContractor() {

    }

    public static class UserAccountDb implements BaseColumns{
        public static final String TABLE_NAME = "user";

        public static final String COL_NAME = "name";
        public static final String COL_ID = "idno";
        public static final String COL_AGE = "age";
        public static final String COL_PHONE = "phone";
        public static final String COL_EMAIL = "email";
        public static final String COL_PASSWORD = "password";
    }

    public static class BankAccountDb implements BaseColumns{
        public static final String TABLE_NAME = "bank";

        public static final String COL_USER_ID = "idno";
        public static final String COL_NAME = "name";
        public static final String COL_NUMBER = "account";
        public static final String COL_AMOUNT = "amount";


    }
}
