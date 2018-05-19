package com.example.max00.moodle.Entity_Class;

public class Student {
    private int carnet;
    private float nota;
    private String materia;
    private String catedratico;

    public Student() {
    }

    public Student(int carnet, float nota, String materia, String catedratico) {
        this.carnet = carnet;
        this.nota = nota;
        this.materia = materia;
        this.catedratico = catedratico;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
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
