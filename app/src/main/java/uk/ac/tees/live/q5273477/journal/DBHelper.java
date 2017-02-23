package uk.ac.tees.live.q5273477.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Theph on 23/02/2017.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "journal.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ENTRY = "CREATE TABLE " + Entry.TABLE  + "("
                + Entry.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Entry.KEY_text + " TEXT, "
                + Entry.KEY_cat + " TEXT, "
                + Entry.KEY_data_time + " REAL )";

        db.execSQL(CREATE_TABLE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE);

        // Create tables again
        onCreate(db);

    }
}
