package com.folioreader.model.sqlite;


import java.io.IOException;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DoshamDbAdapter {
    protected static final String TAG = "DataAdapter";

    private final Context mContext;
    private  static  SQLiteDatabase mDb;
    private DoshamDatabaseHelper mDbHelper;

    public DoshamDbAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DoshamDatabaseHelper(mContext);
    }

    public DoshamDbAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public DoshamDbAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            //mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }


    public static Cursor getDosh(String dosh) {
        Log.e(TAG, "getDosh >>" +dosh);


        dosh.replaceAll("i", "I");
        dosh.replaceAll("l", "I");
        dosh.replaceAll("I", "I");

        Log.e(TAG, "doshCHanged >> " +dosh);

        try {
            //String sql = "SELECT * FROM dosham where word LIKE" +  dosh  + "LIMIT 1";
            Cursor mCur = mDb.rawQuery("SELECT * FROM dosham where word1 like '%" + dosh + "%'", null);
            if (((mCur != null) && (mCur.getCount() > 0))) {
                mCur.moveToFirst();
                /*if(!dosh.equals(mCur.getColumnIndex("word1"))){
                    mCur = mDb.rawQuery("SELECT * FROM dosham where word1 match '%" + dosh + "%'", null);
                    mCur.moveToFirst();

                }*/

            } else {
                dosh = dosh.substring(0, dosh.length() - 2);
                mCur = mDb.rawQuery("SELECT * FROM dosham where word1 like '%" + dosh + "%'", null);
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }
    /*protected static final String TAG = "DoshamDataAdapter";

    //private final Context mContext;
    public static SQLiteDatabase mDb;
   // private DoshamDatabaseHelper mDbHelper;


    *//*public static void initialize(Context mContext) {
        mDb = DoshamDatabaseHelper.getInstance(mContext).getMyWritableDatabase();
    }*//*

    public static void terminate() {
        FolioDatabaseHelper.clearInstance();
    }


    public static Cursor getTestData() {
        try {
            String sql = "SELECT * FROM dosham where id = 1";
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            Log.e(TAG, "getTestData >>" + mCur.toString());
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }*/

    /*public DoshamDbAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DoshamDatabaseHelper(mContext);
    }*/

    /*public DoshamDbAdapter createDatabase() throws SQLException {
        try {
            mDbHelper.createDataBase();
        } catch (IOException mIOException) {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }


    public SQLiteDatabase getMyWritableDatabase() {
        if ((mDbHelper == null) || (!mDbHelper.isOpen())) {
            mDbHelper = this.getWritableDatabase();
        }

        return mDbHelper;
    }

    public DoshamDbAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDbHelper.close();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public Cursor getTestData() {
        try {
            String sql ="SELECT * FROM dosham where id = 1";
            Cursor mCur = mDb.rawQuery(sql, null);
            if (mCur != null) {
                mCur.moveToNext();
            }
            return mCur;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>"+ mSQLException.toString());
            throw mSQLException;
        }
    }*/
}



