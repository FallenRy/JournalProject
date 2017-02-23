package uk.ac.tees.live.q5273477.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class EntryCRUD  {

    private DBHelper dbHelper;

    public EntryCRUD (Context context){
        dbHelper = new DBHelper(context);
    }

    public int insert(Entry entry){

        SQLiteDatabase db;
        ContentValues values = new ContentValues();
        long entry_id;


        try {
            db = dbHelper.getWritableDatabase();
            values.put(Entry.KEY_data_time, entry.date_time);
            values.put(Entry.KEY_cat, entry.categoty);
            values.put(Entry.KEY_text, entry.text);
            entry_id = db.insert(Entry.TABLE, null, values);
            db.close();
            return (int) entry_id;
        }catch (SQLException e){
            Log.d("EntryCRUD: insert", e.getMessage());
        }



    return -1;
    }

    public void delete(int entry_id){
        SQLiteDatabase db;


        try {
            db = dbHelper.getWritableDatabase();
            db.delete(Entry.TABLE, Entry.KEY_ID +"= ?",new String[]{String.valueOf(entry_id)});
            db.close();
        }catch (SQLException e){
            Log.d("EntryCRUD: delete", e.getMessage());
        }
    }

    public void update(Entry entry){
        SQLiteDatabase db;
        ContentValues values = new ContentValues();

        try {
            db = dbHelper.getWritableDatabase();
            values.put(Entry.KEY_data_time, entry.date_time);
            values.put(Entry.KEY_cat, entry.categoty);
            values.put(Entry.KEY_text, entry.text);

            db.update(Entry.TABLE, values, Entry.KEY_ID + "= ?", new String[]{String.valueOf(entry._id)});
            db.close();
        }catch (SQLException e){
            Log.d("EntryCRUD: update", e.getMessage());
        }
    }

    public ArrayList<HashMap<String, String>> getEntryList(){
        SQLiteDatabase db;

        try {
            db = dbHelper.getReadableDatabase();
            String selectQuery = "SELECT  " +
                    Entry.KEY_ID + "," +
                    Entry.KEY_text + "," +
                    Entry.KEY_cat + "," +
                    Entry.KEY_data_time +
                    " FROM " + Entry.TABLE;

            ArrayList<HashMap<String, String>> entryList = new ArrayList<HashMap<String, String>>();

            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {
                    HashMap<String, String> entry = new HashMap<>();
                    entry.put("id", cursor.getString(cursor.getColumnIndex(Entry.KEY_ID)));
                    entry.put("name", cursor.getString(cursor.getColumnIndex(Entry.KEY_text)));
                    entryList.add(entry);

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return entryList;

        }catch (SQLException e){
            Log.d("EntryCRUD: getEntryList", e.getMessage());
        }
        return null;
    }

    public Entry getEntryByID(int id){
        SQLiteDatabase db;

        try {
            db = dbHelper.getReadableDatabase();

            String selectQuery =  "SELECT  " +
                    Entry.KEY_ID + "," +
                    Entry.KEY_text + "," +
                    Entry.KEY_cat + "," +
                    Entry.KEY_data_time +
                    " FROM " + Entry.TABLE
                    + " WHERE " +
                    Entry.KEY_ID + "=?";

            Entry entry = new Entry();

            Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

            if (cursor.moveToFirst()) {
                do {
                    entry._id =cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                    entry.text =cursor.getString(cursor.getColumnIndex(Entry.KEY_text));
                    entry.categoty  =cursor.getString(cursor.getColumnIndex(Entry.KEY_cat));
                    entry.date_time =cursor.getLong(cursor.getColumnIndex(Entry.KEY_data_time));

                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return entry;

        }catch (SQLException e){
            Log.d("EntryCRUD: getEntryByID", e.getMessage());
        }

    return null;
    }

}
