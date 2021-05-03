package com.example.applicationwithsqlite.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DBController extends SQLiteOpenHelper {
    public DBController(Context context) {
        super(context, "ProdiTI", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table teman (id interger primary key, nama text, telepon text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists teman");
        onCreate(db);
    }

    public void insertData(HashMap<String,String> queryValues){
        SQLiteDatabase basisData = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("nama",queryValues.get("nama"));
        nilai.put("telepon",queryValues.get("telepon"));
        basisData.insert("teman", null, nilai);
        basisData.close();
    }

    public void updateTeman(HashMap<String,String> qValues){
        SQLiteDatabase basisData = this.getWritableDatabase();
        ContentValues nilai = new ContentValues();
        nilai.put("id",qValues.get("id"));
        nilai.put("nama", qValues.get("nama"));
        nilai.put("telepon",qValues.get("telepon"));
//        basisData.update("teman",nilai,"id=?",new String[]{qValues.get("id")});
        basisData.update("teman",nilai,"nama"+"=?", new String[]{qValues.get("nama")});
//        basisData.update("teman",nilai,"telepon"+"=?", new String[]{qValues.get("telepon")});
        basisData.close();
    }

    public void deleteTeman(HashMap<String,String> qValues){
        SQLiteDatabase basisData = this.getWritableDatabase();
        basisData.delete("teman", "nama=?", new String[]{qValues.get("nama")});
        basisData.close();
    }

    public ArrayList<HashMap<String,String>> getAllTeman(){
        ArrayList<HashMap<String,String>> daftarTeman;
        daftarTeman = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM teman";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                HashMap<String,String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama",cursor.getString(1));
                map.put("telepon",cursor.getString(2));
                daftarTeman.add(map);
            }while (cursor.moveToNext());
        }
        db.close();
        return daftarTeman;
    }

//    public HashMap<String,String> getTemanInfo(String idTeman){
//        HashMap<String,String> temanList = new HashMap<String, String>();
//        SQLiteDatabase basisData = this.getReadableDatabase();
//        String selectQuery = "SELECT * FROM teman where id='"+idTeman+"'";
//        Cursor cursor = basisData.rawQuery(selectQuery, null);
//        if (cursor.moveToFirst()){
//            do{
//                temanList.put("id", cursor.getString(0));
//                temanList.put("nama", cursor.getString(1));
//                temanList.put("telepon", cursor.getString(2));
//            }while (cursor.moveToNext());
//        }
//        return temanList;
//    }
}
