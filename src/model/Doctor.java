package model;

public class Doctor {

    private int id;
    private String name;
    private String specialization;
    private double salary;

    public Doctor(String name,String specialization,double salary){

        this.name=name;
        this.specialization=specialization;
        this.salary=salary;
    }

    public Doctor(int id,String name,String specialization,double salary){

        this.id=id;
        this.name=name;
        this.specialization=specialization;
        this.salary=salary;
    }

    public int getId(){

        return id;
    }

    public String getName(){

        return name;
    }

    public String getSpecialization(){

        return specialization;
    }

    public double getSalary(){

        return salary;
    }
}