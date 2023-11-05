package com.example.camelcasetestt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DataClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "IITDHResourcesApp.db";
    SQLiteDatabase db;
    Cursor c;


    @Override
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("CREATE TABLE \"users\" (\n" +
               "\t\"Name\"\tTEXT NOT NULL,\n" +
               "\t\"id\"\tINTEGER NOT NULL,\n" +
               "\t\"Branch\"\tTEXT,\n" +
               "\t\"Batch\"\tINTEGER,\n" +
               "\t\"pw\"\tTEXT,\n" +
               "\t\"type\"\tINTEGER NOT NULL DEFAULT 0,\n" +
               "\t\"RollNO\"\tTEXT UNIQUE,\n" +
               "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
               ");");

       db.execSQL("CREATE TABLE \"resources\" (\n" +
               "\t\"id\"\tINTEGER NOT NULL,\n" +
               "\t\"Name\"\tTEXT NOT NULL,\n" +
               "\t\"Location\"\tTEXT,\n" +
               "\t\"type\"\tTEXT NOT NULL DEFAULT 'CLASSROOM',\n" +
               "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
               ");");

       db.execSQL("CREATE TABLE \"bookings\" (\n" +
               "\t\"id\"\tINTEGER NOT NULL,\n" +
               "\t\"rid\"\tINTEGER NOT NULL,\n" +
               "\t\"uid\"\tINTEGER NOT NULL,\n" +
               "\t\"sdate\"\tdatetime,\n" +
               "\t\"edate\"\tdatetime,\n" +
               "\t\"Authorised\"\tINTEGER NOT NULL DEFAULT 0,\n" +
               "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
               ");");





    }

    DataClass(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        db=SQLiteDatabase.openDatabase("/data/data/com.example.camelcasetestt/databases/IITDHResourcesApp.db",null,SQLiteDatabase.OPEN_READWRITE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+" users");
        db.execSQL("DROP TABLE IF EXISTS"+" bookings");
        db.execSQL("DROP TABLE IF EXISTS"+" resources");

        onCreate(db);


    }
    public void dbClose()
    {
        db.close();
    }


    int nameID(String name)
    {
        Cursor res = db.rawQuery("select id from users where name = ?",new String[]{name});
        if(res != null && res.getCount()>0)
            res.moveToFirst();
        return res.getInt(0);

    }
    int IDfromRNO(String rno)
    {
        Cursor res = db.rawQuery("select id from users where RollNO= ?",new String[]{rno});
        if(res != null && res.getCount()>0)
            res.moveToFirst();
        return res.getInt(0);

    }

    String userName(int id)

    {
        String uid = Integer.toString(id);
        Cursor res = db.rawQuery("select * from users where id =?",new String[]{uid});
        if(res != null && res.getCount()>0)
            res.moveToFirst();
        return res.getString(0);

    }

    boolean insertToUsers(ContentValues cont)
    {


        //long res=db.insert("users",null,cont);
        String s[] = new String[2];
        s[0] = cont.get("Name").toString();
        s[1] = cont.get("RollNO").toString();

        db.execSQL("insert into users(Name,RollNO) values(?,?)",s);


//        if(res==-1)
//        {
//            return false;
//        }
        return true;



    }

    Cursor getUser(int id)
    {
        Cursor res = db.rawQuery("SELECT * from users where id = ? ",new String[]{Integer.toString(id)});
        res.moveToFirst();
        return res;
    }

    Cursor getUserNames()
    {
        Cursor res =db.rawQuery("select * from users",null);
        return res;
    }

    Cursor getresourceNames()
    {
        Cursor res = db.rawQuery("select name from resources",null);
        return res;
    }

    int getResID(String name)
    {
        Cursor res=db.rawQuery(" SELECT id FROM resources WHERE Name = ? ",new String[]{name});
       if(res!=null)
        {
            res.moveToFirst();
            return Integer.parseInt(res.getString(0));

        }
        else
            return -1;
    }

    long insertBookingRid(int rid,int uid)
    {
       ContentValues cont=new ContentValues();
       cont.put("rid",rid);
       cont.put("uid",uid);
       return db.insert("bookings",null,cont);

    }

    void insertSDate(int id,String date)
    {
        db.execSQL("UPDATE bookings SET sdate = ? WHERE id = ?",new String[]{date,Integer.toString(id)});
    }

    void insertEDate(int id, String date)
    {
        db.execSQL("UPDATE bookings SET edate = ? WHERE id = ?",new String[]{date,Integer.toString(id)});

    }

    void insertRemarks(int id,String rem)
    {
        db.execSQL("UPDATE bookings SET remarks = ? where id = ? ",new String[]{rem,Integer.toString(id)});
    }

    void insertTarget(int id,String targ)
    {
        db.execSQL("UPDATE bookings SET Target = ? WHERE id = ?",new String[]{targ,Integer.toString(id)});

    }

    long insertToResources(ContentValues cont)
    {
        long res = db.insert("resources",null,cont);
        return res;

    }


    String userBatch(int id)
    {
        Cursor res = db.rawQuery("SELECT Batch FROM users WHERE id = ?",new String[]{Integer.toString(id)});
        if(res!=null)
        {
            res.moveToFirst();
            return res.getString(0);

        }
        else
            return "-1";
    }

    String userBranch(int id)
    {
        Cursor res = db.rawQuery("SELECT Branch FROM users WHERE id = ?",new String[]{Integer.toString(id)});
        if(res!=null)
        {
            res.moveToFirst();
            return res.getString(0);

        }
        else
            return "-1";

    }

    String userRoll(int id)
    {
        Cursor res = db.rawQuery("SELECT RollNO FROM users WHERE id = ?",new String[]{Integer.toString(id)});
        if(res!=null)
        {
            res.moveToFirst();
            return res.getString(0);

        }
        else
            return "-1";

    }

    Cursor viewBookings(int rid)
    {
       Cursor res = db.rawQuery(" SELECT uid, sdate, edate from bookings WHERE rid = ? ",new String[]{Integer.toString(rid)});
        if(res!=null)
        {
            res.moveToFirst();
            return res;

        }
        else
            return null;

    }

    boolean insertToUserTable(ContentValues cont)
    {

        Cursor cur = db.rawQuery("SELECT id FROM users WHERE RollNO = ?",new String[]{cont.get("RollNO").toString()});
        if(cur.getCount()==1)
            return false;
        long res = db.insert("users",null,cont);
        if(res==-1)
            return false;
        return true;
    }

    Cursor getAllBookings()
    {
        Cursor res = db.rawQuery(" SELECT uid, rid, sdate, edate,Target from bookings",null);
        if(res!=null)
        {
            res.moveToFirst();
            return res;

        }
        else
            return null;

    }


    String getResourceName(int id)
    {
        Cursor res = db.rawQuery(" SELECT name FROM resources WHERE id = ? " ,new String[]{Integer.toString(id)});

        if(res!=null)
        {
            res.moveToFirst();
            return res.getString(0);

        }
        else
            return null;
    }

    Cursor targetSearch(String s)
    {
        Cursor res  = db.rawQuery("SELECT  uid,rid,sdate,edate FROM bookings WHERE Target like ?",new String[]{"%"+s+"%"});
        if(res!=null)
        {
            res.moveToFirst();
            return res;

        }
        else
            return null;

    }





}
