package model;

public class Student {

    private int studentId;
    private String name;
    private String email;
    private String phone;
    private String branch;
    private int year;

    public Student(int studentId, String name, String email,
                   String phone, String branch, int year) {

        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.branch = branch;
        this.year = year;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBranch() {
        return branch;
    }

    public int getYear() {
        return year;
    }
}