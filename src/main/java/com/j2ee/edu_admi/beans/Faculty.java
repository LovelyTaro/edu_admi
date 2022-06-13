package com.j2ee.edu_admi.beans;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Dcy
 */
public class Faculty {


    static {
        setFacultyList();
    }

    //院系列表
    private static Map<Integer,String> facultyList;
    private int facultyNum;
    private String facultyName;

    public Faculty(){

    }

    public static void setFacultyList() {
        facultyList = new HashMap<>();
        facultyList.put(1,"计算机系");
        facultyList.put(2,"电子系");
        facultyList.put(3,"自动化系");

        //应该是第一次的时候向数据库查询然后获得List
    }

    public static String getFacultyNameByNum(int num){
        return facultyList.get(num);
    }

    public int getFacultyNum() {
        return facultyNum;
    }

    public void setFacultyNum(int facultyNum) {
        this.facultyNum = facultyNum;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }
}
