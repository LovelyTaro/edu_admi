package com.j2ee.edu_admi.beans;

public class Course{

    private int courseNum;
    private int teacherNum;
    private int weeks;
    private int credit;
    private String courseName;
    private String courseTime;
    private String coursePosition;
    private int facultyNum;
    private String facultyName;

    public Course() {
    }

    public Course(int courseNum, String courseName , int teacherNum, String courseTime, String coursePosition, int weeks, int facultyNum, int credit) {
        this.courseNum = courseNum;
        this.teacherNum = teacherNum;
        this.weeks = weeks;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.coursePosition = coursePosition;
        this.facultyNum = facultyNum;
        this.credit = credit;
        this.facultyName = Faculty.getFacultyNameByNum(facultyNum);
    }

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }

    public int getWeeks() {
        return weeks;
    }

    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCoursePosition() {
        return coursePosition;
    }

    public void setCoursePosition(String coursePosition) {
        this.coursePosition = coursePosition;
    }

    public int getFacultyNum() {
        return facultyNum;
    }

    public void setFacultyNum(int facultyNum) {
        this.facultyNum = facultyNum;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getFacultyName() {
        return facultyName;
    }

}
