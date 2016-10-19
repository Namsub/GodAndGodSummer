package com.juntcompany.godandgodsummer.Manager;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.os.Build;

import com.juntcompany.godandgodsummer.Data.Message;
import com.juntcompany.godandgodsummer.Util.DBContants;
import com.juntcompany.godandgodsummer.Util.GodAndGod;

/**
 * Created by NSLee on 2016-09-28.
 */

//public class TargetManager extends SQLiteOpenHelper {
public class TargetManager {
/*
    private static TargetManager instance;
    public static TargetManager getInstance() {
        if (instance == null) {
            instance = new TargetManager();
        }
        return instance;
    }

*/
    private static final String DB_NAME = "target.db";
    private static final int DB_VERSION = 1;
//    private int[] result;

    public TargetManager() {
//        super(GodAndGod.getContext(), DB_NAME, null, DB_VERSION);
//        result = new int[4];
    }
    /*
    ///////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE " + DBContants.Target.TABLE_NAME + " ( ");
        //sb.append(DBContants.Target._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(DBContants.Target.COLUMN_FAITH + " INTEGER, ");
        sb.append(DBContants.Target.COLUMN_POPULAR + " INTEGER, ");
        sb.append(DBContants.Target.COLUMN_DONATE + " INTEGER, ");
        sb.append(DBContants.Target.COLUMN_FRIENDLY + " INTEGER); ");
        db.execSQL(sb.toString());
        System.out.println("onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(int faith, int popular, int donate, int friendly){
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        ContentValues values = new ContentValues();
        values.put(DBContants.Target.COLUMN_FAITH, faith);
        values.put(DBContants.Target.COLUMN_POPULAR, popular);
        values.put(DBContants.Target.COLUMN_DONATE, donate);
        values.put(DBContants.Target.COLUMN_FRIENDLY, friendly);
        db.insert(DBContants.Target.TABLE_NAME, null, values);
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_FAITH + ") VALUES(" + faith + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_POPULAR + ") VALUES(" + popular + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_DONATE + ") VALUES(" + donate + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_FRIENDLY + ") VALUES(" + friendly + ");");
        db.close();
    }
    public void update(int rowid, int faith, int popular, int donate, int friendly){
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 변경
        ContentValues values = new ContentValues();
        values.put(DBContants.Target.COLUMN_FAITH, faith);
        values.put(DBContants.Target.COLUMN_POPULAR, popular);
        values.put(DBContants.Target.COLUMN_DONATE, donate);
        values.put(DBContants.Target.COLUMN_FRIENDLY, friendly);
        db.update(DBContants.Target.TABLE_NAME, values, "rowid=?", new String[]{Integer.toString(rowid)});
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_FAITH + "=" + faith + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_POPULAR + "=" + popular + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_DONATE + "=" + donate + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_FRIENDLY + "=" + friendly + " WHERE " + DBContants.Target._ID + "=1");
        db.close();
    }

    public int[] select(){
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        //DB에 있는 데이터를 쉽게 처리하기 위해 Cursor 사용
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBContants.Target.TABLE_NAME, null);
        while (cursor.moveToNext()){
            result[0] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_FAITH)); //faith
            result[1] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_POPULAR)); //popular
            result[2] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_DONATE)); //donate
            result[3] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_FRIENDLY)); //friendly
        }
        return result;
    }
    public void delete(int rowid){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(DBContants.Target.TABLE_NAME, "rowid=?", new String[]{Integer.toString(rowid)});
        db.close();
    }

    public void drop(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("drop table " + DBContants.Target.TABLE_NAME);
        db.close();
    }
    */
}
