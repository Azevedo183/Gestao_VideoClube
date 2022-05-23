package com.example.gestovideoclube;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class BaseDeDados extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "VideoClube.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "filmes";
    private static final String COLLUMN_TITLE = "titulo_filme";
    private static final String COLLUMN_YEAR = "ano_filme";
    private static final String COLLUMN_TIME = "tempo_filme";

    public BaseDeDados(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLLUMN_TITLE + " TEXT, " +
                COLLUMN_YEAR + " INTEGER, " +
                COLLUMN_TIME + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
