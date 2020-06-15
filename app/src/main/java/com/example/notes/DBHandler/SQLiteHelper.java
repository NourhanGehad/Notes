package com.example.notes.DBHandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.notes.NoteModel;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void createTable(){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "CREATE TABLE IF NOT EXISTS NOTES(Id INTEGER PRIMARY KEY AUTOINCREMENT, title VARCHAR, description VARCHAR)";
        database.execSQL(sql);
    }

    public void insertData(String title, String description){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO NOTES(title, description) VALUES (?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindString(1, title);
        statement.bindString(2, description);
        statement.executeInsert();
        database.close();
    }

    public void updateData(String title, String description, Integer id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE NOTES SET title = ?, description = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,title);
        statement.bindString(2,description);
        statement.bindDouble(3,id);

        statement.execute();
        database.close();

    }

    public void deleteNote(Integer id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM NOTES WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindDouble(1,id);
        statement.execute();
        database.close();
    }

    public ArrayList<NoteModel> getNotes(){
        ArrayList<NoteModel> notes = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT Id, title, description FROM NOTES";

        Cursor cursorData = database.rawQuery(sql,null);

        int id;
        String title;
        String description;

        try{
            if(cursorData != null && cursorData.getCount() > 0){
                if(cursorData.moveToFirst()){
                    do{
                        id = cursorData.getInt(0);
                        title = cursorData.getString(1);
                        description = cursorData.getString(2);

                        NoteModel note = new NoteModel(id, title, description);
                       notes.add(note);
                    } while (cursorData.moveToNext());
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(cursorData != null) {
                cursorData.close();
            }
        }

        return notes;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
