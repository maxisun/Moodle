package com.example.max00.moodle.Entity_Class;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
    private String carnet;
    private String nota;
    private String materia;
    private String catedratico;

    public Student() {
    }

    public Student(String carnet, String nota, String materia, String catedratico) {
        this.carnet = carnet;
        this.nota = nota;
        this.materia = materia;
        this.catedratico = catedratico;
    }

    public Student(String carnet, String nota) {
        this.carnet = carnet;
        this.nota = nota;
    }

    protected Student(Parcel in) {
        carnet = in.readString();
        nota = in.readString();
        materia = in.readString();
        catedratico = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(carnet);
        dest.writeString(nota);
        dest.writeString(materia);
        dest.writeString(catedratico);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }
}
