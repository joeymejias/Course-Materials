package ly.generalassemb.sqliteopenhelperdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by joey on 7/12/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "favorites.db";
    public static final String FAVORITES_TABLE_NAME = "favoritesTable";

    public static final String COL_ID = "_id";
    public static final String COL_GAME_NAME = "gameName";
    public static final String COL_GAME_YEAR = "year";

    private static DatabaseHelper sInstance;

    private DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context){
        if(sInstance == null){
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createString = "CREATE TABLE " + FAVORITES_TABLE_NAME + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_GAME_NAME + " TEXT NOT NULL, " +
                COL_GAME_YEAR + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(createString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAVORITES_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addFavorite(String name, int year){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_GAME_NAME, name);
        values.put(COL_GAME_YEAR, year);
        db.insert(FAVORITES_TABLE_NAME, null, values);

        db.close();
    }

    public Cursor getAllGames(){
        SQLiteDatabase db = getReadableDatabase();
        return db.query(FAVORITES_TABLE_NAME, null, null, null, null, null, null);
    }

    public int getGameCount(String name){
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {COL_GAME_NAME};

        String selection = COL_GAME_NAME + " = ?";
        String[] selectionArgs = {name};

        Cursor cursor = db.query(FAVORITES_TABLE_NAME, projection, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    public void deleteGameByName(String name) {
        SQLiteDatabase db = getWritableDatabase();

        String[] projection = {COL_GAME_NAME};

        String selection = COL_GAME_NAME + " = ?";
        String[] selectionArgs = {name};

        db.delete(FAVORITES_TABLE_NAME, selection, selectionArgs);
//        db.delete(FAVORITES_TABLE_NAME,
//                COL_GAME_NAME + " = " + name,
//                new String[]{});
        db.close();
    }

    public void UpdateGameYear(String name, int year){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_GAME_YEAR, year);

        String selection = COL_GAME_YEAR + " = ?";
        String[] selectionArgs = {name};
        db.update(FAVORITES_TABLE_NAME, values, selection, selectionArgs);
        db.close();
    }
}
