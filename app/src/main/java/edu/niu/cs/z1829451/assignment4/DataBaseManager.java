/*Group Members: SIVALINGAM SUNDARARAJ SHANTHI z1829451
                 YOKESH SRIHARI z1809328*/

///////////////////////////////////////////////////////////////////////////////////////////////////////
//This is a database helper class which helps in inserting, deleting, updating and clearing the items//
//in the database                                                                                    //
///////////////////////////////////////////////////////////////////////////////////////////////////////
package edu.niu.cs.z1829451.assignment4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by ss siva on 4/26/2018.
 */

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "checkDb",
                                TABLE_NAME    = "checked",
                                ID            = "id",
                                ITEM          = "item";

    private static final int VERSION_NUMBER = 1;

    public DataBaseManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "create table " + TABLE_NAME
                            + "( " + ID + " integer primary key autoincrement, "
                            + ITEM + " text" + " )";

        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop = "drop table if exists " + TABLE_NAME;

        db.execSQL(sqlDrop);

        onCreate(db);
    }

    ///////////////////////////////////////////////////////////
    //This function is used to insert items into the database//
    ///////////////////////////////////////////////////////////

    public void insert(Check check){
        SQLiteDatabase database = getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_NAME
                            + " values( null, '" + check.getItem()
                            + "' )";

        database.execSQL(sqlInsert);
        database.close();
    }

    //////////////////////////////////////////////////////////////////////////////////
    //This function returns an array list of items which are present in the database//
    //////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Check> selectAll(){
        String sqlQuery = "select * from " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();

        Cursor cursor = database.rawQuery(sqlQuery,null);

        ArrayList<Check> checkList = new ArrayList<>();

        while(cursor.moveToNext()){
            Check check = new Check(Integer.parseInt(cursor.getString(0)),cursor.getString(1));

            checkList.add(check);
        }

        database.close();

        return checkList;
    }

    ///////////////////////////////////////////////////////////
    //This function is used to delete items from the database//
    ///////////////////////////////////////////////////////////
    public void deleteById(int id){
        String sqlDelete = "delete from " + TABLE_NAME + " where " + ID + " = " + id;

        SQLiteDatabase database = getWritableDatabase();

        database.execSQL(sqlDelete);

        database.close();
    }

    ///////////////////////////////////////////////////////////////////
    //This function is used to delete all the items from the database//
    ///////////////////////////////////////////////////////////////////
    public void clear(){
        String sqlClear = "delete from " + TABLE_NAME;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlClear);

        database.close();
    }

    /////////////////////////////////////////////////////////////
    //This function is used to update the items in the database//
    /////////////////////////////////////////////////////////////
    public void updateById(int id, String item){
        String sqlUpdate = "update " + TABLE_NAME + " set " + ITEM + " = '" + item + "'" + " where " + ID + " = " + id;

        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sqlUpdate);
        database.close();
    }
}
