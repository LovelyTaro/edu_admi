package com.j2ee.edu_admi.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Teacher extends User{
    private int teacherNum;
    private int userNum;
    private String teacherName;
    private String gender;
    private int facultyNum;
    private Date birth;
    private String facultyName;


    public Teacher() {
        this.setId("tea");
    }

    public Teacher(String teacherName, String gender,int facultyNum, String birth) {
        this.teacherName = teacherName;
        this.facultyNum = facultyNum;
        this.setBirth(birth);
        this.gender = gender;
        this.setId("tea");
    }

    public Teacher(int teacherNum, int userNum, String teacherName, String gender, int facultyNum, String birth) {
        this.teacherNum = teacherNum;
        this.teacherName = teacherName;
        this.userNum = userNum;
        this.facultyNum = facultyNum;
        this.setBirth(birth);
        this.gender = gender;
        this.setId("tea");
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getFacultyNum() {
        return facultyNum;
    }

    public void setFacultyNum(int facultyNum) {
        this.facultyNum = facultyNum;
    }

    public String getBirth() {
        return birth.toString();
    }

    public void setBirth(String birth) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = simpleDateFormat.parse(birth);
            this.birth = new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFacultyName() {
        return Faculty.getFacultyNameByNum(facultyNum);
    }
}
