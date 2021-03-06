package com.example.max00.moodle.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.max00.moodle.Entity_Class.Student;

import java.util.ArrayList;
import java.util.List;

public class DAO extends SQLiteOpenHelper {
    public static final String DB_NAME = "bd_usuarios";
    public static final String TABLA_USUARIO = "Estudiante";
    public static final String CAMPO_CARNET = "carnet";
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

    public void add(Student s){
        ContentValues values = new ContentValues();
        values.put(CAMPO_CARNET,s.getCarnet());
        values.put(CAMPO_NOTA,s.getNota());
        values.put(CAMPO_MATERIA,s.getMateria());
        values.put(CAMPO_CATEDRATICO,s.getCatedratico());
        db.insert(TABLA_USUARIO,null,values);
        Toast.makeText(context,"Insertado con exito", Toast.LENGTH_SHORT).show();
    }

    public Student findUser(String carnet){
        Student p;
        String[] parametros = {carnet};
        String[] campos = {CAMPO_NOTA,CAMPO_MATERIA,CAMPO_CATEDRATICO};

        try {
            Cursor cursor = db.query(TABLA_USUARIO,campos,CAMPO_CARNET+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            p = new Student(carnet,cursor.getString(0),cursor.getString(1),cursor.getString(2));
        }catch (Exception e){
            p = null;
        }
        return p;
    }

    public boolean editUser(Student student){
        String[] parametros = {student.getCarnet()};
        //String[] campos = {CAMPO_NOMBRE};
        ContentValues values = new ContentValues();
        values.put(CAMPO_NOTA,student.getNota());
        db.update(TABLA_USUARIO,values,CAMPO_CARNET+"=?",parametros);
        Toast.makeText(context,"Usuario Actualizado con exito",Toast.LENGTH_LONG).show();
        return true;
    }

    public ArrayList<Student> getAllElements() {
        ArrayList<Student> list = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLA_USUARIO;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            try {
                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Student obj = new Student();
                        //only one column
                        obj.setCarnet(cursor.getString(0));
                        //you could add additional columns here..
                        obj.setNota(cursor.getString(1));
                        obj.setMateria(cursor.getString(2));
                        obj.setCatedratico(cursor.getString(3));
                        list.add(obj);
                    } while (cursor.moveToNext());
                }
            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { Toast.makeText(context,"pinga",Toast.LENGTH_SHORT).show(); } catch (Exception ignore) {}
        }
        return list;
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
