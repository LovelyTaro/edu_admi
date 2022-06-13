package com.j2ee.edu_admi.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * @author Dcy
 */
public class Student extends User {
    private int studentNum;
    private int userNum;
    private String studentName;
    private String gender;
    private  int facultyNum;
    private String facultyName;
    private Date birth;
    private Integer score;

    public Student(){
        this.setId("stu");
    }

    public Student(int studentNum, int userNum, String studentName, String gender, int facultyNum, String birth){
        this.studentNum = studentNum;
        this.userNum = userNum;
        this.studentName = studentName;
        this.gender = gender;
        this.facultyNum = facultyNum;
        this.setBirth(birth);
        this.setId("stu");
    }

    public Student( String studentName, String gender, int facultyNum, String birth) {
        this.studentName = studentName;
        this.gender = gender;
        this.facultyNum = facultyNum;
        this.setBirth(birth);
        this.setId("stu");
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getFacultyName() {
        return Faculty.getFacultyNameByNum(facultyNum);
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
