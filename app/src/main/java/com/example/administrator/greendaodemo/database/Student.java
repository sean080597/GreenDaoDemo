package com.example.administrator.greendaodemo.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

//This annotation defines this class as a table to green dao
@Entity
public class Student {
    @org.greenrobot.greendao.annotation.Id (autoincrement = true)
    private Long Id;
    private String name;
    private String fatherName;
    private String motherName;
    private String contactNumber;
    @Generated(hash = 1072631787)
    public Student(Long Id, String name, String fatherName, String motherName, String contactNumber) {
        this.Id = Id;
        this.name = name;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.contactNumber = contactNumber;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFatherName() {
        return this.fatherName;
    }
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getMotherName() {
        return this.motherName;
    }
    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }
    public String getContactNumber() {
        return this.contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
