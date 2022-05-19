package com.j2ee.edu_admi.beans;

/**
 * @author Dcy
 */
public class User {

    public User(String username, String password, String id){
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
    }

    private String username;

    private String password;

    private String id;

    private static final String Teacher = "老师";
    private static final String Student = "学生";
    private static final String Administrator = "管理员";


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //获取身份的简称：stu、tea、adm
    public String getId() {
        return id;
    }

    //获取身份的中文名
    public String getIdChinese(){
        switch(id){
            case "stu":
                return Student;
            case "tea":
                return "Teacher";
            case "adm":
                return "Administrator";
            default:
                return null;
        }
    }

    //将输入的中文名转成简称,如果本身就是简称则直接赋值
    public void setId(String id) {
        switch(id){
            case Teacher:
                this.id = "tea";
                break;
            case Student:
                this.id = "stu";
                break;
            case Administrator:
                this.id = "adm";
                break;
            case "tea":
            case "adm":
            case "stu":
                this.id = id;
                break;
        }
    }
}
