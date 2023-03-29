package com.folioreader.model.sqlite;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DoshamDatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    private static String DB_NAME ="database.db"; // Database name
    private static int DB_VERSION = 1; // Database version
    private final File DB_FILE;
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    public DoshamDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.mContext = context;
    }

    public void createDataBase() throws IOException {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DB_NAME);
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    // Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        // Log.v("DB_PATH", DB_FILE.getAbsolutePath());
        mDataBase = SQLiteDatabase.openDatabase(String.valueOf(DB_FILE), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        mDataBase.setVersion(2);
        // mDataBase = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if(mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}


/*public class DoshamDatabaseHelper extends SQLiteOpenHelper {

    private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window
    private static String DATABASE_NAME ="database.db"; // Database name
    private static int DATABASE_VERSION = 2; // Database version
    private final File DB_FILE;
    private SQLiteDatabase mDataBase;
    private final Context mContext;

    private static DoshamDatabaseHelper mInstance;


    *//*public DoshamDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
        DB_FILE = context.getDatabasePath(DB_NAME);
        this.mContext = context;
    }*//*

    public DoshamDatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        DB_FILE = context.getDatabasePath(DATABASE_NAME);
        mContext = context;
    }

    public static void clearInstance() {
        mInstance = null;
    }

    @Override
    public final void onCreate(final SQLiteDatabase db) {
        // If the database does not exist, copy it from the assets.
        boolean mDataBaseExist = checkDataBase();
        if(!mDataBaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                // Copy the database from assests
                copyDataBase();
                Log.e(TAG, "createDatabase database created");
            } catch (IOException mIOException) {
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    public SQLiteDatabase getMyWritableDatabase() {
        if ((mDataBase == null) || (!mDataBase.isOpen())) {
            mDataBase = this.getWritableDatabase();
        }

        return mDataBase;
    }

    public static DoshamDatabaseHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DoshamDatabaseHelper(context);
        }
        return mInstance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



    // Check that the database file exists in databases folder
    private boolean checkDataBase() {
        return DB_FILE.exists();
    }

    // Copy the database from assets
    private void copyDataBase() throws IOException {
        InputStream mInput = mContext.getAssets().open(DATABASE_NAME);
        OutputStream mOutput = new FileOutputStream(DB_FILE);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    // Open the database, so we can query it
    public boolean openDataBase() throws SQLException {
        // Log.v("DB_PATH", DB_FILE.getAbsolutePath());
        mDataBase = SQLiteDatabase.openDatabase(String.valueOf(DB_FILE), null, SQLiteDatabase.CREATE_IF_NECESSARY);
        // mDataBase = SQLiteDatabase.openDatabase(DB_FILE, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    @Override
    public synchronized void close() {
        if(mDataBase != null) {
            mDataBase.close();
        }
        super.close();
    }

}*/



