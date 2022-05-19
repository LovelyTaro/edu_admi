package com.j2ee.edu_admi.beans;

import java.util.List;

public class CourseManagementBean {

    private final DBControl dbControl;

    public CourseManagementBean() throws ClassNotFoundException {
        dbControl = DBControl.getDBControl();
    }

    public List<Course> getCourseList() {
        if(dbControl.getCourseList()!=null){
            return dbControl.getCourseList();
        }
        return null;
    }



}
