package com.example.max00.moodle.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.max00.moodle.Entity_Class.Student;
public class DAO extends SQLiteOpenHelper {
    public static final String DB_NAME = "bd_usuarios";
    public static final String TABLA_USUARIO = "Estudiante";
    public static final String CAMPO_CARNET = "dui";
    public static final String CAMPO_NOTA = "nota";
    public static final String CAMPO_MATERIA = "materia";
    public static final String CAMPO_CATEDRATICO = "catedratico";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " + TABLA_USUARIO+"("+CAMPO_CARNET+" TEXT,"+ CAMPO_NOTA + " TEXT, "+CAMPO_MATERIA+" TEXT, "+ CAMPO_CATEDRATICO + " TEXT)";
    public static DAO myDB = null;
    private Context context;
    SQLiteDatabase db;

    public static DAO getInstance(Context ctx){
        if(myDB == null){
            myDB = new DAO(ctx.getApplicationContext());
        }
        return myDB;
    }

    public DAO(Context context){
        super(context,DB_NAME,null,1);
        this.context = context;
        db = this.getWritableDatabase();
    }

    public boolean add(Student s){
        ContentValues values = new ContentValues();
        values.put(CAMPO_CARNET,s.getCarnet());
        values.put(CAMPO_NOTA,s.getNota());
        values.put(CAMPO_MATERIA,s.getMateria());
        values.put(CAMPO_CATEDRATICO,s.getCatedratico());
        db.insert(TABLA_USUARIO,null,values);
        Toast.makeText(context,"Insertado con exito", Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_USUARIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CAMPO_CARNET);
        onCreate(db);
    }
}