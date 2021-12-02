package com.example.test3.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="project_db";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(Flags.CREATE_fNAME);
        db.execSQL(Informations.CREATE_iTABLE);
        db.execSQL(Choice.CREATE_cTABLE);
        db.execSQL(Thedays.CREATE_tTABLE);
        db.execSQL(Places.CREATE_pTABLE);
        db.execSQL(Save.CREATE_sTABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+Flags.TABLE_fNAME);
        db.execSQL("DROP TABLE IF EXISTS "+Informations.TABLE_iNAME);
        db.execSQL("DROP TABLE IF EXISTS "+Choice.TABLE_cNAME);
        db.execSQL("DROP TABLE IF EXISTS "+Thedays.TABLE_tNAME);
        db.execSQL("DROP TABLE IF EXISTS "+Places.TABLE_pNAME);
        db.execSQL("DROP TABLE IF EXISTS "+Save.TABLE_sNAME);

        onCreate(db);
    }

    //플래그 기본 정도 insert
    public void addFlag(Flags flags){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("fname",flags.getName());
        content.put("fflag",flags.getFlag());

        db.insert(flags.TABLE_fNAME,null,content);
        db.close();
    }

    //플래그 설정
    public void updateFlags(Flags flags){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("fflag",flags.getFlag());
        content.put("fname",flags.getName());

        db.update(flags.TABLE_fNAME,content,"fname=?",new String[]{content.getAsString("fname")});

        db.close();
    }

    public Flags getOneFlag(String key){
        Flags flag=new Flags();

        db=this.getWritableDatabase();
        String selectquery="SELECT * FROM "+flag.TABLE_fNAME+" WHERE fname='"+key+"'";

        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);

        cursor.moveToFirst();
        flag.setFlag(cursor.getInt(1));

        cursor.close();
        db.close();
        return flag;
    }


    //획득한 정보 insert
    public void addInformations(Informations informations){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("iinfo",informations.getInfo());

        db.insert(informations.TABLE_iNAME,null,content);

        db.close();
    }

    //획득한 정보 모두 select(얻은 정보란)
    public List<Informations> getAllInfo(){
        List<Informations> informations=new ArrayList<Informations>();

        db=this.getReadableDatabase();
        String selectquery="SELECT * FROM "+Informations.TABLE_iNAME;

        Cursor cursor=db.rawQuery(selectquery,null);

        if(cursor.moveToFirst()){
            do{
                Informations info=new Informations();
                info.setInfo(cursor.getString(0));
                informations.add(info);
            }while(cursor.moveToNext());
        }

        db.close();
        return informations;
    }

    //선택한 버튼내용 add
    public void addChoice(Choice choice){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("ckey",choice.getKey());
        content.put("cchoice",choice.getChoice());

        db.insert(Choice.TABLE_cNAME,null,content);

        db.close();
    }

    //선택한 버튼 내용 select(마지막 요약)
    public Choice getOneChoice(String key){
        Choice choice=new Choice();

        db=this.getWritableDatabase();
        String selectquery="SELECT * FROM "+Choice.TABLE_cNAME+" WHERE ckey='"+key+"'";

        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);
        cursor.moveToFirst();
        choice.setChoice(cursor.getString(1));

        cursor.close();
        db.close();
        return choice;
    }

    //활성화된 날짜 저장
    public void addDays(Thedays days){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("tname",days.getName());
        content.put("tday",days.getDay());

        db.insert(days.TABLE_tNAME,null,content);
        db.close();
    }

    //활성화된 날짜 select(비교)
    public Thedays getOneday(String name){
        Thedays days=new Thedays();

        db=this.getWritableDatabase();
        String selectquery="SELECT * FROM "+Thedays.TABLE_tNAME+" WHERE tname='"+name+"'";

        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);

        if (cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            days.setDay(cursor.getString(1));

            cursor.close();
            db.close();
            return days;
        }else{
            return days=null;
        }
    }

    //달력 표시 위해 활성화 날짜 모두 가져오기
    public List<Thedays> getAllDays(){
        List<Thedays> days=new ArrayList<Thedays>();

        db=this.getReadableDatabase();
        String selectquery="SELECT * FROM "+Thedays.TABLE_tNAME;

        Cursor cursor=db.rawQuery(selectquery,null);

        if(cursor.moveToFirst()){
            do{
                Thedays thedays=new Thedays();
                thedays.setName(cursor.getString(0));
                thedays.setDay(cursor.getString(1));
                days.add(thedays);
            }while(cursor.moveToNext());
        }

        db.close();
        return days;
    }

    //선택한 장소 add
    public void addPlace(Places places){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("pplace",places.getPlace());

        db.insert(places.TABLE_pNAME,null,content);
        db.close();
    }

    //찾아볼 수 있는 장소 하나씩 select
    public Places getOnePlace(String place){
        Places pl=new Places();

        db=this.getWritableDatabase();
        String selectquery="SELECT * FROM "+Places.TABLE_pNAME+" WHERE pplace='"+place+"'";

        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);
        cursor.moveToFirst();
        pl.setPlace(cursor.getString(1));

        return pl;
    }

    //파일저장
    public void addSave(Save save){
        db=this.getWritableDatabase();

        ContentValues content=new ContentValues();
        content.put("sname",save.getJsonname());
        content.put("sproid",save.getProid());

        db.insert(save.TABLE_sNAME,null,content);
        db.close();
    }

    //save한 내용 가져오기
    public Save getSave(){
        Save save=new Save();

        db=this.getWritableDatabase();
        String selectquery="SELECT * FROM "+Save.TABLE_sNAME+" ORDER BY snum DESC limit 1";

        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectquery,null);

        if (cursor != null && cursor.getCount() != 0){
            cursor.moveToFirst();
            save.setJsonname(cursor.getString(1));
            save.setProid(cursor.getInt(2));

            cursor.close();
            db.close();
            return save;
        }else{
            return save=null;
        }
    }
}
