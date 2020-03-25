package com.example.yourmission;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBAdapter {
    private static final String KEY_ROWID   = "_id";
    private static final String KEY_NAME    = "name";
    private static final String KEY_DESC    = "description";
    private static final String KEY_DATE    = "deadline";
    private static final String TAG         = "DB Adapter";
    private static final String DB_NAME     = "MyDB";
    private static final String DB_TABLE    = "task";
    private static final int    DB_VERSION  = 1;
    private static final String DB_CREATE   = "create table "
            + DB_TABLE + "("
            + KEY_ROWID + " integer primary key autoincrement, "
            + KEY_NAME + " text not null, "
            + KEY_DESC + " text not null, "
            + KEY_DATE + " text not null);";
    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context){
            super(context, DB_NAME, null, DB_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to"
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS task");
            onCreate(db);
        }
    }

    // Open the database
    DBAdapter open(){
        db = DBHelper.getWritableDatabase();
        return this;
    }

    // Close the database
    void close(){
        DBHelper.close();
    }

    // Insert a task
    long insertTask(String nameTask, String descTask, String deadlineTask){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, nameTask);
        initialValues.put(KEY_DESC, descTask);
        initialValues.put(KEY_DATE, deadlineTask);
        return db.insert(DB_TABLE, null, initialValues);
    }

    long insertTask(String rowId, String nameTask, String descTask, String deadlineTask){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ROWID, rowId);
        initialValues.put(KEY_NAME, nameTask);
        initialValues.put(KEY_DESC, descTask);
        initialValues.put(KEY_DATE, deadlineTask);
        return db.insert(DB_TABLE, null, initialValues);
    }

    // Retrieve data.
    Cursor getAllTask(){
        return db.query(DB_TABLE, new String[] {KEY_ROWID, KEY_NAME, KEY_DESC, KEY_DATE}, null, null, null, null, null);
    }

    // Update Task.
    boolean updateTask(long RowId, String name, String description, String date){
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_DESC, description);
        args.put(KEY_DATE, date);
        return db.update(DB_TABLE, args, KEY_ROWID + "=" + RowId, null) > 0;
    }


    // Delete Task.
    boolean deleteContact(long rowId){
        return db.delete(DB_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
