package com.lyondry.lyondry.Hnadler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lyondry.lyondry.Modals.LocalResponse;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {
    public Context context;
    public static final String DATABASE_NAME = "dataManager";

    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "data";
    public static final String KEY_ID = "id";
    public static final String KEY_IMG_URL = "ImgFavourite";
    public static final String KEY_Order_No = "OrderNo";

    private SQLiteDatabase sqLiteDatabase;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        //Toast.makeText(context, "Constructor called", Toast.LENGTH_LONG).show();
    }

    //Add Items Data
    public void imgAdd(LocalResponse localResponse){
        ContentValues contentValues =new ContentValues();
        contentValues.put(DataBaseHandler.KEY_ID,localResponse.getUid());
        contentValues.put(DataBaseHandler.KEY_Order_No,localResponse.getOrderId());
        contentValues.put(DataBaseHandler.KEY_IMG_URL,localResponse.getImage());


        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DataBaseHandler.TABLE_NAME, null, contentValues);
    }

    public List<LocalResponse> getLocalResponse() {
        //String sql = "select * from "+ TABLE_NAME ;
        String sql = "select (select count(*) from " + TABLE_NAME + " a  where a.id >= b.id)sno,b.id,ImgFavourite,OrderNo" +
                " from " + TABLE_NAME + " b order by sno";
        sqLiteDatabase = this.getReadableDatabase();
        List<LocalResponse>  localResponses = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            localResponses.clear();
            do {

                int Id = Integer.parseInt(String.valueOf(cursor.getInt(0)));
                int OrderId = cursor.getInt(1);
                String ImageItems = cursor.getString(2);




                localResponses.add(new LocalResponse(Id,OrderId,ImageItems));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return localResponses;
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_Order_No + " TEXT ," +
            KEY_IMG_URL + " TEXT" + ")";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + "";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void deleteEntry(long row) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, KEY_ID + "=" + row, null);
    }

}