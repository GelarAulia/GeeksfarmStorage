package com.gelaraulia.geeksfarmstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by G_Aulia on 26 Des 2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "VideoGames.db";
    public static final String TABLE_NAME = "videoGames";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "PUBLISHER";
    public static final String COL_4 = "PRICE";
    public static final String TABLE_CREATE = "CREATE TABLE "+TABLE_NAME+" ( "+
            COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COL_2+" TEXT, "+
            COL_3+" TEXT, "+
            COL_4+" TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean save_game(String name, String publisher, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, publisher);
        cv.put(COL_4, price);
        long result = db.insert(TABLE_NAME,null,cv);
        return result != 1;
    }

    public Cursor list_game(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor games = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return games;
    }

    public boolean update_game(String id, String name, String publisher, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1,id);
        cv.put(COL_2,name);
        cv.put(COL_3,publisher);
        cv.put(COL_4,price);
        db.update(TABLE_NAME,cv,"ID = ?",new String[]{id});
        return true;
    }

    public Integer delete_game(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID = ?",new String[]{id});
    }
}
