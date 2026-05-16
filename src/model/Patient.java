package model;

public class Patient {

    private int id;
    private String name;
    private int age;
    private String address;
    private double payment;

    public Patient(String name, int age, String address, double payment) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.payment = payment;
    }

    public Patient(int id, String name, int age, String address, double payment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public double getPayment() {
        return payment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}