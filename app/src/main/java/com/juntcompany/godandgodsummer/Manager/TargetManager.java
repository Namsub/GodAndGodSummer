package com.juntcompany.godandgodsummer.Manager;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.util.Calendar;
import android.os.Build;

import com.juntcompany.godandgodsummer.Data.Chat;
import com.juntcompany.godandgodsummer.Data.Message;
import com.juntcompany.godandgodsummer.Util.DBContants;
import com.juntcompany.godandgodsummer.Util.GodAndGod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NSLee on 2016-09-28.
 */


public class TargetManager extends SQLiteOpenHelper {
//public class TargetManager {

    private static TargetManager instance;
    public static TargetManager getInstance() {
        if (instance == null) {
            instance = new TargetManager();
        }
        return instance;
    }

    private static final String DB_NAME = "target.db";
    private static final int DB_VERSION = 1;
//    private int[] result;

    public TargetManager() {
        super(GodAndGod.getContext(), DB_NAME, null, DB_VERSION);
//        result = new int[4];
    }

    ///////////////////////////
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE " + DBContants.Target.TABLE_NAME + " ( ");
        //sb.append(DBContants.Target._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(DBContants.Target.COLUMN_NAME + " TEXT PRIMARY KEY, ");
        sb.append(DBContants.Target.COLUMN_LAST_SPEAK + " TEXT, ");
        sb.append(DBContants.Target.COLUMN_LAST_TIME + " TEXT); ");
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
        values.put(DBContants.Target.COLUMN_NAME, faith);
        values.put(DBContants.Target.COLUMN_LAST_SPEAK, popular);
        values.put(DBContants.Target.COLUMN_LAST_TIME, donate);
        db.insert(DBContants.Target.TABLE_NAME, null, values);
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_FAITH + ") VALUES(" + faith + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_POPULAR + ") VALUES(" + popular + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_DONATE + ") VALUES(" + donate + ");");
        //db.execSQL("INSERT INTO " + DBContants.Target.TABLE_NAME + " (" + DBContants.Target.COLUMN_FRIENDLY + ") VALUES(" + friendly + ");");
        db.close();
    }
    public void update(String name, String last_speak, String last_time){
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 변경
        ContentValues values = new ContentValues();
        values.put(DBContants.Target.COLUMN_NAME, name);
        values.put(DBContants.Target.COLUMN_LAST_SPEAK, last_speak);
        values.put(DBContants.Target.COLUMN_LAST_TIME, last_time);
        db.update(DBContants.Target.TABLE_NAME, values, "name=?", new String[]{name});
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_FAITH + "=" + faith + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_POPULAR + "=" + popular + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_DONATE + "=" + donate + " WHERE " + DBContants.Target._ID + "=1");
        //db.execSQL("UPDATE " + DBContants.Target.TABLE_NAME + " SET " + DBContants.Target.COLUMN_FRIENDLY + "=" + friendly + " WHERE " + DBContants.Target._ID + "=1");
        db.close();
    }

    public List<Chat> select(String name){
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();

        //DB에 있는 데이터를 쉽게 처리하기 위해 Cursor 사용
        Cursor cursor = db.rawQuery("SELECT * FROM " + DBContants.Target.TABLE_NAME + " WHERE NAME=" + name + ";", null);
        List<Chat> room_list = new ArrayList<Chat>();
        while (cursor.moveToNext()){
//            result[0] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_NAME)); //faith
//            result[1] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_)); //popular
//            result[2] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_DONATE)); //donate
//            result[3] = cursor.getInt(cursor.getColumnIndex(DBContants.Target.COLUMN_FRIENDLY)); //friendly
        }

        return room_list;
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

}
