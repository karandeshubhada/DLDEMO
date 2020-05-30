package com.demo.defineslabdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.demo.defineslabdemo.db.StoredList;

import java.util.ArrayList;

/*
package com.demo.defineslabdemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import java.util.ArrayList;

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "Demo";
    private	static final String TABLE_LIST = "List";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SID = "id";
    private static final String COLUMN_NAME = "name";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_TABLE = "CREATE	TABLE " + TABLE_LIST + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SID + " INTEGER PRIMARY KEY,"  + COLUMN_NAME + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    public ArrayList<StoredList> list(){
        String sql = "select * from " + TABLE_LIST;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<StoredList> storelist = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String sid = cursor.getString(1);
                String name = cursor.getString(2);
                storelist.add(new StoredList(id,sid, name));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storelist;
    }

    public void addContacts(StoredList contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_LIST, null, values);
    }

    public void updateContacts(StoredList contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_LIST, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(contacts.getId())});
    }

    public Cursor fetch() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_LIST, null);


        return cursor;
    }
    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
*/
public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "foresquare";
    private	static final String TABLE_FORESQUARE = "foresquares";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "FORESQUARE";
    private static final String COLUMN_SID = "sid";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_TABLE = "CREATE	TABLE " + TABLE_FORESQUARE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_SID + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORESQUARE);
        onCreate(db);
    }

    public boolean isExist(String string) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FORESQUARE + " WHERE FORESQUARE = '" + string + "'", null);
        boolean exist = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return exist;

    }

    public Cursor fetch() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_FORESQUARE, null);
        return cursor;
    }

    public void add(StoredList contacts){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contacts.getName());
        values.put(COLUMN_SID, contacts.getSid());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_FORESQUARE, null, values);
    }
    public void delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FORESQUARE, COLUMN_SID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}