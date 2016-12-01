package com.example.hyeonseok.gumpulza_v003;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public int rowCount() {
        int row = 0;
        String selectQuery = "SELECT * FROM S_2016_01";
        Cursor cursor = database.rawQuery(selectQuery, null);
        row = cursor.getCount();
        return row;
    }

    public List<Question> getAllQuestions() {

        List<Question> list = new ArrayList<Question>();

        Cursor cursor = database.rawQuery("SELECT * FROM S_2016_01", null);

        if (cursor.moveToFirst()) {
            do {

                Question quest = new Question();

                quest.setQUESTION(cursor.getBlob(0));
                quest.setANSWER(cursor.getString(1));

                list.add(quest);

            } while (cursor.moveToNext());
        }

        return list;

    }


}
